package cn.lmz.mike.admin.business.util;


import cn.lmz.mike.common.base.StrU;

public class AdmU {

    public static Integer getInteger(String str){
        if(StrU.isBlank(str)) return 0;
        return new Integer(str);
    }
    public static Integer addInteger(Integer d,String str){
        if(d==null) d = 0;
        return getInteger(str)+d;
    }



}
