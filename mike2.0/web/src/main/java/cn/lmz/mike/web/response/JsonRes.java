package cn.lmz.mike.web.response;


import cn.lmz.mike.common.code.ErrorCode;
import cn.lmz.mike.common.code.ICode;

import java.util.HashMap;

public class JsonRes extends HashMap<String, Object>{

    public static JsonRes instance(){
        JsonRes res = new JsonRes();
        return res;
    }
    public static JsonRes instance(String code,String msg){
        JsonRes res = instance();
        res.put(ICode.KEY_CODE,code);
        res.put(ICode.KEY_MSG,msg);
        return res;
    }
    public static JsonRes instance(ICode code){
        return instance(code.getCode(),code.getMsg());
    }

    public JsonRes put(String key, Object object){
        super.put(key, object);
        return this;
    }

    public static JsonRes success(){
        return instance(ErrorCode.SUCCESS);
    }
    public static JsonRes success(String key,Object val){
        return instance(ErrorCode.SUCCESS).put(key,val);
    }
    public static JsonRes error(ICode code){
        return instance(code);
    }

}
