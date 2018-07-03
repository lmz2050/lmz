package cn.lmz.mike.web.init;

import cn.lmz.mike.common.M;
import cn.lmz.mike.common.web.annotation.LField;
import cn.lmz.mike.common.web.annotation.Lbean;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class LBeanInitCache implements InitializingBean {

    private static final Map<Class<?>,Map<String,PropertyDescriptor>> map = new HashMap<Class<?>,Map<String,PropertyDescriptor>>();
    private static final Map<Class<?>,Map<String,PropertyDescriptor>> mapAll = new HashMap<Class<?>,Map<String,PropertyDescriptor>>();
    public static final Map<Class<?>,String> selectMap = new HashMap<Class<?>,String>();
    private static final Map<String,Class<?>> namemap = new HashMap<String,Class<?>>();

    public static void reg(String pkgName){
        Reflections reflections = new Reflections(pkgName);
        Set<Class<?>> clist = reflections.getTypesAnnotatedWith(Lbean.class);
        log.info("init class:");
        if(clist!=null&&clist.size()>0){
            for(Class<?> c:clist){
                String name = c.getSimpleName();
                Lbean lb = c.getAnnotation(Lbean.class);
                if(lb!=null&&!M.string.isBlank(lb.sname())){
                    name=lb.sname();
                }
                log.info("{}:{}",name,c.getName());
                namemap.put(name, c);
                initC(c);
            }
        }

    }

    public static Class<?> getClass(String name){
        return namemap.get(name);
    }

    public static Map<String,PropertyDescriptor> getBeanInfo(Class<?> c){
        return getBeanInfo(c,false);
    }
    public static Map<String,PropertyDescriptor> getBeanInfo(Class<?> c,boolean all){
        Map<Class<?>,Map<String,PropertyDescriptor>> mm;
        if(all){
            mm=mapAll;
        }else{
            mm=map;
        }
        if(mm.get(c)==null){
            initC(c);
            return mm.get(c);
        }
        return mm.get(c);
    }

    public static void init(String c){
        String [] ss = c.split(",");
        for(int i=0;i<ss.length;i++){
            initC(ss[i]);
        }
    }

    public static void initC(String name){
        try {
            Class<?> c = Class.forName(name);
            initC(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initC(Class<?> c){
        try {
            log.info("initC {} start ",c.getSimpleName());
            java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(c);
            Field[] fs = c.getDeclaredFields();
            Map<String,PropertyDescriptor> m = new HashMap<String,PropertyDescriptor>();
            String select="";
            Map<String,PropertyDescriptor> mall = new HashMap<String,PropertyDescriptor>();
            if(info!=null){
                PropertyDescriptor pd[] = info.getPropertyDescriptors();
                for(int i=0;i<pd.length;i++){
                    boolean addselect = false;
                    String fieldName = pd[i].getName();
                    if (fieldName != null && !fieldName.equals("class")) {
                        Field f = null;
                        for(int k=0;k<fs.length;k++){
                            if(fieldName.equals(fs[k].getName())){
                                f = fs[k];
                                break;
                            }
                        }
                        if(f!=null){
                            LField lf = f.getAnnotation(LField.class);
                            if(lf!=null){
                                if(!M.string.isBlank(lf.name())){
                                    fieldName=lf.name();
                                }
                                if(!M.string.isBlank(lf.sql())){
                                    String sql=" ( "+lf.sql()+" ) "+fieldName;
                                    select+="".equals(select)? sql:","+sql;
                                    addselect=true;
                                }
                                if(!lf.useForDb()){
                                    mall.put(fieldName, pd[i]);
                                    continue;
                                }
                            }
                        }
                        //O.pn("name = "+fieldName);
                        if(pd[i].getReadMethod()!=null){
                            //O.pn(" rm = "+pd[i].getReadMethod().getName());
                            pd[i].getReadMethod().setAccessible(true);
                        }
                        if(pd[i].getWriteMethod()!=null){
                            //O.pn(" wm = "+pd[i].getWriteMethod().getName());
                            pd[i].getWriteMethod().setAccessible(true);
                        }
                        if(!addselect){
                            select+="".equals(select)? fieldName:(","+fieldName);
                        }
                        m.put(fieldName, pd[i]);
                    }
                }
                mall.putAll(m);
            }
            map.put(c, m);
            mapAll.put(c, mall);
            log.info(select);
            selectMap.put(c, select);
            log.info("end p size =  "+m.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTab(Class<?> c){
        String tab=c.getSimpleName();
        Lbean b = c.getAnnotation(Lbean.class);
        if(b!=null&&!M.string.isBlank(b.name())){
            tab = b.name();
        }
        return tab;
    }
    public static String getOthField(Class<?> c){


        String tab=c.getSimpleName();
        Lbean b = c.getAnnotation(Lbean.class);
        if(b!=null&&!M.string.isBlank(b.name())){
            tab = b.name();
        }
        return tab;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        reg("com.lmz");
    }
}
