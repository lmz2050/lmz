package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.invoke.InvokeU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.RunCodeException;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.lexer.Row;
import cn.lmz.mike.lmz.sys.util.TypeUtil;
import cn.lmz.mike.lmz.sys.util.param.ParamBean;
import cn.lmz.mike.lmz.sys.util.param.ParamUtil;

import java.util.Map;


public class MethodNode extends ANode {
	
	private Code key;
	private String methodName;
	private ParamNode paramNode;

	public MethodNode(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(Context ctx) throws Exception {
		
		if(methodName==null){
			return paramNode.execute(ctx);
		}
		
		if(key == null){
			FunctionNode b = (FunctionNode)ctx.getCfg().get(methodName);
			if(b==null){
				throw new RunCodeException(this, methodName+"("+paramNode+")"+paramNode.getParamr().toString());
			}
			if(b.getParams()!=null&&b.getParams().size()>0){
				Object obj = paramNode.execute(ctx);
				Map<String,Object> params = (Map<String,Object>) b.getParams();
				if(obj!=null&&obj instanceof Object[]){
					Object[] paramsv = (Object[])obj;
					Object[] keys = (Object[])params.keySet().toArray();
					
					int len = params.size()>paramsv.length?paramsv.length:params.size();
					
					for(int k=0;k<len;k++){
						params.put((String)keys[k], paramsv[k]);
					}
				}
				
			}
			
			return b.runNode(ctx);
		}
		
		return executeMethod(ctx, key, methodName, paramNode);
		
	}
	
	@Override
	public Object interpret(ICode c) throws Exception {
		super.interpret(c.getR());

		Code prec = c.getPrev();
		if(prec!=null&& TypeUtil.TYPE_ID.equalsIgnoreCase(prec.getType())){
			methodName = prec.getVal();
			prec = prec.getPrev();
			if(prec!=null&&'.'==prec.getCh()){			
				key = prec.getPrev();
			}
		}

		ParamBean pb = ParamUtil.getParamBean((Code)c,null);
		
		paramNode = new ParamNode(ctx);		
		paramNode.interpret(pb.getR());
		Code nc = pb.getNc();

		
		Row rnew = new Row();
		if(key!=null){
			rnew.addAll(NodeExpression.getPrevCodes(key));
		}else{
			rnew.addAll(NodeExpression.getPrevCodes(c.getPrev()));
		}
		rnew.add(new Code(TypeUtil.TYPE_VAR,ctx.getCfg().put(this)));
		rnew.addAll(NodeExpression.getNextCodes(nc));
		
		return rnew;

	}
	
	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]"+(key==null?"":key.toString(ctx))+"."+methodName+paramNode.toString(ctx);
	}



	public static Object executeMethod(Context ctx, Code key, String mm, ParamNode p) throws Exception{
		Object bean =null;
		Object[] param = null;
		try{
			bean = key.getV(ctx);
			//O.pn("EXE:"+key.getVal()+"."+mm+"("+p+"): bean="+bean);
			O.debug(ctx.getRunCode()+key+"."+mm+"("+p+")");
			if(bean==null){
				throw new RunCodeException(key.getVal()+"."+mm+"("+p+")", " class is null "+p.getParamr().toString());
			}
			param = (Object[])p.runNode(ctx);
			return InvokeU.invokeMethod(bean,mm,param);
		}catch(Exception e){
			O.error("executeMethod:"+bean+"."+mm+"("+ MC.toStr(param)+")==>"+e.getMessage());
			throw new RunCodeException("cmdU "+key.getVal()+"."+mm+"("+p+")", p.getParamr().toString(),e);
		}
	}

}
