package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.ErrCodeException;
import cn.lmz.mike.lmz.sys.exception.RunCodeException;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.lexer.Block;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.lexer.Row;
import cn.lmz.mike.lmz.sys.util.TypeUtil;
import cn.lmz.mike.lmz.sys.util.param.ParamBean;
import cn.lmz.mike.lmz.sys.util.param.ParamUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class IfNode extends ANode {

	private Map<ANode,BlockNode> iflist = new LinkedHashMap<ANode,BlockNode>();
	
	private Code nc = null;

	public IfNode(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object execute(Context ctx) throws Exception {

		for(Map.Entry<ANode, BlockNode> en:iflist.entrySet()){
			
			ANode blNode = en.getKey();
			BlockNode b = en.getValue();
			
			if(blNode==null){
				b.runNode(b.getCtx().init());
				break;
			}
			
			Object v = blNode.runNode(ctx);
			if(v==null&&!(v instanceof Boolean)){
				throw new RunCodeException(this,"not boolean val "+getCode());
			}
			Boolean bl = (Boolean)v;
			
			if(bl){
				
				b.runNode(b.getCtx().init());
				break;
				
			}
			
			Boolean isBreak = (Boolean)ctx.getCfg().get(Const.V_BREAK);
			if(isBreak!=null&&isBreak){
				O.debug(ctx.getRunCode()+"break");
				break;
			}
			
			Object vreturn = (Boolean)ctx.getCfg().get(Const.V_RETURN);
			if(vreturn!=null){
				O.debug(ctx.getRunCode()+"return");
				break;
			}
		}

		return null;
	}

	private Map<ANode,BlockNode> getIfNode(Code c) throws Exception{

		if("if".equalsIgnoreCase(c.getVal())){

			nc = c.getNext();
			if(nc==null||nc.getCh()!='(') throw new ErrCodeException(c,ErrCodeException.ERR_CODE_100+(nc==null?null:nc.getCh()));
			
			
			ParamBean pb = ParamUtil.getParamBean(nc,null);
			Row blR = pb.getR();
			
			nc = pb.getNc().getNext();

			if(nc==null||!TypeUtil.TYPE_BLOCK.equalsIgnoreCase(nc.getType())){
				throw new ErrCodeException(c,ErrCodeException.ERR_CODE_300);
			}
			
			ANode blNode = NodeExpression.interpret(ctx,blR);
			
			Block ifb = (Block)ctx.getCfg().get(nc.getVal());
			if(ifb==null){
				throw new ErrCodeException(c,ErrCodeException.ERR_CODE_400);
			}
			BlockNode bn = new BlockNode(ctx.getChildCtx());
			bn.interpret(ifb);
			
			
			Map<ANode,BlockNode> ifn = new HashMap<ANode,BlockNode>();
			ifn.put(blNode, bn);
			
			nc = nc.getNext();
			
			return ifn;
		}else if("else".equalsIgnoreCase(c.getVal())){
			nc = c.getNext();
			if(nc!=null&&"if".equalsIgnoreCase(nc.getVal())){
				return getIfNode(nc);
			}else{

				if(nc==null||!TypeUtil.TYPE_BLOCK.equalsIgnoreCase(nc.getType())){
					throw new ErrCodeException(c.getR(),ErrCodeException.ERR_CODE_300);
				}
				
				Block elseb = (Block)ctx.getCfg().get(nc.getVal());
				if(elseb==null){
					throw new ErrCodeException(c.getR(),ErrCodeException.ERR_CODE_400);
				}
				BlockNode elsebn = new BlockNode(ctx.getChildCtx());
				elsebn.interpret(elseb);
				
				Map<ANode,BlockNode> ifn = new HashMap<ANode,BlockNode>();
				ifn.put(null, elsebn);
				
				return ifn;
			}
			
			
		}
		
		return null;
	}
	
	@Override
	public Object interpret(ICode c) throws Exception {
		super.interpret(c.getR());
		
		nc = (Code) c;
		Map<ANode,BlockNode> ifn = getIfNode(nc);
		if(ifn!=null){
			iflist.putAll(ifn);
		}
		
		while(nc!=null&&ifn!=null){
			iflist.putAll(ifn);
			ifn = getIfNode(nc);	
		}
		
		
		return null;
	}

	public String toString(Context ctx){
		String msg="";
		for(Map.Entry<ANode, BlockNode> en:iflist.entrySet()){
			ANode blNode = en.getKey();
			BlockNode b = en.getValue();
			msg+=(blNode==null?"[else]":getString(blNode, ctx))+"-->"+getString(b, ctx)+"\r\n";
		}
		return "["+this.getClass().getSimpleName()+"]"+msg;
	}
	
}
