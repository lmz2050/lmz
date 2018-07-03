package cn.lmz.mike.data.support.service;

import cn.lmz.mike.data.IEntity;
import cn.lmz.mike.data.IService;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.support.page.Page;
import cn.lmz.mike.data.support.page.PageUtil;
import cn.lmz.mike.data.support.session.db.sql.impl.Delete;
import cn.lmz.mike.data.support.session.db.sql.impl.Insert;
import cn.lmz.mike.data.support.session.db.sql.impl.Query;
import cn.lmz.mike.data.support.session.db.sql.impl.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("lService")
public class LService implements IService {
	@Resource
	protected IEntity en;

	@Override
	@Transactional
	public DataBean create(DataBean b) {
		return en.create(b);
	}

	@Override
	@Transactional
	public int delete(DataBean b) {
		return en.delete(b);
	}

	@Override
	@Transactional
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
	@Transactional
	public int insert(Insert insert) {
		return en.insert(insert);
	}

	@Override
	@Transactional
	public int delete(Delete delete) {
		return en.delete(delete);
	}

	@Override
	@Transactional
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
	@Transactional
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
