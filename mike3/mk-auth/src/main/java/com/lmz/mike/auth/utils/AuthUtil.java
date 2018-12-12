package com.lmz.mike.auth.utils;

import com.lmz.mike.auth.constant.AuthConst;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/3 0:37
 * 4
 */
public class AuthUtil {

    public static String getRedisKey(String key){
        return AuthConst.REDIS_AUTH_PRE_FIX+key;
    }

}
