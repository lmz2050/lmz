package cn.lmz.mike.admin.business.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Adm_dev_patch extends BaseBean {

	protected String vname;
	protected String url;
	protected String okupdata;
	protected String remark;

	protected String cby;
	protected String ctm;
	protected String uby;
	protected String utm;
	
	
	public StringBuilder toXmlString(){
		StringBuilder sb = new StringBuilder();
		sb.append("<file>");
		sb.append("<Vname>"+vname+"</Vname>");	   
		sb.append("<Url>"+url+"</Url>");
		sb.append("<OkUpdata>"+okupdata+"</OkUpdata>");
		sb.append("<Explain>"+remark+"</Explain>");
		sb.append("</file>");
		return sb;
	}
	
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOkupdata() {
		return okupdata;
	}
	public void setOkupdata(String okupdata) {
		this.okupdata = okupdata;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
