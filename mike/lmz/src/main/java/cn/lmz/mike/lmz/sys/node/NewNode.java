package cn.lmz.mike.lmz.sys.node;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Map;


public class NewNode extends ANode {

	private static final Logger log = LoggerFactory.getLogger(NewNode.class);

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
				log.info("new "+clz);
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

				log.info("new "+clz+" - "+paramsv);
				o = cc.newInstance(paramsv);
				
			}
			
			
		}catch(Exception e){
			throw new RunCodeException(this, clz.getCanonicalName()+e.getMessage(),e);
		}
		
		return o;
	}
	
	private Class<?> getClass(String name,Context ctx){
		Map<String,Class<?>> pkgMap = (Map<String,Class<?>>)ctx.getCfg().get(Const.PKG_MAP);
		Class<?> clz = pkgMap.get(name);
		//SysU.showPkgs();
		log.trace("map class:"+name+":"+SysU.getClsStr(clz));
		return clz;
	}
	private Class<?> getClass(String clzname){
		Class<?> clz = null;
		log.trace(clzname);
		try{
			clz = Class.forName(clzname);
			log.debug(" getClass clz:"+clz.getName());
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
			SysU.showPkgs();
			throw new ErrCodeException(c.getR(), "can not find class with name:"+className);
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
