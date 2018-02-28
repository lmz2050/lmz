package cn.lmz.mike.web.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Lmzweb extends BaseBean {

	private String domain;
	private String mdomain;
	private String name;
	private String logo;
	private String titles;
	private String keywords;
	private String explains;
	private String phone;
	private String tel;
	private String addr;
	private Integer disabled;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMdomain() {
		return mdomain;
	}
	public void setMdomain(String mdomain) {
		this.mdomain = mdomain;
	}
	
}
