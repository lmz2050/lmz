package cn.lmz.mike.web.base.action;

import cn.lmz.mike.data.util.LanU;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Locale;
import java.util.Map;

@Controller()
@Scope("prototype")
public class LanAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -3370730214067114226L;
	
	@SuppressWarnings("unchecked")
	private Map session;
	private String lan;
	
	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public String lan(){
		try{
			if(lan!=null){
				String[] lo = lan.split("_");
				if(lo.length>1){
					Locale local = new Locale(lo[0],lo[1]);
					session.put(LanU.LAN_SESSION_KEY,local);
					String lan = local.getLanguage()+"_"+local.getCountry();
					LanU.setLan(lan);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	public String getLan() {
		return lan;
	}

	public void setLan(String lan) {
		this.lan = lan;
	}

}