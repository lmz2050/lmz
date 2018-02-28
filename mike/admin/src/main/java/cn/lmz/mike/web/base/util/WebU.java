package cn.lmz.mike.web.base.util;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.data.IData;
import cn.lmz.mike.web.base.bean.Lmztheme;
import cn.lmz.mike.web.base.bean.Lmztpl;
import cn.lmz.mike.web.base.service.IWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WebU implements IData {

	private static final Logger log = LoggerFactory.getLogger(WebU.class);
	private static String THEME=null;

	public static Map<String,Lmztpl> tplMap = new HashMap<String,Lmztpl>();
	public static final Map<Class<?>,Map<String,Object>> addmap = new HashMap<Class<?>,Map<String,Object>>();	
	
	
    private static final String XHR_OBJECT_NAME = "XMLHttpRequest";  
    private static final String HEADER_REQUEST_WITH = "x-requested-with";
    
    public void reg(){
    	
    }
    
	
	public static String getTheme(Map session) throws LMZException {
		if(THEME==null){
			IWService service = (IWService)Context.getBean("wService");
			List tlist = service.search(Lmztheme.class,null,null," ord ").getList();
			if(tlist!=null&&tlist.size()>0){
				for(int i=0;i<tlist.size();i++){
					Lmztheme lt = (Lmztheme)tlist.get(i);
					if("Y".equalsIgnoreCase(lt.getDef())){
						THEME = lt.getName();break;
					}
				}
				if(THEME==null){
					Lmztheme lt = (Lmztheme)tlist.get(0);
					THEME = lt.getName();
				}
			}
			log.info("THEME:"+THEME);
			if(THEME==null)THEME="";
		}
		session.put("THEME", THEME);
		return THEME;
	}	
	public static String getTplPath(String m) throws LMZException{
		String root="";
		if(!MC.string.isBlank(THEME)){
			root="/tpl/"+THEME+"/";
			if("1".equals(m)){
				root=root+"mobile";
			}else if(!StrU.isBlank(m)){
				root=root+m;
			}else{
				root=root+"web";
		}
		}
		return root;
	}
	
	public static String getString(Object o){
		if(o==null) return "";
		if(o instanceof String[]){
			o = ((String[])o)[0];
		}
		return String.valueOf(o);
	}
	/*
	public static boolean isAjax(HttpServletRequest request){
		if (XHR_OBJECT_NAME.equals(request.getHeader(HEADER_REQUEST_WITH))) {  
            return true;
        }
		return false;
	}
	*/
	public static String getDateID(){
		return MC.date.getTimeString()+(int)Math.random()*100;
	}
}
