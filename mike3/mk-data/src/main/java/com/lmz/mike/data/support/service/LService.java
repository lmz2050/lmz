package com.lmz.mike.data.support.service;
import com.lmz.mike.data.IEntity;
import com.lmz.mike.data.IService;
import com.lmz.mike.data.support.bean.DataBean;
import com.lmz.mike.data.support.page.Page;
import com.lmz.mike.data.support.page.PageUtil;
import com.lmz.mike.data.support.session.db.sql.impl.Delete;
import com.lmz.mike.data.support.session.db.sql.impl.Insert;
import com.lmz.mike.data.support.session.db.sql.impl.Query;
import com.lmz.mike.data.support.session.db.sql.impl.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("lService")
public class LService implements IService {

	@Resource
	protected IEntity en;

	@Override
	public DataBean create(DataBean b) {
		return en.create(b);
	}

	@Override
	public int delete(DataBean b) {
		return en.delete(b);
	}

	@Override
	public int update(DataBean b, Map sets) {
		return en.update(b,sets);
	}

	@Override
	public PageUtil query(DataBean bean, Page page, String ord) {
		return en.query(bean,page,ord);
	}

	@Override
	public int queryMaxId(DataBean b) {
		return en.queryMaxId(b);
	}

	@Override
	public int queryCount(DataBean bean) {
		return en.queryCount(bean);
	}

	@Override
	public PageUtil query(String sql, Page page, Object... objs) {
		return en.query(sql,page,objs);
	}

	@Override
	public <T> T queryObj(DataBean bean) {
		return en.queryObj(bean);
	}

	@Override
	public int insert(Insert insert) {
		return en.insert(insert);
	}

	@Override
	public int delete(Delete delete) {
		return en.delete(delete);
	}

	@Override
	public int update(Update update) {
		return en.update(update);
	}

	@Override
	public List query(Query query) {
		return en.query(query);
	}

	@Override
	public <T> T queryObj(Class<? extends T> clz, Query query) {
		return en.queryObj(clz,query);
	}

	@Override
	public int execute(String sql, Object... objs) {
		return en.execute(sql,objs);
	}

	@Override
	public <T> T queryObj(Class<? extends T> clz, String sql, Object... objs) {
		return en.queryObj(clz,sql,objs);
	}

	@Override
	public List query(String sql, Object... objs) {
		return en.query(sql,objs);
	}
}
