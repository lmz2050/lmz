package cn.lmz.mike.data.support;


import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;

public interface IService extends LDao{

	public PageUtil searchObj(DataBean b, Page page, String ord) throws LMZException;
	public int searchMaxID(DataBean b) throws LMZException;	
	public PageUtil searchObj(Class<?> c, String sql, Page page, Object... objs) throws LMZException;

	public String executeR(String sql, Object... objs) throws LMZException;
	public void sync(Class<?> c) throws LMZException;
	
	public IEntity getEn();
}
