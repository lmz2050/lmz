package cn.lmz.mike.common.net;


import cn.lmz.mike.common.base.XmlU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpU {

	private static final Logger log = LoggerFactory.getLogger(HttpU.class);

    private static final String XHR_OBJECT_NAME = "XMLHttpRequest";  
    private static final String HEADER_REQUEST_WITH = "x-requested-with";
    
	public static boolean isAjax(HttpServletRequest request){
		if (XHR_OBJECT_NAME.equals(request.getHeader(HEADER_REQUEST_WITH))) {  
            return true;
        }
		return false;
	}
	
	public static String getRequestStr(HttpServletRequest request){
		String str="";
		String inputLine=null;
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				str += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static Map<String,String> getRequestXmlMap(HttpServletRequest request){
		
		String str=getRequestStr(request);
		Map<String,String> map = XmlU.parseXmlToList2(str);
		
		return map;
	}	

	public static Map<String,String> getRequestMap(HttpServletRequest request) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		request.setCharacterEncoding("UTF-8");
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			log.debug(name+":"+valueStr);
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"UTF-8");
			params.put(name, valueStr);
		}
		return params;
	}
	public static String getRequestMapStr(HttpServletRequest request) throws Exception{
		String valueStr = "";
		request.setCharacterEncoding("UTF-8");
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			valueStr+=((valueStr.trim().length()==0)?"":"&")+name+"="+values[0];
			
		}
		return valueStr;
	}	
}
