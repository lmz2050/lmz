package cn.lmz.mike.data.util.json;


import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.invoke.Handler;

public class JsonHander implements Handler {

	private String jstr="";	
	public void doresult(String key,Object value)  throws LMZException {
		jstr+=JsonUtil.getjStrByKV(key,value);
	}
	
	public String getJStr(){
      if(!MC.string.isBlank(jstr)){
       	  jstr = "{"+removeLastF(jstr)+"}";
        }else{
       	  jstr = "{}";
        } 
	    return jstr;
	}

	public static String removeLastF(String str){
		return str.substring(0,str.length()-1);
	}
    
}
