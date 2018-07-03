package cn.lmz.mike.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class StrU extends StringUtils{

    public static String toString(Object val){
        return JSON.toJSONString(val);
    }


}
