package cn.lmz.mike.web.base.bean;

import cn.lmz.mike.common.invoke.InvokeBean;

import java.io.Serializable;

public class BaseBean implements Serializable,InvokeBean,Cloneable{

	private static final long serialVersionUID = 1L;
	
	protected String id;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public BaseBean clone(){
		try {
			return (BaseBean)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
