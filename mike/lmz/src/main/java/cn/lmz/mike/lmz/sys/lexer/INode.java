package cn.lmz.mike.lmz.sys.lexer;


import cn.lmz.mike.lmz.sys.context.Context;

public interface INode {

	public String toString(Context ctx);
	public String getCodeStr();
	
}
