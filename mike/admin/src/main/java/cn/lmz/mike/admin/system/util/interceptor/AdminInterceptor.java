package cn.lmz.mike.admin.system.util.interceptor;

import cn.lmz.mike.admin.system.util.SysU;
import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.net.HttpU;
import cn.lmz.mike.data.util.LanU;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.util.Context;
import cn.lmz.mike.web.base.util.OnlineUserMap;
import cn.lmz.mike.web.base.util.WebMsg;
import cn.lmz.mike.web.base.util.WebSV;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

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
			String msg="";
	        if (admin!=null){

				Lmzadmin loginuserinfo = OnlineUserMap.onlineuser.get(admin.getId());
				if(loginuserinfo!=null&&admin.getSessionId().equalsIgnoreCase(loginuserinfo.getSessionId())){
					return actionInvocation.invoke();
				}else {
					msg = WebMsg.getI18nMsg("login.msg.LOGIN_OTHER");
				}

	        } else {
	        	msg = WebMsg.getI18nMsg("login.msg.LOGIN_FIRST");
	        }
			actionInvocation.getInvocationContext().put("msg", msg);
	        
	        ActionContext ac = actionInvocation.getInvocationContext();
            ValueStack stack = ac.getValueStack();
            String m = stack.findString("m");
	        
	        HttpServletRequest request = (HttpServletRequest)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
	        if(HttpU.isAjax(request)){
	        	HttpServletResponse responce = (HttpServletResponse)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
	        	responce.setContentType("text/html;charset=UTF-8");
	        	String url= SysU.getDomain((IWService) Context.getBean("wService"),m)+"login.html";
	        	String str="<script>alert('"+msg+"');top.location.href='"+url+"'</script>";
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
