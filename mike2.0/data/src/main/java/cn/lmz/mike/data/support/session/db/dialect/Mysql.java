package cn.lmz.mike.data.support.session.db.dialect;

import cn.lmz.mike.data.support.session.db.IDialect;
import cn.lmz.mike.data.support.page.Page;

import java.util.List;

public class Mysql implements IDialect{

    private static final String PAGE_SQL = " %s  limit ?,? ";

    public String getPageSql(List<Object> params,Page page)
    {
        params.add(page.getStartNum());
        params.add(page.getIntPageSize());
        return PAGE_SQL;
    }

    public String getCurrTimeSqlName()
    {
        return "now()";
    }
}
