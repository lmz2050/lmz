package cn.lmz.mike.data;

import cn.lmz.mike.common.invoke.Handler;
import cn.lmz.mike.common.invoke.InvokeBean;
import cn.lmz.mike.data.util.LmzU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

public class BeanUtil {

    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

    public static void handBean(Object bean, Handler hander) {
        try {
            log.trace("handBean - " + bean.getClass().getName() + " : ");
            Map<String, PropertyDescriptor> map = Data.getBeanInfo(bean.getClass());
            for (Map.Entry<String, PropertyDescriptor> entry : map.entrySet()) {
                PropertyDescriptor mb = entry.getValue();
                if (mb != null && mb.getReadMethod() != null) {
                    Object value = mb.getReadMethod().invoke(bean);
                    log.trace("  - " + mb.getReadMethod().getName() + " [" + entry.getKey() + "," + value + "]");
                    hander.doresult(entry.getKey(), value);
                }
            }
            log.trace("handBean - end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> beanToMap(Object bean) {
        return beanToMap(bean, true);
    }

    public static Map<String, Object> beanToMap(Object bean, boolean withNullVal) {
        if (bean == null) {
            return null;
        }
        Map<String, Object> bmap = new HashMap<String, Object>();
        try {
            log.trace("handBean - " + bean.getClass().getName() + " : ");
            Map<String, PropertyDescriptor> map = Data.getBeanInfo(bean.getClass());
            for (Map.Entry<String, PropertyDescriptor> entry : map.entrySet()) {
                PropertyDescriptor mb = entry.getValue();
                if (mb != null && mb.getReadMethod() != null) {
                    Object value = mb.getReadMethod().invoke(bean);
                    log.trace("  - " + mb.getReadMethod().getName() + " [" + entry.getKey() + "," + value + "]");
                    if (withNullVal || value != null) {
                        bmap.put(entry.getKey(), value);
                    }
                }
            }
            log.trace("handBean - end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmap;
    }

    public static Object createBean(Class<?> c, Map<String, Object> params) {
        try {
            log.trace("createBean: " + c.getName());
            Object a = c.newInstance();
            return setBean(a, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object setBean(Object bean, Map<String, Object> params) {
        try {
            log.trace("setBean - " + bean.getClass().getName() + " : ");
            Map<String, PropertyDescriptor> map = Data.getBeanInfo(bean.getClass(), true);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                PropertyDescriptor mb = map.get(entry.getKey());
                if (mb != null && mb.getWriteMethod() != null) {
                    if (entry.getValue() != null || (entry.getValue() == null && !mb.getPropertyType().isPrimitive())) {
                        log.info("  - " + mb.getWriteMethod().getName() + " [" + entry.getKey() + "," + entry.getValue() + "]");
                        if (mb.getPropertyType().equals(Integer.class)) {
                            mb.getWriteMethod().invoke(bean, LmzU.getInteger(entry.getValue()));
                        } else {
                            mb.getWriteMethod().invoke(bean, entry.getValue());
                        }
                    } else {

                    }
                }
            }
            log.trace("setBean - end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    @SuppressWarnings("unchecked")
    public static List searchBean(List list, Map<String, Object> params, boolean isLike) {
        log.trace("searchBean - start");
        if (params == null || params.size() == 0) {
            return list;
        } else {
            try {
                if (list != null && list.size() > 0) {
                    List rlist = new ArrayList();
                    if (params != null) {
                        Map<String, PropertyDescriptor> map = Data.getBeanInfo(list.get(0).getClass());

                        for (int i = 0; i < list.size(); i++) {
                            if (checkBean(list.get(i), params, map, isLike)) {
                                rlist.add(list.get(i));
                            }
                        }

                    } else {
                        return list;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static boolean checkBean(Object bean, Map<String, Object> params, Map<String, PropertyDescriptor> map, boolean isLike) throws Exception {
        if (bean == null) {
            return false;
        } else {
            if (params == null || params.size() == 0) {
                return true;
            } else {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    Method rMethod = map.get(entry.getKey()).getReadMethod();
                    if (rMethod != null) {
                        Object o = rMethod.invoke(bean);
                        if (isLike) {
                            if (!likes(o, entry.getValue())) {
                                return false;
                            }
                        } else {
                            if (!equals(o, entry.getValue())) {
                                return false;
                            }
                        }
                    } else {
                        continue;
                    }
                }
                return true;
            }
        }
    }

    public static boolean equals(Object a, Object b) {
        if (a == null || a.getClass().isPrimitive()) {
            return a == b;
        } else {
            return a.equals(b);
        }
    }

    public static boolean likes(Object a, Object b) {
        if (a != null && (a instanceof String) && b != null && (b instanceof String)) {
            return ((String) b).contains((String) a);
        }
        return false;
    }

    public static boolean containAttr(Class<?> c, String atr) {
        Map<String, PropertyDescriptor> map = Data.getBeanInfo(c);
        if (map != null) {
            return map.containsKey(atr);
        }
        return false;
    }

    public static Object createWBean(Class<?> c, Map info) throws Exception {
        Object bean = c.newInstance();
        Map<String, PropertyDescriptor> map = Data.getBeanInfo(c, true);
        if (info != null && info.size() > 0) {
            Iterator it = info.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                if (map.containsKey(en.getKey())) {
                    PropertyDescriptor mb = map.get(en.getKey());
                    String val = ((String[]) en.getValue())[0];
                    if (val != null || (val == null && !mb.getPropertyType().isPrimitive())) {
                        log.trace("  - " + mb.getWriteMethod().getName() + " [" + en.getKey() + "," + val + "]" + mb.getPropertyType());
                        if (!val.getClass().equals(mb.getPropertyType())) {
                            if (mb.getPropertyType().equals(Integer.class)) {
                                mb.getWriteMethod().invoke(bean, LmzU.getInteger(val));
                            } else {
                                mb.getWriteMethod().invoke(bean, val);
                            }
                        } else {
                            mb.getWriteMethod().invoke(bean, val);
                        }
                    } else {

                    }

                }

            }
        }

        return bean;
    }

    public static void setBeanDef(InvokeBean b, Map info) {
        if (b != null && info != null && info.size() > 0) {
            Map bmap = BeanUtil.beanToMap(b);
            Iterator it = info.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                if (bmap.containsKey(en.getKey())) {
                    if (bmap.get(en.getKey()) == null) {
                        bmap.put(en.getKey(), en.getValue());
                    }
                }
            }
            BeanUtil.setBean(b, bmap);
        }
    }

}
