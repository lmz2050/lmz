package cn.lmz.mike.admin.system.service;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.service.IWService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public interface ISystemService extends IWService {

	void setRoleMenu(String rid, String mids) throws LMZException;

	void setUserRole(String id, String msg) throws LMZException;
	
	List<Map> getUserMenu(String id, Map<String,Object> session) throws LMZException;

}
