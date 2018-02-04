package cn.lmz.mike.data.db.sql;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.data.Data;

import java.util.Map;


public class Insert extends LQuery{

	public Insert(Class<?> c,Map<String,Object> param) throws Exception{
		init(c,param);
	}
	
	public void init(Class<?> c,Map<String,Object> param) throws Exception{
		if(param==null||param.size()==0){
			throw new LMZException(" insert - value size = 0");
		}
		String keystr="";
		String valstr="";
		for(Map.Entry<String, Object> entry:param.entrySet()){
			String key = entry.getKey();
			keystr+=(keystr.length()==0?"":",")+key;
			valstr+=(valstr.length()==0?"":",")+" ? ";
			this.vals.add(entry.getValue());
		}
		this.sql = "INSERT INTO " + Data.getTab(c) +" ( "+keystr+" ) VALUES ( "+valstr+" ) ";
	}

}
