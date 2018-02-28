package cn.lmz.mike.web.base.util.interceptor;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.util.WebU;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PageInterceptor implements Interceptor {
	
	private static final long serialVersionUID = -4237718941834173012L;
	private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class);

	public void destroy() {
		
	}

	public void init() {
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		try{
			Map session = invocation.getInvocationContext().getSession();
			String theme = WebU.getTheme(session);
			ActionConfig config = invocation.getProxy().getConfig();
			
			ActionContext ac = invocation.getInvocationContext();
			
            ValueStack stack = ac.getValueStack();
            String m = stack.findString("m");
            log.info("PageInterceptor-m:"+m);

            String tpath = WebU.getTplPath(m);
            stack.set("TPATH", tpath);
            log.info("PageInterceptor1:"+tpath);
            
            String re = invocation.invoke();
            
	        return re;
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"intercept(ActionInvocation invocation)",e);
		}
	}

}