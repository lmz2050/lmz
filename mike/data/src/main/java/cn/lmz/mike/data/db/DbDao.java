package cn.lmz.mike.data.db;

import java.util.List;
import java.util.Map;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.Dao;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.db.sql.*;
import cn.lmz.mike.data.support.LDao;
import org.springframework.jdbc.core.JdbcOperations;

import org.springframework.stereotype.Repository;

@Repository
public class DbDao extends Dao implements LDao {
	
	protected JdbcOperations jdbcTemplate;
	protected LQuery lq = null;

	public DataBean create(DataBean b) throws LMZException {
		try{
			O.info("create: ");
			lq = new Insert(b.getClz(),b);
			jdbcTemplate.update(lq.getSql(),lq.getParam());		
			return b;
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"create(Object b)",e,lq,b);
		}
	}
	/*-----------------------------insert delete== S---------------------------------------*/
	@SuppressWarnings("unchecked")
	public void delete(DataBean b)  throws LMZException {
		try{
			lq = new Delete(b.getClz(),b);
			jdbcTemplate.update(lq.getSql(), lq.getParam());	
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"delete(Class<?> b,Map params)",e,lq,b.getClz(),b);
		}
	}
	/*-----------------------------insert delete== E---------------------------------------*/
	
	
	/*-----------------------------update == S---------------------------------------*/
	@SuppressWarnings("unchecked")
	public void update(DataBean b,Map sets) throws LMZException {
		try{
			lq = new Update(b.getClz(),sets,b);
			jdbcTemplate.update(lq.getSql(),lq.getParam());	
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"update(String tname,Map sets,Map params)",e,lq,b.getClz(),sets,b);
		}		
	}
	public void execute(String sql,Object...objs) throws LMZException {
		try{
			O.info(sql);
			if(objs==null){
				Object o =jdbcTemplate.update(sql);
				O.info(o);
			}else{
				Object o =jdbcTemplate.update(sql, objs);
				O.info(o);
			}
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"execute(String sql)",e,sql);
		}		
	}	
	/*-----------------------------update == E---------------------------------------*/
	
	
	/*-----------------------------search == S---------------------------------------*/
	@SuppressWarnings("unchecked")
	public PageUtil searchMap(DataBean b, Page page, String ord) throws LMZException {
		PageUtil re = new PageUtil();
		try{
			if(page!=null){
				lq = new Search(b.getClz(),b,Search.FUN_COUNT);
				int count = jdbcTemplate.queryForObject(lq.getSql(),lq.getParam(),Integer.class);
				//count=0;
				page.init(count,page.getIntCurrentPage());
				re.setPage(page);
			}
			lq = new Search(b.getClz(),b,page,ord);
			List list = jdbcTemplate.queryForList(lq.getSql(), lq.getParam());
			//list = new ArrayList();
			//list = DataU.createObjectList(b.getClz(), list);
			
			re.setList(list);
		}catch(Exception e){	
			throw new LMZException(this.getClass().getName(),"search(Class<?> c,Map params,Page page,Ord ord)",e,lq,b.getClz(),b,page,ord);
		}
		return re;
	}
	
	@SuppressWarnings("unchecked")
	public PageUtil searchMap(String sql,Page page,Object...objs) throws LMZException {
		PageUtil re = new PageUtil();
		O.info(sql);
		try{
			if(page!=null){
				int count = jdbcTemplate.queryForObject(String.format(COUNT_SQL, sql),objs,Integer.class);
				
				page.init(count,page.getIntCurrentPage());
				re.setPage(page);
				sql+=" LIMIT "+page.getStartNum()+" , "+page.getIntPageSize();
			}

			List list = jdbcTemplate.queryForList(sql, objs);
			re.setList(list);
		}catch(Exception e){	
			throw new LMZException(this.getClass().getName(),"searchMap(String sql,Object[] objs,Page page)",e,sql,objs,page);
		}
		return re;
	}

	public Integer searchInt(String sql,Object...objs) throws LMZException {
		try{
			return jdbcTemplate.queryForObject(sql, objs,Integer.class);
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"searchInt(String sql,Object...objs)",e,sql,objs);
		}
	}	
	/*-----------------------------search == E---------------------------------------*/

	@SuppressWarnings("unchecked")
	public int searchCount(DataBean b) throws LMZException {
		return search(b.getClz(), b,Search.FUN_COUNT);
	}

	@SuppressWarnings("unchecked")
	public int searchMax(String atr,DataBean b) throws LMZException {
		String key = String.format(MAX_SQL, atr);
		return search(b.getClz(),b,key);
	}
	
	@SuppressWarnings("unchecked")
	public int search(Class<?> c,Map params,String fun) throws LMZException {
		try{
			O.pNull(fun, " null param ");
			lq = new Search(c,params,fun);
			return jdbcTemplate.queryForObject(lq.getSql(),lq.getParam(),Integer.class);
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"search(Class<?> c,Map params,String fun)",e,lq,c,params,fun);
		}
	}
	
	public JdbcOperations getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
