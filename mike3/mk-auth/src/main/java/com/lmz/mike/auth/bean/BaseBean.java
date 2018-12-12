package com.lmz.mike.auth.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseBean implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	
	protected String id;

	protected String ctBy;
	protected Date ctDate;
	protected String upBy;
	protected Date upDate;

	@Override
	public BaseBean clone(){
		try {
			return (BaseBean)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
