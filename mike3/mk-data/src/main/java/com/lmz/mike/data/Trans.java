package com.lmz.mike.data;


import com.lmz.mike.data.exception.DbException;
import com.lmz.mike.data.support.session.db.sql.common.LSql;
import com.lmz.mike.data.support.session.db.sql.impl.Delete;
import com.lmz.mike.data.support.session.db.sql.impl.Insert;
import com.lmz.mike.data.support.session.db.sql.impl.Update;

public interface Trans{
	void insert(Insert insert) throws DbException;
	boolean delete(Delete delete)  throws DbException;
	boolean update(Update update)  throws DbException;
	void execute(LSql lq) throws DbException;
}
