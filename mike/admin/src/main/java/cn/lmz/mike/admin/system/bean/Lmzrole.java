package cn.lmz.mike.admin.system.bean;


import cn.lmz.mike.data.annotation.LField;
import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean(name="lmzrole")
public class Lmzrole extends BaseBean {

	private String name;
	@LField(useForDb=false)
	private String aa;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}
	
}
