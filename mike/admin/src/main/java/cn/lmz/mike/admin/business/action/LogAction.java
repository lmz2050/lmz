package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.admin.business.bean.Adm_dev_log;
import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



@Controller()
@Scope("prototype")
public class LogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected Adm_dev_log info = new Adm_dev_log();

	private String oem_type;
	private String deverce_name;
	//private String mac_name;
	private String version;
	private String current_time;
	private String log;

	public String erorrLog(){

		info.setCus_name(oem_type);
		info.setDev_name(deverce_name);
		//info.setMac1(mac_name);
		info.setVersion(version);
		info.setUby("api");
		info.setUtm(DateU.getTimeString());
		info.setContent(log);

		try {
			wService.create(info);
		} catch (LMZException e) {
			r.setSuccess(false);
			r.setMsg(e.getMessage());
			handException(e);
		}

		return null;
	}

	public Adm_dev_log getInfo() {
		return info;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Adm_dev_log)bean;
	}

	public void setInfo(Adm_dev_log info) {
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(String current_time) {
		this.current_time = current_time;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
}
