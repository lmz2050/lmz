package com.lmz.mike.data.support.session.db.dialect;

import com.lmz.mike.data.support.page.Page;
import com.lmz.mike.data.support.session.db.IDialect;

import java.util.List;

public class Mysql implements IDialect {

    private static final String PAGE_SQL = " %s  limit ?,? ";

    @Override
    public String getPageSql(List<Object> params, Page page)
    {
        params.add(page.getStartNum());
        params.add(page.getIntPageSize());
        return PAGE_SQL;
    }

    @Override
    public String getCurrTimeSqlName()
    {
        return "now()";
    }
}
