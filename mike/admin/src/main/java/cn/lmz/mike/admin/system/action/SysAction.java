package cn.lmz.mike.admin.system.action;

import cn.lmz.mike.admin.system.service.ISystemService;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.util.WebSV;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public abstract class SysAction extends BaseAction {

	@Resource
	protected ISystemService systemService;
	
	public void refreshMenu(){
		try{
			Lmzadmin admin = this.getAdmin();
			if(admin!=null){
				List<Map> mlist = systemService.getUserMenu(admin.getId()+"");
				this.getSession().put(WebSV.LTREE, mlist);
				for(int i=0;i<mlist.size();i++){
					O.pn(mlist.get(i).get("text"));
				}
			}
		}catch(Exception e){
			handException(e);
		}
	}
	
	


	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}
	
	
	

}
