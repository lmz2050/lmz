package com.lmz.mike.data.support.session.db.sql;

import java.util.List;

public interface ISql extends ISqlExp {
    public List<Object> getParams();
}
