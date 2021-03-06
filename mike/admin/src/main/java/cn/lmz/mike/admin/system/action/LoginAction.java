package cn.lmz.mike.admin.system.action;

import cn.lmz.mike.common.V;
import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.sec.MD5U;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.service.ILoginService;
import cn.lmz.mike.web.base.util.CookieUtil;
import cn.lmz.mike.web.base.util.LoginMsg;
import cn.lmz.mike.web.base.util.OnlineUserMap;
import cn.lmz.mike.web.base.util.WebSV;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller()
@Scope("prototype")
public class LoginAction extends SysAction{

	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

	private static final long serialVersionUID = 1L;	
	private String new_pass;
	private String code;
	@Resource
	private ILoginService loginService;
	private Lmzadmin info = new Lmzadmin();
	

	public String loginPage(){
		return WebSV.SUCCESS;
	}

	public String home(){
		return WebSV.SUCCESS;
	}

	//管理员
	public String login()
	{
		try {
			info = this.getAdmin();
			if(info!=null){
				infoL = systemService.getUserMenu(info.getId()+"",this.getSession());
				//this.getSession().put(WebSV.LTREE, infoL);
				return WebSV.ADMIN;
			}
		} catch (Exception e) {	
			handException(e);
		}
		return getRA(WebSV.LOGIN,null);
	}

	public String check()
	{
		try{
			String codeSession = (String)this.getSession().get(V.CODE_SESSION_KEY);
			if(!StrU.isBlank(codeSession)&&!codeSession.equalsIgnoreCase(code)){
				r.setSuccess(false);
				r.setMsg(getText("login.msg.INCORRECT_CODE"));
			}
			
			if(StrU.isBlank(r.getMsg())){
				
				List alist = wService.searchCO(Lmzadmin.class, "username",info.getUsername(),"userpwd", MD5U.calc(info.getUserpwd()));
				if(alist!=null&&alist.size()>0)
				{
					Lmzadmin admin1 = (Lmzadmin) alist.get(0);
					admin1.setLastip(ServletActionContext.getRequest().getRemoteAddr());
					admin1.setLastlogin(DateU.getTimeString());
					loginService.update(admin1);
					this.setAdmin(admin1,"1");
					r.setMsg("用户: " + info.getUsername() + "登录成功");
					r.setSuccess(true);
				}else
				{
					r.setSuccess(false);
					r.setMsg(getText("login.msg.INCORRECT_USER_OR_PWD"));
				}				
				
			}

		}catch(Exception e){
			handException(e);
		}
		return jsonStr();
	}
	
	public String uppwd(){
		try{
			Lmzadmin admin1= this.getAdmin();
			if(admin1.getUserpwd().equals(MD5U.calc(info.getUserpwd()))){			
				admin1.setUserpwd(MD5U.calc(new_pass));
				try {
					loginService.update(admin1);
				} catch (Exception e) {		
					handException(e);
				}
			}else{
				error = getText("login.msg.INCORRECT_PWD");
		}
		}catch(Exception e){
			handException(e);
		}
		error=WebSV.SUCCESS;
		return WebSV.SUCCESS;
	}
	
	public String reg(){
		try {
			if(loginService.findByUserName(info.getUsername())){
				msg= getText("login.msg.EXIST");
				return WebSV.SUCCESS;
			}else{
				info.setLastip(ServletActionContext.getRequest().getRemoteAddr());
				info.setLastlogin(DateU.getTimeString());
				log.info(info.getUsername()+":"+info.getUsername());
				info.setUserpwd(MD5U.calc(info.getUserpwd()));
				info.setType(0);
				info= loginService.create(info);
				log.info("reg:user:"+info.getId()+"-"+info.getUsername()+",ip:"+info.getLastip());
				setAdmin(info);
				msg=WebSV.SUCCESS;
				return WebSV.SUCCESS;
			}
		} catch (Exception e) {		
			handException(e);
		}

		return WebSV.SUCCESS;		
	}
	
	public String loginout(){
		try{
			this.removeAdmin();
		}catch(Exception e){
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	protected void setAdmin(Lmzadmin admin) throws LMZException {
		getSession().put(WebSV.admin, admin);
		CookieUtil.setCookie(WebSV.NAME_PWD, admin.getUsername()+WebSV.SPX+admin.getUserpwd());
	}
	protected void removeAdmin() throws LMZException{
		CookieUtil.delCookie(WebSV.NAME_PWD);
		getSession().put(WebSV.admin, null);
		getSession().remove(WebSV.admin);
	}

	public String getNew_pass() {
		return new_pass;
	}

	public void setNew_pass(String newPass) {
		new_pass = newPass;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Lmzadmin getInfo() {
		return info;
	}
	public void setInfo(Lmzadmin info) {
		this.info = info;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		info = (Lmzadmin)bean;
	}
}
