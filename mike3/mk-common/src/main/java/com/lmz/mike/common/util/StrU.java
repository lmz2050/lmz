package com.lmz.mike.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class StrU {

    public static String toString(Object val) {
        return JSON.toJSONString(val);
    }

    public static boolean isBlank(String b) {
        return StringUtils.isBlank(b);
    }

}
