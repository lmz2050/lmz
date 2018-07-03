package cn.lmz.mike.data;

import cn.lmz.mike.data.exception.DataException;
import cn.lmz.mike.data.support.session.db.sql.comon.LSql;
import cn.lmz.mike.data.support.session.db.sql.impl.*;

public interface Trans{
	void insert(Insert insert) throws DataException;
	boolean delete(Delete delete)  throws DataException;
	boolean update(Update update)  throws DataException;
	void execute(LSql lq) throws DataException;
}
