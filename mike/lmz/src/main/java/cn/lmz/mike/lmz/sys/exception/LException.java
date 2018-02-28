package cn.lmz.mike.lmz.sys.exception;


import cn.lmz.mike.lmz.sys.lexer.INode;

public class LException extends Exception{

	public static final String ERR_SYNTAX="Syntax error";
	public static final String ERR_RUN="Run abnormity";

	private static final long serialVersionUID = 1L;
	
	public LException(String etype,String codestr,String msg) {
		super(etype+"["+codestr+"] :"+msg);
	}
	public LException(String etype,String codestr,String msg,Exception e) {
		super(etype+"["+codestr+"] :"+msg,e);
	}	
	public LException(String etype, INode n, String msg) {
		super(etype+"["+n.getCodeStr()+"]["+n.toString()+"] :"+msg);
	}
	
	public LException(String etype,INode n,String msg,Exception e) {
		super(etype+"["+n.getCodeStr()+"]["+n.toString()+"] :"+msg,e);
	}
	
}
