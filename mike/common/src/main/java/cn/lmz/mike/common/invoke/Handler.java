package cn.lmz.mike.common.invoke;


import cn.lmz.mike.common.exception.LMZException;

public interface Handler {

	public void doresult(String k, Object v) throws LMZException;
}
