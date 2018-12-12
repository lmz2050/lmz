package com.lmz.mike.data.support.session.db.sql.common;

import com.lmz.mike.common.M;
import com.lmz.mike.data.exception.DbException;
import com.lmz.mike.data.support.session.db.sql.ISql;

import java.util.List;
import java.util.Map;

public class Where extends LSql implements ISql {

    public static Where instance(){
        return new Where();
    }
    public static Where defaultWhere(Map<String,Object> cons){
        return instance().addCondition(cons,"id");
    }

    @Override
    public String getPreString() {
        return " where ";
    }

    @Override
    public String getSplitString() {
        return " and ";
    }

    public Where addCondition(String sql) {
        addSql(sql);
        return this;
    }
    public Where addCondition(String sql,Object... objs) {
        addSql(sql);
        M.addArrayToList(params,objs);
        return this;
    }
    public Where addCondition(Map<String,Object> cons) {
        if(!M.isEmpty(cons)){
            for(Map.Entry<String,Object> en:cons.entrySet()){
                Object val = en.getValue();
                if(val!=null){
                    addSql(en.getKey()).append(" = ? ");
                    params.add(val);
                }else{
                    addSql(en.getKey()).append(" is null ");
                }
            }
        }
        return this;
    }
    public Where addCondition(Map<String,Object> cons,String keys) {
        if(M.isEmpty(keys)){
            throw new DbException(" where condition is empty ");
        }
        String[] keysa = keys.split(",");
        return addCondition(cons,keysa);
    }
    public Where addCondition(Map<String,Object> cons,String... keys) {
        if(M.isEmpty(keys)){
            throw new DbException(" where condition is empty ");
        }
        for(String key:keys){
            Object val = M.getMapObject(cons,key);
            if(val!=null){
                addSql(key).append(" = ? ");
                params.add(val);
            }else{
                addSql(key).append(" is null ");
            }
        }
        return this;
    }
    public Where addCondition(Object... params) {
        if(M.isEmpty(params)){
            throw new DbException(" where condition is empty ");
        }
        Map map = M.getParams(params);
        return addCondition(map);
    }

    @Override
    public String getSql() {
       return sqlsb.toString();
    }

    @Override
    public List<Object> getParams() {
        return params;
    }
}
