package cn.lmz.mike.data.cache.base;

import java.util.List;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.data.annotation.Ehcache;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.util.CUtil;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.data.util.define.DataSV;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheU{
	
	private static CacheManager cacheManager=null;
	private static String cacheLocation = null;
	public static final String ID="id";
	
	public static void main(String[] args){
		CacheU.init();
	}
	
	public static synchronized CacheManager getCacheManager(){
		if(cacheManager==null){
			cacheManager = new CacheManager(cacheLocation);
			init();
		}
		return cacheManager;
	}
	
	
	public static synchronized void init(){
		
		List<Class<?>> clist = CUtil.getClassesByAnnotation("com.lmz",Ehcache.class);
		for(Class<?> c:clist){
			if(!cacheManager.cacheExists(c.getName())){
				Cache cacheNew = new Cache(c.getName(),10000, true, true, 0, 0);
				cacheManager.addCache(cacheNew);
			}
		}
	}
	
	
	public static void clear(String name) throws Exception{
		O.info("clear cache "+ name+"...");
		Cache cache = getCache(name);
		cache.removeAll();
	}
	public static void clear(Class<?> c) throws Exception{
		clear(c.getName());
	}	
	public static void clearAll() throws Exception{
		O.info("clearALl..");
		String [] cnames = getCacheManager().getCacheNames();
		for(int i=0;i<cnames.length;i++){
			clear(cnames[i]);
		}
	}

	public static void flush(String name) throws Exception{
		O.info("flush cache "+ name+"...");
		Cache cache = getCache(name);
		cache.flush();
	}
	public static void flush() throws Exception{
		O.info("flushAll..");
		String [] cnames = getCacheManager().getCacheNames();
		for(int i=0;i<cnames.length;i++){
			flush(cnames[i]);
		}
	}
	public static void flush(Class<?> c) throws Exception{
		flush(c.getName());
	}
	
	
	@SuppressWarnings("unchecked")
	public static void init(List list,Class<?> c) throws Exception{
		if(list!=null&&list.size()>0){
			Cache cache = getCache(c);
			for(int i=0;i<list.size();i++){
				DataBean b = (DataBean)list.get(i);
				addToCache(cache,b);
			}
		}
	}
	public static void addToCache(Cache cache,DataBean b) throws Exception{
		cache.put(new Element(b.getString(ID),b));
		flush(b.getClass());
		SyncU.addCync(b, DataSV.INSERT);
	}
	public static void delete(Cache cache,DataBean b) throws Exception {
		cache.remove(b.getId());
		flush(b.getClass());
		SyncU.addCync(b, DataSV.DELETE);
	}	
	public static void update(Cache cache, DataBean b) throws Exception {
		cache.put(new Element(b.getId(),b));
		flush(b.getClass());
		SyncU.addCync(b, DataSV.UPDATE);
	}
	
	public static Object getOValue(Cache cache,int i) throws Exception{
		Object key = cache.getKeys().get(i);
		return getValue(cache,key);
	}
	public static Object getValue(String cname,Object key) throws Exception{
		Cache cache = getCache(cname);
		return getValue(cache,key);
	}	
	public static Object getValue(Class<?> c,Object key) throws Exception{
		Cache cache = getCache(c);
		return getValue(cache,key);
	}	
	public static Object getValue(Cache cache,Object key) throws Exception{
		Element e = cache.get(key);
		if(e!=null){
			return LmzU.clone((DataBean)e.getObjectValue());
		}
		return null;
	}


	public static Cache getCache(Class<?> c) throws Exception{
		O.pNull(c,"getCache(Class<?> c),c= null ");
		return getCache(c.getName());
	}
	public static Cache getCache(String name) throws Exception{
		Cache cache = getCacheManager().getCache(name);
		O.pNull(cache,"get null cache with name:"+name);
		return cache;
	}
	
	public static void showCache(String name) throws Exception{
		Cache cache = getCache(name);
		if(cache!=null){
			O.info(cache.toString());
		}else{
			O.info("null");
		}
		
	}

	public static String getCacheLocation() {
		return cacheLocation;
	}

	public static void setCacheLocation(String cacheLocation) {
		CacheU.cacheLocation = cacheLocation;
	}
}
