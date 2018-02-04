package cn.lmz.mike.lmz.sys.expression;


import cn.lmz.mike.lmz.sys.context.Context;

public interface IExpression {

	public void interpret(Context ctx) throws Exception;
	
}
