package cn.lmz.mike.data.support;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;

public interface LDao extends TransDao{
	
	public PageUtil searchMap(DataBean bean, Page page, String ord) throws LMZException;
	public int searchMax(String atr, DataBean bean) throws LMZException;
	public int searchCount(DataBean bean) throws LMZException;
	public PageUtil searchMap(String sql, Page page, Object... objs) throws LMZException;
	public Integer searchInt(String sql, Object... objs) throws LMZException;
	
	
	public final String COUNT_SQL=" SELECT COUNT(0) FROM (%s) as ctab ";
	public final String MAX_SQL = " ifnull(MAX( cast(%s as signed) ),0)  ";

}
