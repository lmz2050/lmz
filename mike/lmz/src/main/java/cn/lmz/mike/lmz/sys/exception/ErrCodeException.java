package cn.lmz.mike.lmz.sys.exception;


import cn.lmz.mike.lmz.sys.lexer.INode;

public class ErrCodeException extends LException {
	
	private static final long serialVersionUID = 1L;
	public static final String TYPE_SYNTAX="Syntax error";
	
	public static final String ERR_CODE_100="next code should be '(' ";
	public static final String ERR_CODE_200="missing ')'";
	public static final String ERR_CODE_300="missing run block ";
	public static final String ERR_CODE_400="run block is null";
	
	public ErrCodeException(INode n, String msg) {
		super(TYPE_SYNTAX, n, msg);
	}
	public ErrCodeException(INode n, String msg, Exception e) {
		super(TYPE_SYNTAX, n, msg, e);
	}

}
