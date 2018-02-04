package cn.lmz.mike.web.base.util;


import cn.lmz.mike.web.base.service.IWService;

public class LoginU {

	public static final String GET="GET";
	public static final String POST="POST";
	
	public static String getRegUrl(IWService service){
		return "/user/signup.jsp";
	}
	
	public static String getLoginUrl(IWService service){
		return "/user/signin.jsp";
	}
	
}
