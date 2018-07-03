package cn.lmz.mike.data;

import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.support.session.ISession;
import cn.lmz.mike.data.support.page.Page;
import cn.lmz.mike.data.support.page.PageUtil;
import java.util.Map;

//bean层面基础操作
public interface IEntity extends ISession {

	DataBean create(DataBean b);
	int delete(DataBean b) ;
	int update(DataBean b, Map sets);

	PageUtil query(DataBean bean, Page page, String ord);
	int queryMaxId(DataBean b);
	int queryCount(DataBean bean);
	PageUtil query(String sql,Page page,Object... objs);

}
