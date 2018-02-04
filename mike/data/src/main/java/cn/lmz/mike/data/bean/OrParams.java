package cn.lmz.mike.data.bean;

import cn.lmz.mike.data.util.LmzU;

import java.io.Serializable;
import java.util.*;


public class OrParams implements Serializable,Cloneable{

	private List<Map<String,String>> mlist = new ArrayList<Map<String,String>>();

	public OrParams(){}

	public OrParams set(String key,String val){
		Map<String,String> m = new HashMap<String,String>();
		m.put(key,val);
		mlist.add(m);
		return this;
	}
	
	public String toString(){
		int i=0;
		String sql="";
		for(Map<String,String> m:mlist){
			for(Map.Entry<String,String> en:m.entrySet()){
				String key = en.getKey();
				Object val = en.getValue();
				val = LmzU.getSqlString(val);
				if(i>0){
					sql+=" or ";
				}
				sql+=key+"="+val;

				i++;
			}
		}
		return sql;
	}
}
