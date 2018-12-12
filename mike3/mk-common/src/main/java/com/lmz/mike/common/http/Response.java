package com.lmz.mike.common.http;

import com.lmz.mike.common.code.ErrorCode;
import com.lmz.mike.common.code.ICode;
import com.lmz.mike.common.constant.ComConst;

import java.util.HashMap;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/12 22:04
 * 4
 */
public class Response extends HashMap<String,Object>{

    public Response(String code,String msg){
        put(ComConst.RESPONSE_CODE,code);
        put(ComConst.RESPONSE_MSG,msg);
    }

    public Response(ICode code){
        put(ComConst.RESPONSE_CODE,code.getCode());
        put(ComConst.RESPONSE_MSG,code.getMsg());
    }

    public static Response SuccessRes(){
        return new Response(ErrorCode.SUCCESS);
    }

    public static Response ErrorRes(ICode code){
        return new Response(code);
    }



}
