package com.lmz.mike.data.support.session.db.sql.impl;


import com.lmz.mike.data.support.bean.Data;
import com.lmz.mike.data.support.session.db.sql.common.LSql;
import com.lmz.mike.data.support.session.db.sql.common.OrderBy;
import com.lmz.mike.data.support.session.db.sql.common.Select;
import com.lmz.mike.data.support.session.db.sql.common.Where;

import java.util.List;

public class Query extends LSql {

	public Query(Class<?> c, Select select, Where where, OrderBy orderBy){
		init(c,select,where,orderBy);
	}
	public Query(StringBuilder sb,List<Object> params){
		super(sb,params);
	}
	public void init(Class<?> c,Select select,Where where,OrderBy orderBy){

		this.sqlsb.append("select ");
		if(select!=null){
			sqlsb.append(select.getSql());
		}else{
			sqlsb.append(" * ");
		}
		sqlsb.append(" from ").append(Data.getTab(c)).append(" b ");

		if(where!=null){
			sqlsb.append(where.getSql());
			this.params.addAll(where.getParams());
		}

		if(orderBy!=null){
			this.sqlsb.append(orderBy.getSql());
		}
	}

}
