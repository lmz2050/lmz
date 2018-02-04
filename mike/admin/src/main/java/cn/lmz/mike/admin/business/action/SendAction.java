package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.admin.business.bean.Adm_ol_send;
import cn.lmz.mike.admin.business.util.AdmU;
import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller()
@Scope("prototype")
public class SendAction extends BusBasAction {

	private static final long serialVersionUID = 1L;
	protected Adm_ol_send info = new Adm_ol_send();

	private String oem_type;
	private String deverce_name;
	private String system_name;
	private String system_version;
	private String app_version;
	private String login_time;
	private String online_times;


	public String apage()
	{
		try {
			if(page==null||page<1)page=1;
			if(rows==null)rows=0;
			Page pg = new Page();
			pg.setIntCurrentPage(page);

			Map params = getQyParams();

			PageUtil pu = getwService().search(getInfo().getClass(), params, pg, getDefOrd());

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", pu.getPage().getIntRowCount());
			jsonMap.put("rows", pu.getList());

			return jsonStr(jsonMap);
		} catch (Exception e) {
			handException(e);
		}
		return WebSV.LOGIN;
	}

	public String sendOnlineInfo(){


		try {
			List list = wService.search(Adm_ol_send.class, LmzU.getParams("cus_name",oem_type,"dev_name",deverce_name,"sysname",system_name,"ol_time",login_time));
			if(list!=null&&list.size()>0){
				info = (Adm_ol_send)list.get(0);
			}else{
				info.setCus_name(oem_type);
				info.setDev_name(deverce_name);
				info.setSysname(system_name);
				info.setSysversion(system_version);
				info.setAppversion(app_version);
				info.setOl_time(login_time);
			}

			info.setOl_total(AdmU.addInteger(info.getOl_total(),online_times));

			if(StrU.isBlank(getInfo().getId())){
				info.setUby("api");
				info.setUtm(MC.date.getTimeString());
				setInfo(getwService().create(getInfo()));
				msg="add";
			}else{
				getwService().update(getInfo());
				msg="update";
			}

		} catch (LMZException e) {
			r.setSuccess(false);
			r.setMsg(e.getMessage());
			handException(e);
		}
		return null;


	}




	public Adm_ol_send getInfo() {
		return info;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Adm_ol_send)bean;
	}

	public void setInfo(Adm_ol_send info) {
		this.info = info;
	}

	public String getOem_type() {
		return oem_type;
	}

	public void setOem_type(String oem_type) {
		this.oem_type = oem_type;
	}

	public String getDeverce_name() {
		return deverce_name;
	}

	public void setDeverce_name(String deverce_name) {
		this.deverce_name = deverce_name;
	}

	public String getSystem_name() {
		return system_name;
	}

	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}

	public String getSystem_version() {
		return system_version;
	}

	public void setSystem_version(String system_version) {
		this.system_version = system_version;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getOnline_times() {
		return online_times;
	}

	public void setOnline_times(String online_times) {
		this.online_times = online_times;
	}
}
