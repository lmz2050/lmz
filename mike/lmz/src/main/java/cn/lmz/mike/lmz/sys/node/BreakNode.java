package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BreakNode extends ANode {

	private static final Logger log = LoggerFactory.getLogger(BreakNode.class);

	public BreakNode(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(Context ctx) throws Exception {
		
		ctx.getCfg().put(Const.V_BREAK,true);
		
		log.debug(ctx.getRunCode()+"break");
		
		return null;
		
	}
	public Object interpret(ICode c)  throws Exception{
		super.interpret(c.getR());
		
		return c;
	}

	
	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]";
	}
}
