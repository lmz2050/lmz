package cn.lmz.mike.lmz.sys.lexer;

public interface ICode extends INode{

	public Code getNext();
	public Code getPrev();
	
	public Row getR();
}
