package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.admin.business.bean.Adm_cs_info;
import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller()
@Scope("prototype")
public class CusAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected Adm_cs_info info = new Adm_cs_info();

	protected String phone;
	protected String name;
	protected String sex;
	protected String wechat;
	protected String mail;
	protected String job;

	public String basicInfo(){

		try {
			info.setPhone(phone);
			info.setName(name);
			info.setSex(sex);
			info.setWechat(wechat);
			info.setMail(mail);
			info.setJob(job);

			info.setUby("api");
			info.setUtm(MC.date.getTimeString());

			wService.create(info);

		} catch (LMZException e) {
			r.setSuccess(false);
			r.setMsg(e.getMessage());
			handException(e);
		}
		return null;

	}


	public Adm_cs_info getInfo() {
		return info;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Adm_cs_info)bean;
	}

	public void setInfo(Adm_cs_info info) {
		this.info = info;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}
