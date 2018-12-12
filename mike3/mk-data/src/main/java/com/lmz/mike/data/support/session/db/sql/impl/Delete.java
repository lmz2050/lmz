package com.lmz.mike.data.support.session.db.sql.impl;

import com.lmz.mike.data.support.bean.Data;
import com.lmz.mike.data.support.session.db.sql.common.LSql;
import com.lmz.mike.data.support.session.db.sql.common.Where;

import java.util.List;


public class Delete extends LSql {

	public Delete(Class<?> c,Where where){
		init(c,where);
	}
	public Delete(StringBuilder sb,List<Object> params){
		super(sb,params);
	}

	public void init(Class<?> c,Where where){
		this.sqlsb.append("delete from ").append(Data.getTab(c));
		if(where!=null){
			sqlsb.append(where.getSql());
			this.params.addAll(where.getParams());
		}
	}

}
