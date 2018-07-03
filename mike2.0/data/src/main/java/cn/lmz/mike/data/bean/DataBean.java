package cn.lmz.mike.data.bean;

import cn.lmz.mike.common.M;
import cn.lmz.mike.data.util.BeanUtil;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


@SuppressWarnings("serial")
public class DataBean<T> extends LinkedHashMap implements Serializable,Cloneable{
	
	public static String ID="id";
	private Class<T> clz;

	public DataBean(){}
	public DataBean(Class<T> c){
		this.clz = c;
	}
	public DataBean(Class<T> c,Map params){
		this.clz = c;
		this.setMap(params);
	}
	public DataBean(Class<T> c,Object...objects){
		this.clz = c;
		this.setParams(objects);
	}
	
	public String getString(String key){
		Object val=get(key);
		if(val==null) return "";
		return val+"";
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<T> clz) {
		this.clz = clz;
	}
	
	public String getId(){
		return getString(ID);
	}
	public void setId(Object id){
		put(ID,id);
	}
	public Object toObject(){
		return BeanUtil.createBean(clz, this);
	}	
	public void setParams(Object...objects){
		if(objects!=null&&objects.length>1){
			for(int i=1;i<objects.length;i+=2){
				this.put(objects[i-1], objects[i]);
			}			
		}
	}
	public void setMap(Map params){
		if(params!=null){
			this.putAll(params);
		}
	}	
	public static DataBean getData(Object bean){
		if(bean==null) return null;
		DataBean bb = new DataBean();
		if(bean instanceof Map){
			Map m = (Map)bean;
			bb.putAll(m);
		}else{
			bb.setClz(bean.getClass());
			Map m = BeanUtil.beanToMap(bean,false);
			bb.putAll(m);
		}
		return bb;
	}
	public static <T> DataBean<T> getData(Class<T> c,Map params){
		DataBean<T> bb = new DataBean<T>();
		bb.setClz(c);
		if(params!=null)bb.putAll(params);
		return bb;
	}	
	public static DataBean getDataNull(Object bean){
		if(bean==null) return null;
		DataBean bb = new DataBean();;
		if(bean instanceof Map){
			Map m = (Map)bean;
			bb.putAll(m);
		}else{
			bb.setClz(bean.getClass());
			Map m = BeanUtil.beanToMap(bean,true);
			bb.putAll(m);
		}
		return bb;
	}
	
	public String toString(){
		String str=this.getClz()+"{";
		Iterator it = this.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object val = this.get(key);
			str+=key+":"+ M.string.toString( val)+",";
		}
		str+="}";
		return str;
	}
}
