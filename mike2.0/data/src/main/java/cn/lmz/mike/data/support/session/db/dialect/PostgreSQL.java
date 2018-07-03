package cn.lmz.mike.data.support.session.db.dialect;

import cn.lmz.mike.data.support.session.db.IDialect;
import cn.lmz.mike.data.support.page.Page;

import java.util.List;

public class PostgreSQL implements IDialect{

    private static final String PAGE_SQL = " %s  limit ? offset ? ";

    public String getPageSql(List<Object> params,Page page)
    {
        params.add(page.getIntPageSize());
        params.add(page.getStartNum());
        return PAGE_SQL;
    }

    public String getCurrTimeSqlName()
    {
        return "now()";
    }
}
