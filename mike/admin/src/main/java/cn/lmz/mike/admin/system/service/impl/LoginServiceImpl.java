package cn.lmz.mike.admin.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.common.sec.MD5U;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.service.ILoginService;
import cn.lmz.mike.web.base.service.impl.WService;
import cn.lmz.mike.web.base.util.CookieUtil;
import cn.lmz.mike.web.base.util.LoginSV;
import cn.lmz.mike.web.base.util.WebSV;
import cn.lmz.mike.web.bean.Lmzuser;
import cn.lmz.mike.web.connect.Openuser;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;


@Service("loginService")
public class LoginServiceImpl extends WService implements ILoginService {


	public Lmzuser getUserByOpenId(String openId, String type) throws LMZException {
		
		Lmzuser u = null;
		String sql=" select u.* from lmzuser u,openuser o where u.id=o.uid and o.openid=? and o.type=? ";
		PageUtil pu = searchObj(Lmzuser.class,sql,null,openId,type);
		if(pu!=null&&!pu.isListNull()){
			u = (Lmzuser)pu.getList().get(0);
		}
		return u;
		
	}


	public void loginSuccess(Lmzadmin u, Map session, String type) throws LMZException {
		u.setLastip(ServletActionContext.getRequest().getRemoteAddr());
		u.setLastlogin(DateU.getTimeString());
		session.put(type, u);
		CookieUtil.setCookie(WebSV.NAME_PWD, u.getUsername()+WebSV.SPX+u.getUserpwd());
	}

	@SuppressWarnings("unchecked")
	public Lmzadmin login(String username, String userpwd)  throws LMZException{
		try{
			Map<String,String> m = new HashMap<String,String>();
			m.put(LoginSV.USER, username);
			m.put(LoginSV.PWD, userpwd);
			List list = search(Lmzadmin.class, m);
			if(list.size()>0){
				return (Lmzadmin)list.get(0);
			}
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"login(String username, String userpwd)",e,username,userpwd);
		}
		return null;
	}	

	@SuppressWarnings("unchecked")
	public boolean findByUserName(String username)  throws LMZException{
		try{
			Map<String,String> m = new HashMap<String,String>();
			m.put(LoginSV.USER, username);
			List list = search(Lmzadmin.class, m);
			if(list!=null){
				return (list.size()>0);
			}
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"findByUserName(String username)",e,username);
		}
		return false;
	}


	public void RegWithOpenId(Openuser ou, Map session, String type) throws LMZException {
		
		Lmzadmin admin=null;
		if(WebSV.user.equalsIgnoreCase(type)){
			Lmzuser user = new Lmzuser();
			user.setName(ou.getName());
			
			admin=user;
		}
		/*
		String name=ou.getName();
		int i=0;
		while(findByUserName(name)){
			name=name+i;
			i++;
		}
		*/
		admin.setUsername(ou.getOpenid());
		admin.setUserpwd(ou.getOpenid());
		admin.setUserpwd(MD5U.calc(admin.getUserpwd()));
		admin.setType(3);

		admin = create(admin);
		
		ou.setUid(admin.getId());
		create(ou);
		
		session.put(type, admin);
		
	}

	@Override
	public BaseBean reg(Lmzadmin u) throws LMZException {
		return create(u);
	}
}
