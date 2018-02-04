package cn.lmz.mike.common.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheU {

	public static final Map<String,Object> cacheMap = new HashMap<String,Object>();
	
	public synchronized static Object get(String key){
		return cacheMap.get(key);
	}
	
	public synchronized static void put(String key,Object val){
		cacheMap.put(key, val);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
