package cn.lmz.mike.web.base.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.data.cache.ISyncBean;

@Lbean(sname="admin")
public class Lmzadmin extends BaseBean implements ISyncBean {

	private static final long serialVersionUID = -6077562361854292590L;

	protected String username;
	protected String userpwd;
	protected String useremail;
	protected String mobile;
	protected String lastip;
	protected String lastlogin;
	protected Integer type;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}