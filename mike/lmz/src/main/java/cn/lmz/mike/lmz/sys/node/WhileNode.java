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

public class WhileNode extends ANode {
	
	private ANode blNode;
	private BlockNode bkNode;

	public WhileNode(Context ctx){
		super(ctx);
	}

	@Override
	public Object interpret(ICode c) throws Exception {
		super.interpret(c.getR());
		
		Code nc = c.getNext();
		if(nc==null||nc.getCh()!='(') throw new ErrCodeException(c,ErrCodeException.ERR_CODE_100);
		
		ParamBean pb = ParamUtil.getParamBean(nc,null);
		Row blR = pb.getR();
		nc = pb.getNc().getNext();
		
		
		if(blR.getList().size()<=0){
			throw new ErrCodeException(c,ErrCodeException.ERR_CODE_200);
		}else if(nc==null||!TypeUtil.TYPE_BLOCK.equalsIgnoreCase(nc.getType())){
			throw new ErrCodeException(c,ErrCodeException.ERR_CODE_300);
		}
		blNode = NodeExpression.interpret(ctx, blR);
		
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
		
		Object v = blNode.runNode(ctx);
		if(v==null&&!(v instanceof Boolean)){
			throw new RunCodeException(this,RunCodeException.ERR_RUN_100+getCode());
		}
		
		Boolean bl = (Boolean)v;
		Boolean isBreak = false;
		
		while(bl){
			
			bkNode.runNode(bkNode.getCtx().init());
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
			
			Object v1 = blNode.runNode(ctx);
			if(v1==null&&!(v1 instanceof Boolean)){
				throw new RunCodeException(this,RunCodeException.ERR_RUN_100+getCode());
			}
			bl = (Boolean)v1;
		}
		
		if(isBreak!=null&&isBreak){
			ctx.getCfg().put(Const.V_BREAK,false);
		}
		
		return null;
	}

	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]"+getString(blNode, ctx)+getString(bkNode, ctx);
	}
	
}
