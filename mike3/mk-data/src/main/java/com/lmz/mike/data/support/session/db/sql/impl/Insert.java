package com.lmz.mike.data.support.session.db.sql.impl;

import com.lmz.mike.common.M;
import com.lmz.mike.data.exception.DbException;
import com.lmz.mike.data.support.bean.Data;
import com.lmz.mike.data.support.session.db.sql.common.LSql;

import java.util.List;
import java.util.Map;

public class Insert extends LSql {

	public Insert(Class<?> c,Map<String,Object> param){
		init(c,(String[])param.keySet().toArray(),param.values().toArray());
	}
	public Insert(StringBuilder sb,List<Object> params){
		super(sb,params);
	}
	
	public void init(Class<?> c, String[] keys,Object[] vals){
		if(M.isEmpty(keys)|| M.isEmpty(vals)){
			throw new DbException(" insert - value size = 0");
		}
		if(vals.length<keys.length){
			throw new DbException(" insert - value size < key size ");
		}
		StringBuilder keySb = new StringBuilder();
		StringBuilder valSb = new StringBuilder();
		for(int i=0;i<keys.length;i++){
			if(i>0){
				keySb.append(",");
				valSb.append(",");
			}
			keySb.append(keys[i]);
			valSb.append(" ? ");
			this.params.add(vals[i]);
		}
		this.sqlsb.append("insert into ").append(Data.getTab(c)).append(" ( ").append(keySb).append(" ) values ( ").append(valSb).append(" ) ").toString();
	}

}
