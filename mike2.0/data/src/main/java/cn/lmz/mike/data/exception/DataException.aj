package cn.lmz.mike.data.exception;

import cn.lmz.mike.common.M;
import cn.lmz.mike.common.code.ErrorCode;
import cn.lmz.mike.common.exception.ErrorCodeException;
import cn.lmz.mike.data.support.session.db.sql.comon.LSql;

public class DataException extends ErrorCodeException{

    private String sql;
    private Object[] params;
    public DataException(){
        super(ErrorCode.DATA_ERROR);
    }

    public DataException(String message){
        super(ErrorCode.DATA_ERROR.getCode(),ErrorCode.DATA_ERROR.getMsg()+">>"+message);
    }
    public DataException(String sql,Object[] objs,Exception e){
        super(ErrorCode.DATA_ERROR,e);
        this.sql = sql;
        this.params = objs;
    }
    public DataException(LSql lq,Exception e){
        super(ErrorCode.DATA_ERROR,e);
        if(lq!=null){
            this.sql = lq.getSql();
            if(lq.getParams()!=null){
                this.params = lq.getParams().toArray();
            }
        }
    }

    public String getMessage() {
        return new StringBuilder().append("[").append(sql).append("]-[").append(M.string.toString(params)).append("]>>").append(super.getMessage()).toString();
    }
}
