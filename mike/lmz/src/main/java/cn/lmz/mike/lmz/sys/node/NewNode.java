package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.ErrCodeException;
import cn.lmz.mike.lmz.sys.exception.RunCodeException;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.lexer.Row;
import cn.lmz.mike.lmz.sys.util.SysU;
import cn.lmz.mike.lmz.sys.util.TypeUtil;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;


public class NewNode extends ANode {
	
	private Class<?> clz = null;
	private ParamNode paramNode;

	public NewNode(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(Context ctx) throws Exception {
		// TODO Auto-generated method stub
		Object o = null;
		try{
			
			if(paramNode==null){
				O.info("new "+clz);
				o = clz.newInstance();
			}else{
				Object[] paramsv =null;
				Object obj = paramNode.execute(ctx);
				if(obj!=null&&obj instanceof Object[]){
					paramsv = (Object[])obj;
				}
				
				Constructor<?> cc = null;
				Constructor<?>[] ccs = clz.getDeclaredConstructors();
				for(int i=0;i<ccs.length;i++){
					if(ccs[i].getParameterTypes().length==paramsv.length){
						cc = ccs[i];
					}
				}
				
				if(cc==null){
					throw new RunCodeException(this, clz+" Constructor is null "+getCodeStr());
				}
				
				O.info("new "+clz+" - "+paramsv);
				o = cc.newInstance(paramsv);
				
			}
			
			
		}catch(Exception e){
			throw new RunCodeException(this, clz.getCanonicalName()+e.getMessage(),e);
		}
		
		return o;
	}
	
	private Class<?> getClass(String name,Context ctx){
		Class<?> clz = null;
		Map<String,String> pkgMap = (Map<String,String>)ctx.getCfg().get(Const.PKG_MAP);
		List<String> pkgList = (List<String>)ctx.getCfg().get(Const.PKG_LIST);
		String clzname = pkgMap.get(name);
		SysU.showPkgs();
		O.dev("map class:"+name+":"+clzname);
		if(MC.string.isBlank(clzname)){
			for(String pkgname:pkgList){
				String clznamepath = pkgname+"."+name;
				clz = getClass(clznamepath);
				if(clz!=null) break;
			}
		}else{
			clz = getClass(clzname);
		}
		return clz;
	}
	private Class<?> getClass(String clzname){
		Class<?> clz = null;
		O.dev(clzname);
		try{
			clz = Class.forName(clzname);
			O.debug(" getClass clz:"+clz.getName());
		}catch(Exception e){}
		return clz;
	}

	
	public Object interpret(ICode c)  throws Exception{
		super.interpret(c.getR());
		
		String className="";
		Code nextCode = c.getNext();
		
		if(nextCode==null){
			throw new ErrCodeException(c.getR()," next code is null ");
		}
		
		while(nextCode!=null&&'('!=nextCode.getCh()){		
			className+=nextCode.getVal();
			nextCode = nextCode.getNext();
		}
		
		Row r = new Row();
		nextCode = nextCode.getNext();
		int in_cdt = 1;
		
		while(nextCode!=null&&in_cdt>0){
			
			if(nextCode.getCh()=='('){
				in_cdt++;
			}
			if(nextCode.getCh()==')'){
				in_cdt--;
			}
			if(in_cdt>0){
				r.add(nextCode);
			}
			nextCode = nextCode.getNext();
		}
		
		if(r.getList().size()>0){
			paramNode = new ParamNode(ctx);
			paramNode.interpret(r);
		}

		if(className!=null&&className.trim().length()>0){
			
			if("function".equalsIgnoreCase(className)){
				clz = FunctionNode.class;
			}else if(className.indexOf(".")!=-1){
				clz = Class.forName(className);
			}else{
				clz = getClass(className, ctx);
			}
			
		}
		
		if(clz==null){
			throw new ErrCodeException(c.getR(), "clz is null ");
		}

		Code newCode = new Code(TypeUtil.TYPE_NEW,ctx.getCfg().put(this));
	
		Row rr = new Row();
		rr.addAll(NodeExpression.getPrevCodes(c));
		rr.add(newCode);
		if(nextCode!=null){
			rr.add(nextCode);
			rr.addAll(NodeExpression.getNextCodes(nextCode));
		}
		return rr;
	}
	
	
	

}
