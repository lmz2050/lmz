package com.lmz.mike.common;

import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M {

    public static boolean isEmpty(Object[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0 ? true : false;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().length() == 0 ? true : false;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0 ? true : false;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static void addArrayToList(List list, Object... objs) {
        if (objs != null && objs.length > 0) {
            for (Object o : objs) {
                list.add(o);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static String getMapString(Map map, String key) {
        return MapUtils.getString(map, key);
    }

    @SuppressWarnings("unchecked")
    public static Object getMapObject(Map map, String key) {
        return MapUtils.getObject(map, key);
    }

    @SuppressWarnings("unchecked")
    public static Map getParams(Object... objects) {
        Map params = new HashMap();
        if (objects != null && objects.length > 1) {
            for (int i = 1; i < objects.length; i += 2) {
                params.put(objects[i - 1], objects[i]);
            }
        }
        return params;
    }

}
