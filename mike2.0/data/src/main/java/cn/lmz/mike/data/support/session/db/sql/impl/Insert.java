package cn.lmz.mike.data.support.session.db.sql.impl;

import cn.lmz.mike.common.M;
import cn.lmz.mike.data.exception.DataException;
import cn.lmz.mike.data.support.session.db.sql.comon.LSql;
import cn.lmz.mike.data.support.bean.Data;

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
		if(M.array.isEmpty(keys)||M.array.isEmpty(vals)){
			throw new DataException(" insert - value size = 0");
		}
		if(vals.length<keys.length){
			throw new DataException(" insert - value size < key size ");
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
