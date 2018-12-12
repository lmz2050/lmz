package com.lmz.mike.data.config;

import com.lmz.mike.common.util.StrU;
import com.lmz.mike.data.IData;
import com.lmz.mike.data.annotation.LField;
import com.lmz.mike.data.annotation.Lbean;
import com.lmz.mike.data.support.bean.Data;
import com.lmz.mike.data.util.CUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class DataConfig implements InitializingBean {

    public static final String BASE_PACKAGE = "com.lmz";

    @Override
    public void afterPropertiesSet() throws Exception {
        reg(BASE_PACKAGE);
    }

    public static void reg(String pkg){
        log.info("init pkg:{}",pkg);
        List<Class<?>> clist = CUtil.getClassesByAnnotation(pkg, Lbean.class);
        if(clist!=null&&clist.size()>0){
            for(int i=0;i<clist.size();i++){
                Class<?> c = clist.get(i);
                String name = c.getSimpleName();
                Lbean lb = c.getAnnotation(Lbean.class);
                if(lb!=null&&!StrU.isBlank(lb.keyName())){
                    name=lb.keyName();
                }
                log.info(name+":"+c.getName());
                Data.getNamemap().put(name, c);
                initC(c);
            }
        }
        Data.getRegmap().put(pkg, "");

        List<Class<?>> ilist = CUtil.getClassesByInterface(pkg, IData.class);
        if(ilist!=null&&ilist.size()>0){
            for(int i=0;i<ilist.size();i++){
                Class<?> c = ilist.get(i);

                if(!c.isInterface()){
                    try{
                        IData d = (IData) c.newInstance();
                        d.reg();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
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
            log.error("initC error",e);
        }
    }

    public static void initC(Class<?> c){
        try {
            log.info("initC "+c.getSimpleName()+ " start ");
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
                                if(!StrU.isBlank(lf.fieldName())){
                                    fieldName=lf.fieldName();
                                }
                                if(!StrU.isBlank(lf.sql())){
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
            Data.getMap().put(c, m);
            Data.getMapAll().put(c, mall);
            log.info(select);
            Data.getSelectMap().put(c, select);
            log.trace("end p size =  "+m.size());
        } catch (Exception e) {
            log.error("reg error",e);
        }
    }
}
