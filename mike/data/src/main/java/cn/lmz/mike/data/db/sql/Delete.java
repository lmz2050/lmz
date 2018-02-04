package cn.lmz.mike.data.db.sql;

import cn.lmz.mike.data.Data;

import java.util.Map;


public class Delete extends LQuery {

	public Delete(Class<?> c,Map<String,Object> param) throws Exception{
		init(c,param);
	}

	public void init(Class<?> c,Map<String,Object> param) throws Exception{
		this.sql = "DELETE FROM "+ Data.getTab(c) + this.getWhere(param);
	}	
}
