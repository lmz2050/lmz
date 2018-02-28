package cn.lmz.mike.web.base.util;

import cn.lmz.mike.web.base.bean.BaseBean;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Context {

	private static final Logger log = LoggerFactory.getLogger(Context.class);
	public static Map<String,Object> getApplication(){
		return ActionContext.getContext().getApplication();
	}	
	public static Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	public static HttpSession getHttpSession(){
		return ServletActionContext.getRequest().getSession();
	}	
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public static HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}	

	public static BaseBean getAdmin(){
		BaseBean b = (BaseBean) getSession().get(WebSV.admin);
		return b;
	}
	
	public static Object getBean(ServletContext ctx,String name){
		return WebApplicationContextUtils.getRequiredWebApplicationContext(ctx).getBean(name);
	}	
	public static Object getBean(String name){
		return WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext()).getBean(name);
	}
	
	public static Map<String, String> getBeans(boolean isAll){
		Map<String, String> bmap = new HashMap<String, String>();
		try{
			Map<String,Object> m = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext()).getBeansOfType(Object.class);
			System.out.println("====================================================="+m.size());
			for(Map.Entry<String, Object> en:m.entrySet()){
				String clz=getTarget(en.getValue()).getClass().getName();
				if(isAll){
					bmap.put(en.getKey(), clz);
					System.out.println(en.getKey()+"="+clz);
				}else{
					if(!clz.startsWith("org.springframework")){
						bmap.put(en.getKey(), clz);
						System.out.println(en.getKey()+"="+clz);
					}	
				}		
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		return bmap;
	}
	   

		/** 
		* 获取 目标对象 
		* @param proxy 代理对象 
		* @return 
		* @throws Exception 
		*/ 
		public static Object getTarget(Object proxy) throws Exception { 
			if(!AopUtils.isAopProxy(proxy)) { 
				return proxy;//不是代理对象 
			} 

			if(AopUtils.isJdkDynamicProxy(proxy)) { 
				return getJdkDynamicProxyTargetObject(proxy); 
			} else { //cglib 
				return getCglibProxyTargetObject(proxy); 
} 
		} 


		private static Object getCglibProxyTargetObject(Object proxy) throws Exception { 
			Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0"); 
			h.setAccessible(true); 
			Object dynamicAdvisedInterceptor = h.get(proxy); 
			Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised"); 
			advised.setAccessible(true); 
			Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget(); 
			return target; 
		} 


		private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception { 
			Field h = proxy.getClass().getSuperclass().getDeclaredField("h"); 
			h.setAccessible(true); 
			AopProxy aopProxy = (AopProxy) h.get(proxy); 
			Field advised = aopProxy.getClass().getDeclaredField("advised"); 
			advised.setAccessible(true); 
			Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget(); 
			return target; 
		}
}
