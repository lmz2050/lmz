package cn.lmz.mike.admin.business.bean;

import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Adm_dev_log extends BaseBean {

	protected String cus_name;
	protected String dev_name;
	protected String mac1;
	protected String mac2;

	protected String version;
	protected String path;
	protected String content;

	protected String uby;
	protected String utm;
	
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getMac1() {
		return mac1;
	}
	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}
	public String getMac2() {
		return mac2;
	}
	public void setMac2(String mac2) {
		this.mac2 = mac2;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUby() {
		return uby;
	}
	public void setUby(String uby) {
		this.uby = uby;
	}
	public String getUtm() {
		return utm;
	}
	public void setUtm(String utm) {
		this.utm = utm;
	}

}
