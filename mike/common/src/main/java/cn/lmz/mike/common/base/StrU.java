package cn.lmz.mike.common.base;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.V;
import cn.lmz.mike.common.json.JsonU;
import cn.lmz.mike.common.log.O;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class StrU extends StringUtils {

    public static void main(String[] args){

    }

    public static String toStr(Object o)
    {
        if (o == null) return "";
        if ((o instanceof List)) {
            return list2String((List)o);
        }
        if (o.getClass().isArray()) {
            return Arrays.deepToString((Object[])o);
        }
        try {
            return JsonU.obj2json(o);
        } catch (Exception e) {
            System.err.println(e.getMessage()+","+o);
        }
        return o.toString();
    }

    public static String list2String(List list){
        String re = "";
        if(list!=null&&list.size()>0){
            for(int i=0;i<list.size();i++){
                re+=list.get(i)+"\r\n";
            }
        }
        return re;
    }

    public static String[] getArray(String str,String split){
        if(str!=null){
            return str.split(split);
        }
        return new String[]{};
    }
    public static String[] getArray(String str){
        return getArray(str, "\\|");
    }

    public static String getErrorInfo(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

    public static String getRandomString(int count){
        return RandomStringUtils.random(count);
    }

    public static String getRoot(){
        String APPHOME=System.getProperty(V.APPHOME);
        if(isBlank(APPHOME)){
            APPHOME = StrU.class.getClassLoader().getResource("").getPath();
            System.setProperty(V.APPHOME,APPHOME);
        };
        APPHOME = APPHOME.replaceAll("\\\\","/");
        return APPHOME;
        /*
        String path = StrU.class.getClassLoader().getResource("").getPath();
        if(path.contains("/bin/")){
            path = path.replaceAll("/bin/", "/");
        }else if(path.contains("/lib/")){
            path = path.replaceAll("/lib/", "/");
        }else if(path.contains("/target/classes/")){
            path = path.replaceAll("/target/classes/", "/");
        }
        return path;
        */
    }

    public static String getWebRoot(){
        File cf = new File(StrU.class.getClassLoader().getResource("/").getPath());
        return cf.getParentFile().getParentFile().getAbsolutePath();
    }

    public static String getMethodString(Method method){
        String paramStr="";
        String modifiers=Modifier.toString(method.getModifiers());
        if(method.getParameterTypes().length>0) {
            for (Class<?> pcls : method.getParameterTypes()) {
                paramStr+= (MC.string.isEmpty(paramStr)?"":",")+pcls.getName();
            }
        }
        String methodStr = "==>"+modifiers+" "+method.getReturnType().getName()+" "+method.getName()+"("+paramStr+")";
        return methodStr;
    }
}
