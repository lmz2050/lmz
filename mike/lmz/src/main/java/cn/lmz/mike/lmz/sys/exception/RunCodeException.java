package cn.lmz.mike.lmz.sys.exception;


import cn.lmz.mike.lmz.sys.lexer.INode;

public class RunCodeException extends LException {

	private static final long serialVersionUID = 1L;
	public static final String ERR_RUN="运行异常";
	
	public static final String ERR_RUN_100="not boolean val ";
	
	public RunCodeException(String codestr,String msg) {
		super(ERR_RUN,"["+codestr+"]",msg);
	}
	public RunCodeException(String codestr,String msg,Exception e) {
		super(ERR_RUN,"["+codestr+"]",msg,e);
	}
	
	public RunCodeException(INode n, String msg) {
		super(ERR_RUN, n, msg);
	}
	public RunCodeException(INode n, String msg, Exception e) {
		super(ERR_RUN, n, msg, e);
	}

}
