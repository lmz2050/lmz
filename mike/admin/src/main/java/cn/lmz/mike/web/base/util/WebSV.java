package cn.lmz.mike.web.base.util;


import cn.lmz.mike.common.base.PropU;
import cn.lmz.mike.common.io.FileU;
import com.opensymphony.xwork2.Action;

import java.util.Map;

public class WebSV {

	private static Map<String,String> webMap= null;

	public static final String AUTO = "auto";
	public static final String ID = "id";
	public static final String PID = "pid";
	public static final String ORD = "ord";
	public static final String INTOP = "intop";
	public static final String TAB = "table";
	public static final String MAX_NUM = "maxnum";
	
	public static final String NAME = "name";
	public static final String ENAME = "ename";
	public static final String TYPE = "type";
	
	public static final String SP = "\\|";
	public static final String SPX = "|";
	
	public static final String admin="admin";
	public static final String user="user";
	public static final String NAME_PWD = "name_pwd";
	public static final int COOKIE_AGE = 7*24*3600;
	
	
	
	public static final String ADMINS = "loginService";
	public static final String DBDAO = "dbDao";
	public static final String CACHEDAO = "cacheDao";
	public static final String ENTITY = "entity";
	
	public static String SUCCESS = Action.SUCCESS;
	public static String INPUT = Action.INPUT;
	public static String LOGIN = Action.LOGIN;
	public static String MOBILE_LOGIN = "mlogin";
	public static String ADMIN_LOGIN = "adminlogin";
	public static String ERROR = Action.ERROR;
	
	public static String REG = "reg";
	public static String INDEX = "index";
	public static String ADMIN = "admin";
	public static String JSON = "json";
	public static String URL = "url";
	public static String URLR = "urlr";
	public static String ACTION = "action";
	public static String RURL = "rurl";
	public static String RACTION = "raction";
	
	
	public static final String LTREE="LTREE";
	public static final String BTNLIST="BTNLIST";

	public static final String PROPERTIES_WEB="web.properties";
	
	
	public static final String FILE_UPLOAD_PATH = "FILE_UPLOAD_PATH";

	public static final String getFileUploadPath(){
		Map<String,String> map = PropU.getMap(FileU.getPath(WebSV.PROPERTIES_WEB));
		if(map!=null){
			return map.get(WebSV.FILE_UPLOAD_PATH);
		}
		return null;
	}
	
}
