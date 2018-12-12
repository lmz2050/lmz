package com.lmz.mike.data.support.bean;


import com.lmz.mike.common.util.StrU;
import com.lmz.mike.data.annotation.Lbean;
import com.lmz.mike.data.config.DataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class Data {

	private static final Logger log = LoggerFactory.getLogger(Data.class);

	private static final Map<Class<?>,Map<String,PropertyDescriptor>> map = new HashMap<Class<?>,Map<String,PropertyDescriptor>>();	
	private static final Map<Class<?>,Map<String,PropertyDescriptor>> mapAll = new HashMap<Class<?>,Map<String,PropertyDescriptor>>();
	public static final Map<Class<?>,String> selectMap = new HashMap<Class<?>,String>();	
	private static final Map<String,Class<?>> namemap = new HashMap<String,Class<?>>();	
	private static final Map<String,Object> regmap = new HashMap<String,Object>();

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
			DataConfig.initC(c);
			return mm.get(c);
		}
		return mm.get(c);
	}	

	public static String getTab(Class<?> c){
		String tab=c.getSimpleName();
		Lbean b = c.getAnnotation(Lbean.class);
		if(b!=null&&!StrU.isBlank(b.tabName())){
			tab = b.tabName();
		}
		return tab;
	}

	public static Map<Class<?>, Map<String, PropertyDescriptor>> getMap() {
		return map;
	}

	public static Map<Class<?>, Map<String, PropertyDescriptor>> getMapAll() {
		return mapAll;
	}

	public static Map<Class<?>, String> getSelectMap() {
		return selectMap;
	}

	public static Map<String, Class<?>> getNamemap() {
		return namemap;
	}

	public static Map<String, Object> getRegmap() {
		return regmap;
	}
}
