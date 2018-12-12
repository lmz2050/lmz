package com.lmz.mike.data;

import com.lmz.mike.data.support.bean.DataBean;
import com.lmz.mike.data.support.page.Page;
import com.lmz.mike.data.support.page.PageUtil;
import com.lmz.mike.data.support.session.ISession;

import java.util.Map;

//bean层面基础操作
public interface IEntity extends ISession {

	DataBean create(DataBean b);
	int delete(DataBean b) ;
	int update(DataBean b, Map sets);

	PageUtil query(DataBean bean, Page page, String ord);
	int queryMaxId(DataBean b);
	int queryCount(DataBean bean);
	PageUtil query(String sql, Page page, Object... objs);
	<T> T queryObj(DataBean bean);

}
