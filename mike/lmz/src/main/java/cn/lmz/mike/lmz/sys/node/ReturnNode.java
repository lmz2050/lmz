package cn.lmz.mike.lmz.sys.node;


import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.lexer.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReturnNode extends ANode {

	private static final Logger log = LoggerFactory.getLogger(ReturnNode.class);
	private ANode node = null;

	public ReturnNode(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(Context ctx) throws Exception {
		// TODO Auto-generated method stub
		
		
		Object v = node.runNode(ctx);
		ctx.getCfg().put(Const.V_RETURN, v);
		
		log.debug(ctx.getRunCode()+"return "+v);
		
		return v;
	}
	
	public Object interpret(ICode c)  throws Exception{
		super.interpret(c);
		
		Row r = NodeExpression.getNextCodes(c);
		
		node = NodeExpression.interpret(ctx, r);
		
		return null;
	}

	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]"+getString(node,ctx);
	}
}
