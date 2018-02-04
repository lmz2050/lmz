package cn.lmz.mike.web.bean;

import cn.lmz.mike.data.annotation.LField;
import cn.lmz.mike.web.base.bean.Lmzadmin;

import java.math.BigDecimal;



public class Lmzuser extends Lmzadmin {
	
	private String lev;
	@LField(useForDb=false)
	private String levname;
	private String ischeked;
	private Integer logincount;
	private BigDecimal damt;
	
	private String name;
	private String sex;
	private String birthday;
	private String prov;
	private String city;
	private String region;
	@LField(useForDb=false)
	private String provname;
	@LField(useForDb=false)
	private String cityname;
	@LField(useForDb=false)
	private String regionname;
	private String address;
	private String zip;
	private String mobile;
	private String tel;

	private Integer point;
	private Integer pointused;
	private String qq;
	private String msn;
	private String remark;
	private String wechat;
	private String cby;
	private String ctm;
	
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getLevname() {
		return levname;
	}
	public void setLevname(String levname) {
		this.levname = levname;
	}
	public String getIscheked() {
		return ischeked;
	}
	public void setIscheked(String ischeked) {
		this.ischeked = ischeked;
	}
	public BigDecimal getDamt() {
		return damt;
	}
	public void setDamt(BigDecimal damt) {
		this.damt = damt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getPointused() {
		return pointused;
	}
	public void setPointused(Integer pointused) {
		this.pointused = pointused;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Integer getLogincount() {
		return logincount;
	}
	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getCby() {
		return cby;
	}
	public void setCby(String cby) {
		this.cby = cby;
	}
	public String getCtm() {
		return ctm;
	}
	public void setCtm(String ctm) {
		this.ctm = ctm;
	}
	
}
