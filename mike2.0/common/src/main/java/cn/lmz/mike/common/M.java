package cn.lmz.mike.common;

import cn.lmz.mike.common.util.StrU;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M {

    public static final class string extends StrU {};
    public static final class array extends ArrayUtils{

        public static boolean isEmpty(Collection list){
            return list==null||list.isEmpty();
        }

        public static void addArrayToList(List list,Object[] params){
            if(params!=null&&params.length>0){
                for(Object o:params){
                    list.add(o);
                }
            }
        }

    }
    public static final class date extends DateUtils{};


    public static boolean isEmpty(Object obj){
        if(obj==null) return true;
        if(obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }
        if(obj instanceof Map){
            return ((Map) obj).isEmpty();
        }
        if(obj instanceof Object[]){
            return ((Object[]) obj).length==0?true:false;
        }
        if(obj instanceof String){
            return ((String) obj).trim().length()==0?true:false;
        }
        if(obj instanceof CharSequence){
            return ((CharSequence) obj).length()==0?true:false;
        }
        return false;
    }

    public static final class map{
        public static String getString(final Map map, final Object key){
            return MapUtils.getString(map,key);
        }
        public static Object getObject(final Map map, final Object key){
            return MapUtils.getObject(map,key);
        }
        public static Map getParams(Object...objects){
            Map params = new HashMap();
            if(objects!=null&&objects.length>1){
                for(int i=1;i<objects.length;i+=2){
                    params.put(objects[i-1], objects[i]);
                }
            }
            return params;
        }
    };

}
