package com.lmz.mike.data.support.session;


import com.lmz.mike.data.support.session.db.sql.impl.Delete;
import com.lmz.mike.data.support.session.db.sql.impl.Insert;
import com.lmz.mike.data.support.session.db.sql.impl.Query;
import com.lmz.mike.data.support.session.db.sql.impl.Update;

import java.util.List;

//持久层面的基础操作
public interface ISession{

    int insert(Insert insert);
    int delete(Delete delete);
    int update(Update update);
    List query(Query query);
    <T> T queryObj(Class<? extends T> clz, Query query);

    //仅db支持
    int execute(String sql, Object... objs);
    <T> T queryObj(Class<? extends T> clz, String sql, Object... objs);
    List query(String sql, Object... objs);
}
