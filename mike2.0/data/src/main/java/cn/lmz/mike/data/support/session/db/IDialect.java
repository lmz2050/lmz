package cn.lmz.mike.data.support.session.db;

import cn.lmz.mike.data.support.page.Page;

import java.util.List;

public interface IDialect {

    String getCurrTimeSqlName();
    String getPageSql(List<Object> params, Page page);
}
