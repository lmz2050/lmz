package com.lmz.mike.data.exception;

import com.lmz.mike.common.code.ErrorCode;
import com.lmz.mike.common.exception.ErrorCodeException;
import com.lmz.mike.data.support.session.db.sql.common.LSql;
import com.lmz.mike.data.support.session.db.sql.impl.Insert;

public class DbException extends ErrorCodeException {

    private String sql;
    private Object[] params;
    public DbException(){
        super(ErrorCode.DATA_ERROR);
    }

    public DbException(String message){
        super(ErrorCode.DATA_ERROR.getCode(),ErrorCode.DATA_ERROR.getMsg()+">>"+message);
    }
    public DbException(String sql,Object[] objs,Exception e){
        super(ErrorCode.DATA_ERROR,e);
        this.sql = sql;
        this.params = objs;
    }

    public DbException(LSql lsql, Exception e) {
        super(ErrorCode.DATA_ERROR,e);
        this.sql = lsql.getSql();
        this.params = lsql.getParams().toArray();
    }


//    @Override
//    public String getMessage() {
//        return new StringBuilder().append("[").append(sql).append("]-[").append(StrU.toString(params)).append("]>>").append(super.getMessage()).toString();
//    }
}
