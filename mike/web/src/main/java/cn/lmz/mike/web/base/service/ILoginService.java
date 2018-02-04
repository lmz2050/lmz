package cn.lmz.mike.web.base.service;


import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;

public interface ILoginService extends IWService{

	//public Lmzuser getUserByOpenId(String openId,String type) throws LMZException;
	//public void loginSuccess(Lmzadmin u,Map session,String type) throws LMZException;
	//public void RegWithOpenId(Openuser ou,Map session,String type) throws LMZException;
	
	public Lmzadmin login(String username, String userpwd)  throws LMZException;
	public boolean findByUserName(String username)  throws LMZException;
	public BaseBean reg(Lmzadmin u)  throws LMZException;
	
}
