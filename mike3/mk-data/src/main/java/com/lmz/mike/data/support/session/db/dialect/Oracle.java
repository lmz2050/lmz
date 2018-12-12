package com.lmz.mike.data.support.session.db.dialect;


import com.lmz.mike.data.support.page.Page;
import com.lmz.mike.data.support.session.db.IDialect;

import java.util.List;

public class Oracle implements IDialect {

    private static final String PAGE_SQL = "select * from ( select row_.*, rownum rownum_ from ( %s  ) row_ ) where rownum_ > ? and rownum_ <= ? ";

    @Override
    public String getPageSql(List<Object> params, Page page)
    {
        params.add(page.getStartNum());
        params.add(page.getStartNum()+page.getIntPageSize());
        return PAGE_SQL;
    }

    @Override
    public String getCurrTimeSqlName()
    {
        return "sysdate";
    }
}
