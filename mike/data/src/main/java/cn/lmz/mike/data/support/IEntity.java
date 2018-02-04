package cn.lmz.mike.data.support;


import cn.lmz.mike.common.exception.LMZException;

public interface IEntity extends LDao{
	
	public void sync(Class<?> c) throws LMZException;
	
	public void setDao(LDao dao);
}
