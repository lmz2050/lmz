package cn.lmz.mike.common.base;

import java.util.HashMap;
import java.util.Map;

public class MapU {

    public static Map getMap(Object... objects){
        Map params = null;
        if(objects!=null&&objects.length>0){
            params = new HashMap<String,Object>();
            for(int i=1;i<objects.length;i+=2){
                params.put(objects[i-1], objects[i]);
            }
        }
        return params;
    }

}
