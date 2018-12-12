package com.lmz.mike.data.support.session.db.sql.common;


import com.lmz.mike.data.support.session.db.sql.ISqlExp;
import org.springframework.util.StringUtils;

public class LSqlExp implements ISqlExp {
    protected StringBuilder sqlsb;

    public LSqlExp(){
        sqlsb = new StringBuilder();
    }

    @Override
    public String getSql() {
        return sqlsb.toString();
    }

    protected StringBuilder addSql(String sql){
        if(!StringUtils.isEmpty(sql)){
            if(sqlsb.length()==0){
                sqlsb.append(getPreString());
            }else{
                sqlsb.append(getSplitString());
            }
            sqlsb.append(sql);
        }
        return sqlsb;
    }

    public String getPreString(){return "";};
    public String getSplitString(){return "";};
}
