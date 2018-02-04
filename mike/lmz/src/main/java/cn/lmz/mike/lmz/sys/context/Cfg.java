package cn.lmz.mike.lmz.sys.context;

import java.util.HashMap;
import java.util.Map;

public class Cfg {

	private Map<String,Object> cfg = new HashMap<String,Object>();
	private static Context currCtx = null;
	
	private int i=0;
	private int j=0;
	
	
	
	public String getIKey(){
		i++;
		return "@i"+i+"@";
	}
	
	public String getJKey(){
		j++;
		return "@j"+j+"@";
	}
	
	public String put(Object value){
		String key = getJKey();
		//O.debug("--"+key+":"+value);
		cfg.put(key, value);
		return key;
	}
	
	public void put(String key,Object value){
		cfg.put(key, value);
	}
	
	public Object get(String key){
		return cfg.get(key);
	}

	public Map<String, Object> getCfg() {
		return cfg;
	}

	public void setCfg(Map<String, Object> cfg) {
		this.cfg = cfg;
	}

	public static Context getCurrCtx() {
		return currCtx;
	}

	public static void setCurrCtx(Context currCtxi) {
		currCtx = currCtxi;
	}
	

	public static String[] getArrayParams(String name){
		String str = (String)Cfg.getCurrCtx().get(name);
		if(str==null)str = name;
		String[] strs = str.split("\\|");
		return strs;	
	}
	public static String getStringParams(String name){
		String str = (String)Cfg.getCurrCtx().get(name);
		if(str==null)str = name;
		return str;
	}
	
}
