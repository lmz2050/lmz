package cn.lmz.mike.admin.business.service;

import cn.lmz.mike.admin.business.bean.Adm_drvices;
import cn.lmz.mike.web.base.bean.Return;
import cn.lmz.mike.web.base.service.IWService;

import java.util.List;


public interface IdrvicesService extends IWService {

	Return update(Adm_drvices info, String uname) throws Exception;
	void createList(List<Object[]> list, String uname) throws Exception;
	
	Return upActivate(String mac1,String mac2, String uname, String function_type) throws Exception;
}
