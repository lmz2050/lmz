package cn.lmz.mike.common.invoke;

import cn.lmz.mike.common.MC;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import cn.lmz.mike.common.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvokeU {


	public static Object invokeMethod(Object object,String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return MethodUtils.invokeMethod(object,methodName,args);
	}

	//Constructor
	public static <T> T invokeConstructor(Class<T> cls, Object[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return ConstructorUtils.invokeConstructor(cls,args);
	}


	//Field
	public static Field getDeclaredField(Class<?> cls, String fieldName){
		return FieldUtils.getDeclaredField(cls,fieldName);
	}
	public static Field[] getAllFields(Class<?> cls){
		return FieldUtils.getAllFields(cls);
	}
	public static Field[] getFieldsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls)
	{
		return FieldUtils.getFieldsWithAnnotation(cls,annotationCls);
	}
	public static Object readDeclaredField(Object target, String fieldName) throws IllegalAccessException {
		return FieldUtils.readDeclaredField(target,fieldName);
	}
	public static void writeDeclaredField(Object target, String fieldName, Object value) throws IllegalAccessException {
		FieldUtils.writeDeclaredField(target,fieldName,value);
	}

	public static Map<String,Object> getAllPubAttribute(Class<?> cls){
		Validate.isTrue(cls != null, "The class must not be null");
		Map<String,Object> atrMap = new HashMap<String,Object>();
		List<Field> flist = getAllPubFields(cls);
		for(Field f:flist){
			atrMap.put(f.getName(),f);
		}
		List<Class> clist =  getAllPubInnerClass(cls);
		for(Class c:clist){
			String key = c.getName().substring(c.getName().indexOf("$"));
			atrMap.put(key,c);
		}
		return  atrMap;
	}

	public static List<Class> getAllPubInnerClass(Class<?> cls){
		Validate.isTrue(cls != null, "The class must not be null");

		List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
		if(allSuperclasses==null) allSuperclasses = new ArrayList<Class<?>>();
		allSuperclasses.add(cls);

		final List<Class> fList = new ArrayList<Class>();
		for (final Class<?> acls : allSuperclasses) {
			final Class[] classs = acls.getDeclaredClasses();
			for (final Class clazz : classs) {
				if (Modifier.isPublic(clazz.getModifiers())
						&& !Modifier.isAbstract(clazz.getModifiers())
						&& !Modifier.isInterface(clazz.getModifiers())) {
					fList.add(clazz);
				}
			}
		}
		return fList;
	}

	public static List<Field> getAllPubFields(Class<?> cls){
		Validate.isTrue(cls != null, "The class must not be null");

		List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
		if(allSuperclasses==null) allSuperclasses = new ArrayList<Class<?>>();
		allSuperclasses.add(cls);

		final List<Field> fList = new ArrayList<Field>();
		for (final Class<?> acls : allSuperclasses) {
			final Field[] fields = acls.getDeclaredFields();
			for (final Field field : fields) {
				if (Modifier.isPublic(field.getModifiers())
						&& !Modifier.isAbstract(field.getModifiers())
						&& !Modifier.isInterface(field.getModifiers())) {
					fList.add(field);
				}
			}
		}
		return fList;
	}
	public static List<Method> getAllPubMethods(Class<?> cls){
		Validate.isTrue(cls != null, "The class must not be null");

		List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
		if(allSuperclasses==null) allSuperclasses = new ArrayList<Class<?>>();
		allSuperclasses.add(cls);

		final List<Method> mList = new ArrayList<Method>();
		for (final Class<?> acls : allSuperclasses) {
			if(!acls.equals(Object.class)) {
				final Method[] methods = acls.getDeclaredMethods();
				for (final Method method : methods) {
					if (Modifier.isPublic(method.getModifiers())
							&& !Modifier.isAbstract(method.getModifiers())
							&& !Modifier.isInterface(method.getModifiers())) {
						mList.add(method);
					}
				}
			}
		}
		return mList;
	}


	/*
	public static Object invoke1(Object bean,String method,Object[] objs) throws Exception{
		boolean isExecuted=false;
		Object re=null;
		if(bean==null){
			throw new Exception(bean+"."+method+"("+StrU.toStr(objs)+")"+": class is null ");
		}

		Class<?> cls = bean.getClass();
		java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(cls);
		
		if(info!=null){
			MethodDescriptor[] md = info.getMethodDescriptors();			
			for(int i=0;i<md.length;i++){
				String name = md[i].getName();
				if(name.equalsIgnoreCase(method)){
					Method mt = md[i].getMethod();
					if(objs==null){
						if(mt.getParameterTypes().length>0){
							continue;
						}
						re = mt.invoke(bean);
						isExecuted = true;
						break;
					}else{
						
						boolean array=false;
						for(int j=0;j<mt.getParameterTypes().length;j++){
							Class<?> ttj = mt.getParameterTypes()[j];
							if(ttj.isArray()){
								array=true;
							}
						}
						if(mt.getParameterTypes().length==objs.length||(array&&mt.getParameterTypes().length<=objs.length)){
							for(int j=0;j<mt.getParameterTypes().length;j++){
								Class<?> ttj = mt.getParameterTypes()[j];
								if("int".equals(ttj.getSimpleName())||"Integer".equals(ttj.getSimpleName())){
									objs[j] = new Integer((String)objs[j]);
								}
								if(ttj.isArray()){
									objs = new Object[]{objs};
								}
							}
							re = mt.invoke(bean, objs);
							isExecuted = true;
							//O.pn("R="+re);
							break;	
						}
					}
					
					
				}
			}
		}

		return re;
	}
*/

	public static void main(String[] args){


		Test t = new Test();
		Object[] args1 = new Object[]{"asd","kkk"};
		//args1[0]="aaa";
		//args1[1]="bbb";
		//{"asd","kkk"};
		Object[] args2 = {null};
		//Object[] args3 = new Object[]{"ddd","fff"};

		try {
			Map<String,Object> am =  getAllPubAttribute(MC.class);
			for(Map.Entry en:am.entrySet()){
				System.out.println(en.getKey()+"-"+en.getValue());
			}
		} catch (Exception e) {
			ExceptionUtils.getRootCause(e);
		}


	}

}
