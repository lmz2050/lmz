package cn.lmz.mike.admin.business.bean;
import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Adm_ol_send extends BaseBean {

	protected String cus_name;
	protected String dev_name;

	protected String sysname;
	protected String sysversion;
	protected String appversion;

	protected String ol_time;
	protected Integer ol_total;

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
	public String getOl_time() {
		return ol_time;
	}
	public void setOl_time(String ol_time) {
		this.ol_time = ol_time;
	}
	public Integer getOl_total() {
		return ol_total;
	}
	public void setOl_total(Integer ol_total) {
		this.ol_total = ol_total;
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

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getSysversion() {
		return sysversion;
	}

	public void setSysversion(String sysversion) {
		this.sysversion = sysversion;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
}
