package cn.lmz.mike.data.support;

import cn.lmz.mike.common.M;
import cn.lmz.mike.data.IEntity;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.exception.DataException;
import cn.lmz.mike.data.support.session.ISession;
import cn.lmz.mike.data.support.session.db.dialect.DialectUtil;
import cn.lmz.mike.data.support.session.db.sql.comon.*;
import cn.lmz.mike.data.support.session.db.sql.impl.Delete;
import cn.lmz.mike.data.support.session.db.sql.impl.Insert;
import cn.lmz.mike.data.support.session.db.sql.impl.Query;
import cn.lmz.mike.data.support.session.db.sql.impl.Update;
import cn.lmz.mike.data.support.page.Page;
import cn.lmz.mike.data.support.page.PageUtil;
import cn.lmz.mike.data.util.IdU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Entity implements IEntity {

	private static final Logger log = LoggerFactory.getLogger(Entity.class);

	@Resource
	protected ISession session;
	@Resource
	protected DialectUtil dialectUtil;

	@Override
	public DataBean create(DataBean b) {
		String id = b.getId();
		if(M.string.isBlank(id)){
			//id = IdU.getId();
			id = new StringBuilder().append(queryMaxId(b)+1).toString();
			b.setId(id);
		}
		Insert insert = new Insert(b.getClz(),b);
		insert(insert);
		return  b;
	}

	@Override
	public int delete(DataBean b) throws DataException {
		Delete delete = new Delete(b.getClz(), Where.instance().addCondition(b));
		return delete(delete);
	}

	@Override
	public int update(DataBean b, Map sets) {
		Update update = new Update(b.getClz(), Set.instance(sets), Where.instance().addCondition(b));
		return update(update);
	}

	@Override
	public PageUtil query(DataBean b, Page page, String ord) {
		PageUtil re = new PageUtil();
		Query query = new Query(b.getClz(), Select.instance().addSelect(b.keySet()),Where.instance().addCondition(b), OrderBy.defaultOrderBy());
		String sql = query.getSql();
		List<Object> params = query.getParams();

		if(page!=null){
			int count = queryCount(b);
			page.init(count,page.getIntCurrentPage());
			re.setPage(page);
			String pageFormat = dialectUtil.getDialect().getPageSql(params,page);
			sql = String.format(pageFormat,sql);
			query = new Query(new StringBuilder(sql),params);
		}

		List list = query(query);
		re.setList(list);

		return re;
	}

	@Override
	public int queryMaxId(DataBean b) {
		Query qy = new Query(b.getClz(),Select.fun(Select.MAXID),Where.instance(),null);
		return (Integer) queryObj(b.getClz(),qy);
	}

	@Override
	public int queryCount(DataBean b) {
		Query qy = new Query(b.getClz(),Select.fun(Select.FUN_COUNT),Where.instance().addCondition(b),null);
		return (Integer) queryObj(Integer.class,qy);
	}

	@Override
	public int insert(Insert insert) {
		return session.insert(insert);
	}

	@Override
	public int delete(Delete delete) {
		return session.delete(delete);
	}

	@Override
	public int update(Update update) {
		return session.update(update);
	}

	@Override
	public List query(Query query) {
		return session.query(query);
	}

	@Override
	public <T> T queryObj(Class<? extends T> clz, Query query) {
		return queryObj(clz,query.getSql(),query.getParams().toArray());
	}

	@Override
	public int execute(String sql, Object... objs) {
		return session.execute(sql,objs);
	}

	@Override
	public <T> T queryObj(Class<? extends T> clz, String sql, Object... objs) {
		return session.queryObj(clz,sql,objs);
	}

	@Override
	public List query(String sql, Object... objs) {
		return session.query(sql,objs);
	}

	@Override
	public PageUtil query(String sql,Page page,Object... objs) {
		PageUtil re = new PageUtil();
		List<Object> params = new ArrayList<Object>();
		M.array.addArrayToList(params,objs);

		if(page!=null){
			int count = queryObj(Integer.class,sql,objs);
			page.init(count,page.getIntCurrentPage());
			re.setPage(page);
			String pageFormat = dialectUtil.getDialect().getPageSql(params,page);
			sql = String.format(pageFormat,sql);
		}

		List list = query(sql,params.toArray());
		re.setList(list);
		return re;
	}
}
