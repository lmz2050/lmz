package cn.lmz.mike.lmz.sys.node;


import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.lexer.ICodeList;
import cn.lmz.mike.lmz.sys.lexer.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ANode implements INode {

	private static final Logger log = LoggerFactory.getLogger(ANode.class);

	protected Object codeObj;
	protected String codeStr;
	protected Context ctx = null;
	
	public ANode(Context ctx){
		this.ctx = ctx;
	}
	
	public Object runNode(Context ctx) throws Exception{
		ctx.push(this);
		if(this.codeStr!=null&&this.codeStr.trim().length()>0){
			log.trace("RUN:"+this.codeStr);
			log.trace("RUN:"+this.toString(ctx));
		}
		Object v = execute(ctx);
		if(this.codeStr!=null&&this.codeStr.trim().length()>0&&v!=null){
			log.trace("==》"+v);
		}
		ctx.pop();
		return v;
	}

	protected abstract Object execute(Context ctx)  throws Exception ;

	public String getCode(){
		if(codeObj==null) return null;
		return codeObj.toString();
	}
	
	public String getCode(Context ctx){
		if(codeObj==null) return null;
		if(codeObj instanceof INode){
			INode n = (INode) codeObj;
			return n.toString(ctx);
		}
		return codeObj.toString();
	}
	
	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]";
	}
	
	public Object interpret(ICodeList cl)  throws Exception{
		this.codeObj = cl;
		//O.pn(this.getClass().getSimpleName()+":"+cl.toString());
		return cl;
	};
	

	public Object interpret(ICode c)  throws Exception{
		this.codeObj = c;
		return c;
	}
	
	public String getCodeStr() {
		return codeStr;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}

	protected String getCodeStr(Code code){
		return code==null?"":code.getVal();
	}
	protected String getString(ANode node,Context ctx){
		return node==null?"":node.toString(ctx);
	}
	protected String getString(Code code,Context ctx){
		return code==null?"":code.toString(ctx);
	}

	public Context getCtx() {
		return ctx;
	}
	
	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
}
