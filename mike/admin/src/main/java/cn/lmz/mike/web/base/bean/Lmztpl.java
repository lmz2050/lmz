package cn.lmz.mike.web.base.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.data.cache.ISyncBean;

@Lbean(sname="tpl")
public class Lmztpl extends BaseBean implements ISyncBean {
	
	public String name;
	public String cname;
	public String url;
	public String param;
	public String action;
	public String atype;
	public String utype;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	

}