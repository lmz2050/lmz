package cn.lmz.mike.data;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.support.IEntity;
import cn.lmz.mike.data.support.LDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class Entity implements IEntity {

	private static final Logger log = LoggerFactory.getLogger(Entity.class);

	protected LDao dao;

	@SuppressWarnings("unchecked")
	public void delete(DataBean b) throws LMZException {
		dao.delete(b);
	}
	@SuppressWarnings("unchecked")
	public int searchCount(DataBean b) throws LMZException {
		return dao.searchCount(b);
	}
	public int searchMax(String atr,DataBean b) throws LMZException {
		return dao.searchMax(atr,b);
	}
	@SuppressWarnings("unchecked")
	public PageUtil searchMap(DataBean b, Page page, String ord)throws LMZException {
		PageUtil pu = dao.searchMap(b,page, ord);
		log.info(pu.getList().size()+"");
		return pu;
	}
	public DataBean create(DataBean b) throws LMZException {
		return dao.create(b);
	}

	public void execute(String sql,Object...objs) throws LMZException {
		dao.execute(sql,objs);
	}
	@SuppressWarnings("unchecked")
	public void update(DataBean b, Map sets) throws LMZException {
		dao.update(b, sets);
	}
	
	@Override
	public PageUtil searchMap(String sql, Page page, Object...objs) throws LMZException {
		return dao.searchMap(sql,page,objs);
	}
	public Integer searchInt(String sql,Object...objs) throws LMZException {
		return dao.searchInt(sql, objs);
	}
	public LDao getDao() {
		return dao;
	}

	public void setDao(LDao dao) {
		this.dao = dao;
	}
	@Override
	public void sync(Class<?> c) throws LMZException {
		// TODO Auto-generated method stub
		
	}
}
