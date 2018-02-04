package cn.lmz.mike.admin.business.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Adm_ol_recv extends BaseBean {

	protected String cus_name;
	protected String dev_name;
	protected String mac1;
	protected String mac2;

	protected String version;
	protected String ol_time;
	protected Integer ol_total;
	
	protected Integer gn1_total;
	protected Integer gn2_total;
	protected Integer gn3_total;
	protected Integer gn4_total;
	protected Integer gn5_total;
	protected Integer gn6_total;
	protected Integer gn7_total;
	protected Integer gn8_total;
	
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
	public Integer getGn1_total() {
		return gn1_total;
	}
	public void setGn1_total(Integer gn1_total) {
		this.gn1_total = gn1_total;
	}
	public Integer getGn2_total() {
		return gn2_total;
	}
	public void setGn2_total(Integer gn2_total) {
		this.gn2_total = gn2_total;
	}
	public Integer getGn3_total() {
		return gn3_total;
	}
	public void setGn3_total(Integer gn3_total) {
		this.gn3_total = gn3_total;
	}
	public Integer getGn4_total() {
		return gn4_total;
	}
	public void setGn4_total(Integer gn4_total) {
		this.gn4_total = gn4_total;
	}
	public Integer getGn5_total() {
		return gn5_total;
	}
	public void setGn5_total(Integer gn5_total) {
		this.gn5_total = gn5_total;
	}
	public Integer getGn6_total() {
		return gn6_total;
	}
	public void setGn6_total(Integer gn6_total) {
		this.gn6_total = gn6_total;
	}
	public Integer getGn7_total() {
		return gn7_total;
	}
	public void setGn7_total(Integer gn7_total) {
		this.gn7_total = gn7_total;
	}
	public Integer getGn8_total() {
		return gn8_total;
	}
	public void setGn8_total(Integer gn8_total) {
		this.gn8_total = gn8_total;
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
