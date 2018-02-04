package cn.lmz.mike.data.util.json;

import cn.lmz.mike.common.invoke.InvokeBean;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.BeanUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtil {
	
   public static String beanToJson(Object bean){
	   JsonHander jh=new JsonHander();   
	   BeanUtil.handBean(bean,jh);
	   return jh.getJStr();
  }
  @SuppressWarnings("unchecked")
public static String listToJson(List list){
	  String jstr="[";
	  for(int i=0;i<list.size();i++){
		  jstr+=toJson(list.get(i));
		  if(i!=list.size()-1){
			  jstr+=",";
		  }
	  }
	  jstr+="]";
	  return jstr;
  }
  
  @SuppressWarnings("unchecked")
public static String toJson(Object bean){
	  String jstr="";
	  if(bean==null){
		  jstr+="null";
	  }else{
		  if(bean instanceof InvokeBean){
		 	  jstr+=beanToJson(bean);
		  }else if(bean instanceof List){
			  jstr+=listToJson((List)bean);
	 	  }else if(bean instanceof String){
	 		 jstr+="\""+bean+"\"";
	 	  }else if(bean instanceof Map){
	 		 jstr+= mapToJson((Map)bean);
	 	  }else{
	 		 jstr+=bean;
	 	  }
	  }
	  return jstr;	  
  }
  
  @SuppressWarnings("unchecked")
public static String mapToJson(Map map){
	  String jstr="";
	  Set  set  =map.entrySet();   
      Iterator  iterator = set.iterator();
      while(iterator.hasNext())   
      {
         Map.Entry mapentry=(Map.Entry)iterator.next(); 
         jstr+=JsonUtil.getjStrByKV(mapentry.getKey()+"",mapentry.getValue()); 
      }
      if(!StrU.isBlank(jstr)){
      	 jstr = "{"+StrU.removeLastF(jstr)+"}";
       }else{
      	 jstr = "{}";
       } 
      return jstr;
  }
   
  public static String getjStrByKV(String key,Object v){
	  String ss = "\""+key+"\":"+toJson(v)+",";
	  return ss;
  }
}
