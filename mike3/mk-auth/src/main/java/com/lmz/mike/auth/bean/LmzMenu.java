package com.lmz.mike.auth.bean;

import com.lmz.mike.data.annotation.LField;
import lombok.Data;

import java.util.List;

@Data
public class LmzMenu extends BaseBean{

	private String code;
	private String name;
	private String img;
	private String pid;
	private String sysId;
	private Integer lev;
	private String url;
	private Integer ord;
	private Integer isOp;
	private Integer isDev;
	private Integer active;
	private String httpMethod;
	private String extjson;

	@LField(useForDb = false)
	private String sysCode;
	@LField(useForDb = false)
	private List<LmzMenu> subMenus;

}
