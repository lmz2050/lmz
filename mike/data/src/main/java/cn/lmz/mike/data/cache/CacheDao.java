package cn.lmz.mike.data.cache;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.BeanUtil;
import cn.lmz.mike.data.Dao;
import cn.lmz.mike.data.Data;
import cn.lmz.mike.data.DataU;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.cache.base.CacheU;
import cn.lmz.mike.data.support.LDao;
import net.sf.ehcache.Cache;

import org.springframework.stereotype.Repository;

@Repository
public class CacheDao extends Dao implements LDao {
	
	public DataBean create(DataBean b) throws LMZException {
		try{
			O.pNull(b,"CacheDao.create(DataBean b) params is null");
			Cache cache = CacheU.getCache(b.getClz());
			CacheU.addToCache(cache, b);
			return b;
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"create(DataBean b)",e,b);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delete(DataBean b)  throws LMZException{
		try{
			Cache cache = CacheU.getCache(b.getClz());
			if(b.containsKey(ID)){
				DataBean bb = (DataBean)CacheU.getValue(cache, b.get(ID));
				CacheU.delete(cache,bb);
			}else{
				List list = this.search(b.getClz(), b);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						DataBean bb = (DataBean)list.get(i);
						CacheU.delete(cache,bb);
					}
				}
			}
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"delete(Class<?> c,Map params)",e,b.getClz(),b);
		}
	}

	@SuppressWarnings("unchecked")
	public void update(DataBean b,Map sets) throws LMZException{
		try{
			Cache cache = CacheU.getCache(b.getClz());
			if(b.containsKey(ID)){
				DataBean bb = (DataBean)CacheU.getValue(cache, b.get(ID));
				BeanUtil.setBean(bb, sets);
				CacheU.update(cache,bb);
			}else{
				List list = this.search(b.getClz(), b);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						DataBean bb = (DataBean)list.get(i);
						BeanUtil.setBean(bb, sets);
						CacheU.update(cache,bb);
					}
				}
			}
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"update(Class<?> c,Map sets,Map params)",e,b.getClz(),sets,b);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public PageUtil searchMap(DataBean b, Page page, String ord) throws LMZException {
		PageUtil pu = new PageUtil();
		try{
			List rlist = search(b.getClz(),b);
			pu.setList(rlist);
			
			if(ord!=null){
				DataU.doOrd(rlist,ord);
			}	
			if(page!=null){
				pu = DataU.doPage(rlist,page);
			}
			return pu;
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"search(Class<?> c,Map params) ",e,b.getClz(),b);
		}
	}

	@SuppressWarnings("unchecked")
	public List search(Class<?> c, Map params) throws LMZException {
		try{
			O.info("Object search(Calss c,map params): ");
			List rlist = new ArrayList();
			Cache cache = CacheU.getCache(c);
			if(params==null||params.size()==0){
				if(cache.getKeys().size()>0){   			
					for(int i=0;i<cache.getKeys().size();i++){
						Object oo = CacheU.getOValue(cache,i);
						rlist.add(oo);
					}
		    	}				
			}else{
				if(cache.getKeys().size()>0){
	    			Object o = CacheU.getOValue(cache,0);
	    			if(o!=null){
		    			Map<String,PropertyDescriptor> map = Data.getBeanInfo(o.getClass());
		    			for(int i=0;i<cache.getKeys().size();i++){
		    				Object oo = CacheU.getOValue(cache,i);
		    				if(BeanUtil.checkBean(oo, params,map,false)){
		    					rlist.add(oo);
		    				}
		    			}
	    			}
		    	}
			}
			return rlist;
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"search(Class<?> c,Map params) ",e,c,params);
		}
	}
	
	@SuppressWarnings("unchecked")
	public int searchMax(String atr,DataBean b) throws LMZException{
		try{
			List list = search(b.getClz(),b);
			int id = 0;
			for(int i=0;i<list.size();i++){
				DataBean bb = (DataBean)list.get(i);
				int idi = new Integer(bb.getId());
				if(idi>id){
					id=idi;
				}
			}
			return id;
		}catch(Exception e){
			e.printStackTrace();
			throw new LMZException(this.getClass().getName(),"getMaxID: ",e,b.getClz());
		}
	}	
	
	@SuppressWarnings("unchecked")
	public int searchCount(DataBean b) throws LMZException{
		try{
			return this.search(b.getClz(),b).size();
		}catch(Exception e){
			e.printStackTrace();
			throw new LMZException(this.getClass().getName(),"getCount : ",e,b.getClz(),b);
		}
	}

	@Override
	public void execute(String sql, Object... objs) throws LMZException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageUtil searchMap(String sql, Page page, Object... objs)
			throws LMZException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer searchInt(String sql, Object... objs) throws LMZException {
		// TODO Auto-generated method stub
		return null;
	}


}
