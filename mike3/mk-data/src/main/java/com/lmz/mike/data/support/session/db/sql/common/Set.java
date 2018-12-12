package com.lmz.mike.data.support.session.db.sql.common;

import com.lmz.mike.common.M;
import com.lmz.mike.data.exception.DbException;

import java.util.Map;

public class Set extends LSql{

    public static Set instance(){
        return new Set();
    }
    public static Set instance(Map<String,Object> cons){
        return instance().addSet(cons);
    }
    @Override
    public String getPreString() {
        return " set ";
    }

    @Override
    public String getSplitString() {
        return " , ";
    }

    public Set addSSet(String sql) {
        addSql(sql);
        return this;
    }
    public Set addSet(String sql, Object... objs) {
        addSql(sql);
        M.addArrayToList(params,objs);
        return this;
    }
    public Set addSet(Map<String,Object> cons) {
        if(!M.isEmpty(cons)){
            for(Map.Entry<String,Object> en:cons.entrySet()){
                Object val = en.getValue();
                if(val!=null){
                    addSql(en.getKey()).append(" = ? ");
                    params.add(val);
                }else{
                    addSql(en.getKey()).append(" = null ");
                }
            }
        }
        return this;
    }
    public Set addSet(Map<String,Object> cons, String keys) {
        if(M.isEmpty(keys)){
            throw new DbException(" set value is empty ");
        }
        String[] keysa = keys.split(",");
        return addSet(cons,keysa);
    }
    public Set addSet(Map<String,Object> cons, String... keys) {
        if(M.isEmpty(keys)){
            throw new DbException(" set value is empty ");
        }
        for(String key:keys){
            Object val = M.getMapObject(cons,key);
            if(val!=null){
                addSql(key).append(" = ? ");
                params.add(val);
            }else{
                addSql(key).append(" = null ");
            }
        }
        return this;
    }
    public Set addSet(Object... params) {
        if(M.isEmpty(params)){
            throw new DbException(" set value is empty ");
        }
        Map map = M.getParams(params);
        return addSet(map);
    }

}
