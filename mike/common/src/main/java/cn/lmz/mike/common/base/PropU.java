package cn.lmz.mike.common.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import cn.lmz.mike.common.sec.SecurityU;


public class PropU {
	
	
	public static final Map<String,Map<String,String>> proMap = new HashMap<String,Map<String,String>>();
	
	public static Map<String,String> load(String pfile){
		InputStream in = null;
		Map<String,String> map = new HashMap<String,String>();
		try{
			Properties pro = new Properties();
			in = new BufferedInputStream (new FileInputStream(pfile));
			pro.load(in);
			boolean isde = encrypt(pro,map);			
			if(isde){
				FileOutputStream oi = new FileOutputStream(pfile);
				pro.store(oi, "updated by DE");
			}
			proMap.put(pfile, map);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				try{
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public static Map<String,String> getMap(String proname){
		if(proMap.containsKey(proname)){
			return proMap.get(proname);
		}else{			
			Map<String,String> map = load(proname);
			proMap.put(proname, map);
			return map;
		}
	}
	
	public static String getValue(String proname,String key){
		Map<String,String> map = getMap(proname);
		return map.get(key);
	}
	
	public static String[] getArrayParams(String proname,String key){
		String str = getValue(proname, key);
		if(str==null)str = key;
		String[] strs = str.split("\\|");
		return strs;
	}
	
	public static boolean encrypt(Properties pro,Map<String,String> map){
		boolean de=false;
		if(pro!=null){
			Iterator it = pro.keySet().iterator();
			while(it.hasNext()){
				String key= (String)it.next();
				
				String val = pro.getProperty(key);
				
				if(val!=null&&val.startsWith("{E}")){
					val = val.substring(3);
					val = SecurityU.en(val);
					pro.put(key, "{D}"+val);
					de=true;
				}
				map.put(key, val);
			}
		}
		return de;
	}
	
	public static boolean decrypt(String file){
		boolean de=false;
		try{
			Properties pro = new Properties();
			InputStream in = new BufferedInputStream (new FileInputStream(file));
			pro.load(in);
			if(pro!=null){
				Iterator it = pro.keySet().iterator();
				while(it.hasNext()){
					String key= (String)it.next();
					
					String val = pro.getProperty(key);
					
					if(val!=null&&val.startsWith("{D}")){
						val = val.substring(3);
						val = SecurityU.de(val);
						pro.put(key, "{E}"+val);
						de=true;
					}
				}
				FileOutputStream oi = new FileOutputStream(file);
				pro.store(oi, "updated by DE");
				oi.close();
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return de;
	}
	
	public static void main(String[] arsg){
		
		String file = "E:\\wkspace\\wk1\\lmz\\etc\\mail.properties";
		
		decrypt(file);
		
		
	}
	
	
}
