package cn.lmz.mike.lmz.sys.util.param;


import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.Row;

public class ParamBean {

	private Row r;
	private Code nc;
	
	public ParamBean(Row r1, Code nc2) {
		this.r = r1;
		this.nc = nc2;
	}

	public Row getR() {
		return r;
	}

	public void setR(Row r) {
		this.r = r;
	}

	public Code getNc() {
		return nc;
	}

	public void setNc(Code nc) {
		this.nc = nc;
	}

}
