package com.lmz.mike.data.support.session.db.dialect;


import com.lmz.mike.data.support.page.Page;
import com.lmz.mike.data.support.session.db.IDialect;

import java.util.List;

public class PostgreSQL implements IDialect {

    private static final String PAGE_SQL = " %s  limit ? offset ? ";

    @Override
    public String getPageSql(List<Object> params, Page page)
    {
        params.add(page.getIntPageSize());
        params.add(page.getStartNum());
        return PAGE_SQL;
    }

    @Override
    public String getCurrTimeSqlName()
    {
        return "now()";
    }
}
