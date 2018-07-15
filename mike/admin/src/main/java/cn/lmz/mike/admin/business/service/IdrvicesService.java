package cn.lmz.mike.admin.business.service;

import cn.lmz.mike.admin.business.bean.Adm_drvices;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.bean.Return;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.upload.FileUploadProgress;

import java.util.List;


public interface IdrvicesService extends IWService {

	Return update(Adm_drvices info, String uname) throws Exception;
	void createList(List<Object[]> list, String uname,FileUploadProgress progress) throws Exception;
	
	Return upActivate(String mac1,String mac2, String uname, String function_type) throws Exception;

	boolean importxls(String filename,Lmzadmin admin) throws LMZException;



}
