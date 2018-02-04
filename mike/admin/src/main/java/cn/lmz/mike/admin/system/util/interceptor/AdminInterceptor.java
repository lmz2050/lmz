package cn.lmz.mike.admin.system.util.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lmz.mike.admin.system.util.SysU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.net.HttpU;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.util.Context;
import cn.lmz.mike.web.base.util.LoginMsg;
import cn.lmz.mike.web.base.util.WebSV;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class AdminInterceptor implements Interceptor {

	private static final long serialVersionUID = -4237718941834173012L;


	public void destroy() {
		 
	}

	public void init() {
		
	}

	
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		try{
			Map session = actionInvocation.getInvocationContext().getSession();
			Lmzadmin admin = (Lmzadmin)session.get(WebSV.admin);
	        if (admin!=null){
	        	return actionInvocation.invoke();
	        } else {
	        	actionInvocation.getInvocationContext().put("msg", LoginMsg.LOGIN_FIRST);
	        }
	        
	        
	        ActionContext ac = actionInvocation.getInvocationContext();
            ValueStack stack = ac.getValueStack();
            String m = stack.findString("m");
	        
	        HttpServletRequest request = (HttpServletRequest)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
	        if(HttpU.isAjax(request)){
	        	HttpServletResponse responce = (HttpServletResponse)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
	        	responce.setContentType("text/html;charset=UTF-8");
	        	String url= SysU.getDomain((IWService) Context.getBean("wService"),m)+"user/login.jsp";
	        	String str="<script>alert('"+LoginMsg.LOGIN_FIRST+"');top.location.href='"+url+"'</script>";
	        	responce.getWriter().write(str);
	        	responce.getWriter().flush();
	        	responce.getWriter().close();
	        	return null;
	        }
	        
	        
	        return WebSV.ADMIN_LOGIN;
	        
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"intercept(ActionInvocation actionInvocation)",e);
		}
	}

}
