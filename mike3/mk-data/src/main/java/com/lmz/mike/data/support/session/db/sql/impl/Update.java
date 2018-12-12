package com.lmz.mike.data.support.session.db.sql.impl;

import com.lmz.mike.data.support.bean.Data;
import com.lmz.mike.data.support.session.db.sql.common.LSql;
import com.lmz.mike.data.support.session.db.sql.common.Set;
import com.lmz.mike.data.support.session.db.sql.common.Where;

import java.util.List;

public class Update extends LSql {
	
	public Update(Class<?> c, Set set, Where where){
		init(c,set,where);
	}
	public Update(StringBuilder sb,List<Object> params){
		super(sb,params);
	}

	public void init(Class<?> c,Set set,Where where){

		this.sqlsb.append("update ").append(Data.getTab(c)).append(set.getSql());
		this.params.addAll(set.getParams());
		if(where!=null){
			sqlsb.append(where.getSql());
			this.params.addAll(where.getParams());
		}
	}

}
