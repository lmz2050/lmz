package cn.lmz.mike.web.base.action;

import cn.lmz.mike.admin.system.service.ISystemService;
import cn.lmz.mike.data.util.LanU;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.util.WebSV;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller()
@Scope("prototype")
public class LanAction extends BasicAction implements SessionAware {

	private static final long serialVersionUID = -3370730214067114226L;
	static Logger log = LoggerFactory.getLogger(LanAction.class);

	@SuppressWarnings("unchecked")
	private Map session;
	private String lan;

	@Resource
	private ISystemService systemService;
	
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
					String lan = local.getLanguage()+"_"+local.getCountry();
					LanU.setLan(lan);
					ActionContext.getContext().setLocale(local);
					ServletActionContext.getRequest().getSession().setAttribute(LanU.LAN_SESSION_KEY, local);
					session.put(LanU.LAN_SESSION_KEY,local);
					log.info("change to language:{}",lan);

					//刷新导航
					Lmzadmin admin = (Lmzadmin)this.getSession().get(WebSV.admin);
					List<Map> mlist = systemService.getUserMenu(admin.getId()+"",this.getSession());
					//this.getSession().put(WebSV.LTREE, mlist);
					for(int i=0;i<mlist.size();i++){
						log.info(mlist.get(i).get("text")+"");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		r.setSuccess(true);
		return jsonStr();
	}

	public String getLan() {
		return lan;
	}

	public void setLan(String lan) {
		this.lan = lan;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}
}