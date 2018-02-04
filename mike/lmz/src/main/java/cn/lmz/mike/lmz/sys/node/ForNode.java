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
import cn.lmz.mike.lmz.sys.util.param.IParamLexer;
import cn.lmz.mike.lmz.sys.util.param.ParamBean;
import cn.lmz.mike.lmz.sys.util.param.ParamUtil;

import java.util.List;

public class ForNode extends ANode implements IParamLexer {
	
	private ANode initNode;
	private ANode blNode;
	private ANode afterNode;
	//private BlockNode blNode;
	private BlockNode bkNode;
	
	private Context ctxf;
	
	public ForNode(Context ctx){
		super(ctx);
		ctxf = ctx.getChildCtx();
	}
	
	@Override
	public Object interpret(ICode c) throws Exception {
		super.interpret(c.getR());
		
		Code nc = c.getNext();
		if(nc==null||nc.getCh()!='(') throw new ErrCodeException(c,ErrCodeException.ERR_CODE_100+(nc==null?null:nc.getCh()));
		
		ParamBean pb = ParamUtil.getParamBean(nc,null);
		List<Row> rlist = ParamUtil.splitRow(pb.getR());
		nc = pb.getNc().getNext();

		if(nc==null||!TypeUtil.TYPE_BLOCK.equalsIgnoreCase(nc.getType())){
			throw new ErrCodeException(c,ErrCodeException.ERR_CODE_300);
		}

		
		Row initR = ParamUtil.getListRow(rlist, 0);
		initNode = NodeExpression.interpret(ctx,initR);
		initNode.setCodeStr(initR.getCodeStr());
		
		Row blR = ParamUtil.getListRow(rlist, 1);
		blNode = NodeExpression.interpret(ctx,blR);
		blNode.setCodeStr(blR.getCodeStr());
		
		Row afR = ParamUtil.getListRow(rlist, 2);
		afterNode = NodeExpression.interpret(ctx,afR);
		afterNode.setCodeStr(afR.getCodeStr());

		Block b = (Block)ctx.getCfg().get(nc.getVal());
		if(b==null){
			throw new ErrCodeException(c,ErrCodeException.ERR_CODE_400);
		}
		BlockNode bn = new BlockNode(ctx.getChildCtx());
		bn.interpret(b);
		
		bkNode = bn; 
		
		return null;
	}

	@Override
	public Object execute(Context ctx) throws Exception {
		
		Context ctxc = bkNode.getCtx().init();
		ctxf.init();
		
		initNode.runNode(ctxf);
		ctxc.putAll(ctxf);
		
		Object v = true;
		if(blNode!=null){	
			v = blNode.runNode(ctxf);
			if(v==null&&!(v instanceof Boolean)){
				throw new RunCodeException(this,RunCodeException.ERR_RUN_100);
			}
		}
		
		Boolean bl = (Boolean)v;
		Boolean isBreak = false;
		
		while(bl){
			
			ctxc = bkNode.getCtx().init();
			ctxc.putAll(ctxf);

			bkNode.runNode(ctxc);
			
			isBreak = (Boolean)ctx.getCfg().get(Const.V_BREAK);
			if(isBreak!=null&&isBreak){
				O.debug(ctx.getRunCode()+"break");
				break;
			}

			Object vreturn = (Boolean)ctx.getCfg().get(Const.V_RETURN);
			if(vreturn!=null){
				O.debug(ctx.getRunCode()+"return");
				break;
			}
			
			ctxf.putHave(ctxc);
			afterNode.runNode(ctxf);
			
			Object v1 = true;
			if(blNode!=null){	
				v1 = blNode.runNode(ctxf);
				if(v==null&&!(v instanceof Boolean)){
					throw new RunCodeException(this,RunCodeException.ERR_RUN_100);
				}
			}

			bl = (Boolean)v1;
		}
		
		if(isBreak!=null&&isBreak){
			ctx.getCfg().put(Const.V_BREAK,false);
		}
		
		ctxf.getCtx().clear();
		ctxf = null;
		return null;
	}

	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]for("+getString(initNode, ctx)+getString(blNode, ctx)+getString(afterNode,ctx)+")"+getString(bkNode,ctx);
	}

	@Override
	public void lexerParam(Code nc, Row r) {
		if(';'==nc.getCh()){
			r = new Row();
		}else{
			r.add(nc);
		}
	}
	
}
