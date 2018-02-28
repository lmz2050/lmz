package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.ErrCodeException;
import cn.lmz.mike.lmz.sys.lexer.Block;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.lexer.Row;
import cn.lmz.mike.lmz.sys.util.TypeUtil;
import cn.lmz.mike.lmz.sys.util.param.IParamLexer;
import cn.lmz.mike.lmz.sys.util.param.ParamBean;
import cn.lmz.mike.lmz.sys.util.param.ParamUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class FunctionNode extends ANode implements IParamLexer {
	
	private List<String> args = new ArrayList<String>();
	private Map<String,Object> params = new LinkedHashMap<String,Object>();
	private BlockNode bkNode;

	public FunctionNode(Context ctx) {
		super(ctx);
	}
	
	
	public FunctionNode setParams(Object...objs){
		if(objs!=null&&objs.length>0&&args.size()>0){
			for(int i=0;i<args.size();i++){
				String key = args.get(i);
				if(objs.length>i){
					params.put(key, objs[i]);
				}
			}
		}
		return this;
	}
	public FunctionNode setParams( Map<String,Object> sets){
		if(sets!=null&&sets.size()>0&&args.size()>0){
			for(int i=0;i<args.size();i++){
				String key = args.get(i);
				params.put(key, sets.get(key));
			}
		}
		return this;
	}

	@Override
	protected Object execute(Context ctx) throws Exception {

		Context ctxi = bkNode.getCtx().init();
		ctxi.getCtx().putAll(params);
		
		bkNode.runNode(ctxi);
		
		
		Object vreturn = ctx.getCfg().get(Const.V_RETURN);
		if(vreturn!=null){
			ctx.getCfg().put(Const.V_RETURN, null);
		}
		return vreturn;
		
	}
	
	public Object interpret(ICode c) throws Exception{
		super.interpret(c);
		
		Code nc = c.getNext();

		String key;
		if(TypeUtil.TYPE_ID.equalsIgnoreCase(nc.getType())){
			key = nc.getVal();
			ctx.getCfg().put(key, this);
			nc = nc.getNext();
		}else{
			key = ctx.getCfg().put(this);
			//throw new ErrCodeException(c,"next code should be keyword but "+nc.getCh()+"["+nc.getType()+"]");
		}

		if(nc==null||nc.getCh()!='(') throw new ErrCodeException(c,ErrCodeException.ERR_CODE_100+(nc==null?null:nc.getCh()));
		
		
		ParamBean pb = ParamUtil.getParamBean(nc, this);
		nc = pb.getNc().getNext();

		if(nc==null||!TypeUtil.TYPE_BLOCK.equalsIgnoreCase(nc.getType())){
			throw new ErrCodeException(c.getR(),ErrCodeException.ERR_CODE_300);
		}
		
		
		Block b = (Block)ctx.getCfg().get(nc.getVal());
		if(b==null){
			throw new ErrCodeException(c,ErrCodeException.ERR_CODE_400);
		}
		BlockNode bn = new BlockNode(ctx.getChildCtx());
		bn.interpret(b);
		
		bkNode = bn;
		
		return null;
	}

	
	public Map<String, Object> getParams() {
		return params;
	}

	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]"+ MC.string.toStr(params.keySet())+getString(bkNode,ctx);
	}


	@Override
	public void lexerParam(Code nc,Row r) {
		if(','!=nc.getCh()){
			args.add(nc.getVal());
		}
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}
}
