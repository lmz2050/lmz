package cn.lmz.mike.web.connect;

import cn.lmz.mike.web.base.bean.BaseBean;

public class Openuser extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String openid;
	private String token;
	private String name;
	private String type;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
