package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;

public class ValueNode extends ANode {

	private Code c;
	
	public ValueNode(Context ctx) {
		super(ctx);
	}

	@Override
	protected Object execute(Context ctx) throws Exception {
		if(c==null) return null;
		return c.getV(ctx);
	}
	
	public Object interpret(ICode c)  throws Exception{
		super.interpret(c);
		this.c = (Code)c;
		return null;
	};


	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]"+c.toString(ctx);
	}

}
