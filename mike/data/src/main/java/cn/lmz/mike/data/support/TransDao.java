package cn.lmz.mike.data.support;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.data.bean.DataBean;

import java.util.Map;


public interface TransDao{
	
	public <T extends DataBean> T create(T b) throws LMZException;
	public void delete(DataBean b)  throws LMZException;	
	public void update(DataBean b, Map sets) throws LMZException;
	public void execute(String sql, Object... objs) throws LMZException;
}
