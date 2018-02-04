package cn.lmz.mike.data.util.json;


import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.invoke.Handler;
import cn.lmz.mike.common.str.StrU;

public class JsonHander implements Handler {

	private String jstr="";	
	public void doresult(String key,Object value)  throws LMZException {
		jstr+=JsonUtil.getjStrByKV(key,value);
	}
	
	public String getJStr(){
      if(!StrU.isBlank(jstr)){
       	  jstr = "{"+StrU.removeLastF(jstr)+"}";
        }else{
       	  jstr = "{}";
        } 
	    return jstr;
	}
    
}
