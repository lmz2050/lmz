package com.lmz.mike.auth.bean;

import lombok.Data;

import java.util.Date;

@Data
public class LmzUser extends BaseBean{

	protected String username;
	protected String userpwd;
	protected String phone;
	protected String email;
	protected Integer type;
	protected String token;
	protected String userjson;
	protected String extjson;
	protected String lastip;
	protected Date lastlogin;
	protected Integer active;

}