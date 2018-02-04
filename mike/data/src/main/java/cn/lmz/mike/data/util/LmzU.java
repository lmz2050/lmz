package cn.lmz.mike.data.util;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.bean.DataBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class LmzU {

	
	public static Object getBean(String cls) throws LMZException {
		Object o = null;
		try{
			Class<?> c = Class.forName(cls);
			o = c.newInstance();
		}catch(Exception e){
			throw new LMZException("LmzU","getBean(String cls)" , e, cls);
		}
		return o;
	}
	
	public static String getRootPath(){
		ClassLoader l = LmzU.class.getClassLoader();
		String classPath = l.getResource("./").getPath();
		String root = classPath.replace("WEB-INF/classes/", "");
		return root;
	}
	
	public static DataBean clone(DataBean b){
		if(b!=null){
			return (DataBean)b.clone();
		}
		return null;
	}
	
	public static int getInt(Object o){
		int i=0;
		if(o!=null){
			if(!StrU.isBlank(o)){
				try{
					i = new Integer(String.valueOf(o));
				}catch(Exception e){
					e.printStackTrace();
					O.info("error int str:"+o);
				}
			}
		}
		return i;
	}
	
	public static Integer getInteger(Object o){
		Integer i = null;
		if(o!=null){
			if(!StrU.isBlank(o)){
				try{
					i = new Integer(String.valueOf(o));
				}catch(Exception e){
					e.printStackTrace();
					O.info("error int str:"+o);
				}
			}
		}
		return i;		
	}
	
	@SuppressWarnings("unchecked")
	public static List getNumList(List list,int num){
		if(list!=null&&list.size()>num){
			List rlist = new ArrayList();
			for(int i=0;i<num;i++){
				rlist.add(list.get(i));
			}
			return rlist;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static Map getParams(Object...objects){
		Map params = new HashMap();
		if(objects!=null&&objects.length>1){
			for(int i=1;i<objects.length;i+=2){
				params.put(objects[i-1], objects[i]);
			}			
		}
		return params;
	}
	
	public static String getSqlString(Object val){
		if(val==null) return "''";
		if(val instanceof String) return "'"+val.toString()+"'";
		return val.toString();
	}
	
	
/*
	public static String getUTF8String(String valueStr) throws Exception {
		if(valueStr==null)return "";
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
		return valueStr;
	}
	public static String getUTF8String(Map params,String valueStr) throws Exception {
		String value = (String)params.get(valueStr);
		if(value==null)return "";
		return value;
	}	
	*/
	public static Object getMapVal(Map m,String key,Object def){
		Object v = m.get(key);
		if(v==null) return def;
		return v;
	}
	public static String getMapValString(Map m,String key,String def){
		Object v = m.get(key);
		if(v==null) return def;
		return v+"";
	}
	
	public static String getMapParam(Map param) throws Exception{
		String str="";
		if(param!=null&&param.size()>0){
			for (Iterator iter = param.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String val="";
				Object oval = param.get(key);
				if(oval instanceof String[]){
					String[] aval = (String[])oval;
					if(aval.length>0){
						val=aval[0];
					}
				}else{
					val=(String)oval;
				}
				str+=("".equals(str) ? "":"&") +key+"="+val;
			}
		}
		return str;
	}
}
