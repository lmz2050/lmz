package cn.lmz.mike.common.io;

import com.xiaoleilu.hutool.lang.ClassScaner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScanU {

    public static Map<String,Class<?>> getClassNamePathMap(String pkgName){
        Map<String,Class<?>> pkgMap = new HashMap<String,Class<?>>();
        Set<Class<?>> cset =  ClassScaner.scanPackage(pkgName);
        for(Class<?> c:cset){
            pkgMap.put(c.getSimpleName(),c);
        }
        return pkgMap;
    }

    public static void main(String[] args){

       Set<Class<?>> cset =  ClassScaner.scanPackage("java");

       for(Class<?> c:cset){
           //System.out.println(c.getName());
       }


       String s = System.getProperty("java.class.path");
        System.out.println(s);

    }

}
