package cn.lmz.mike.web.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.service.ILoginService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class CookieUtil{

	public static Lmzadmin loginWithCookie() throws LMZException {
		try{
			String namepwd = getCookie(WebSV.NAME_PWD);
			if(!StrU.isBlank(namepwd)&&namepwd.indexOf(WebSV.SPX)!=-1){
				String[] split = namepwd.split(WebSV.SP);  
	            String username = split[0];  
	            String userpwd = split[1];
	            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext()); 
	   		    ILoginService adminS = (ILoginService)applicationContext.getBean(WebSV.ADMINS);
	   		    Lmzadmin admin = adminS.login(username,userpwd);
	   		    return admin;
			}
		}catch(Exception e){
			throw new LMZException(CookieUtil.class.getName(),"loginWithCookie()",e);
		}
		return null;
	}
	
	public static String getCookie(String name) throws LMZException{
		try{
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			String value = "";
			if (cookies != null) {
	            for (Cookie cookie : cookies) {  
	                if(name.equals(cookie.getName())){  
	                    value = cookie.getValue();
	                    break;
	                 }
	            }
	        }
	        return value;
		}catch(Exception e){
			throw new LMZException(CookieUtil.class.getName(),"getCookie(String name)",e,name);
		}
	}
	
	public static void setCookie(String key,String value) throws LMZException{
		try{
	        Cookie cookie = new Cookie(key, value);  
	        cookie.setMaxAge(WebSV.COOKIE_AGE);
	        cookie.setPath("/");
	        ServletActionContext.getResponse().addCookie(cookie); 
		}catch(Exception e){
			throw new LMZException(CookieUtil.class.getName(),"setCookie(String key,String value)",e,key,value);
		}
	}
	
	public static void delCookie(String key) throws LMZException{
		try{
			Cookie cookie = new Cookie(key, "");  
	        cookie.setMaxAge(0);
	        cookie.setPath("/");
	        ServletActionContext.getResponse().addCookie(cookie); 
		}catch(Exception e){
			throw new LMZException(CookieUtil.class.getName(),"delCookie(String key)",e,key);
		}
	}
	
	public static void sendMsg(String msg) throws LMZException{
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");
	        msg = "{\"msg\":\""+msg+"\"}";
	        response.getWriter().write(msg);
	        response.getWriter().flush();
	        response.getWriter().close();
		}catch(Exception e){
			throw new LMZException(CookieUtil.class.getName(),"sendMsg(String msg)",e,msg);
		}
	}
	
}
