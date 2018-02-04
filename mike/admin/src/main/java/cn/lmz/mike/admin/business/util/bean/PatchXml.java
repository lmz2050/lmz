package cn.lmz.mike.admin.business.util.bean;

import cn.lmz.mike.admin.business.bean.Adm_dev_patch;

public class PatchXml {

	private String id;
	private String Vname;
	private String Url;
	private String OkUpdata;
	private String Explain;
	
	
	public PatchXml(Adm_dev_patch ap, String downloadPath){
		this.Vname = ap.getVname();
		this.Url = ap.getUrl();
		this.OkUpdata = ap.getOkupdata();
		this.Explain = ap.getRemark();
	}
	
	public String toXmlString(){
		String str="<file>";
		str+="<Vname>"+Vname+"</Vname>";	   
		str+="<Url>"+Url+"</Url>";
		str+="<OkUpdata>"+OkUpdata+"</OkUpdata>";
		str+="<Explain>"+Explain+"</Explain>";
		str+="</file>";
		return str;
	}
	
	
	
	public String getVname() {
		return Vname;
	}
	public void setVname(String vname) {
		Vname = vname;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getOkUpdata() {
		return OkUpdata;
	}
	public void setOkUpdata(String okUpdata) {
		OkUpdata = okUpdata;
	}
	public String getExplain() {
		return Explain;
	}
	public void setExplain(String explain) {
		Explain = explain;
	}
}
