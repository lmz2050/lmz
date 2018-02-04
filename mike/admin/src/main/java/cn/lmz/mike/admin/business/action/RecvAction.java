package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.admin.business.bean.Adm_ol_recv;
import cn.lmz.mike.admin.business.util.AdmU;
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
public class RecvAction extends BusBasAction {

	private static final long serialVersionUID = 1L;
	protected Adm_ol_recv info = new Adm_ol_recv();

	private String oem_type;
	private String deverce_name;
	private String mac_name;
	private String version;
	private String login_time;
	private String online_times;
	private String gn_1_times;
	private String gn_2_times;
	private String gn_3_times;
	private String gn_4_times;
	private String gn_5_times;
	private String gn_6_times;
	private String gn_7_times;
	private String gn_8_times;


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
	public String receiveOnlineInfo(){

		try {
			List list = wService.search(Adm_ol_recv.class, LmzU.getParams("cus_name",oem_type,"dev_name",deverce_name,"mac1",mac_name,"ol_time",login_time));
			if(list!=null&&list.size()>0){
				info = (Adm_ol_recv)list.get(0);
			}else{
				info.setCus_name(oem_type);
				info.setDev_name(deverce_name);
				info.setMac1(mac_name);
				info.setVersion(version);
				info.setOl_time(login_time);
			}

			info.setOl_total(AdmU.addInteger(info.getOl_total(),online_times));
			info.setGn1_total(AdmU.addInteger(info.getGn1_total(),gn_1_times));
			info.setGn2_total(AdmU.addInteger(info.getGn2_total(),gn_2_times));
			info.setGn3_total(AdmU.addInteger(info.getGn3_total(),gn_3_times));
			info.setGn4_total(AdmU.addInteger(info.getGn4_total(),gn_4_times));
			info.setGn5_total(AdmU.addInteger(info.getGn5_total(),gn_5_times));
			info.setGn6_total(AdmU.addInteger(info.getGn6_total(),gn_6_times));
			info.setGn7_total(AdmU.addInteger(info.getGn7_total(),gn_7_times));
			info.setGn8_total(AdmU.addInteger(info.getGn8_total(),gn_8_times));


			if(StrU.isBlank(getInfo().getId())){

				info.setUby("api");
				info.setUtm(DateU.getTimeString());

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


	public Adm_ol_recv getInfo() {
		return info;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Adm_ol_recv)bean;
	}

	public void setInfo(Adm_ol_recv info) {
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

	public String getMac_name() {
		return mac_name;
	}

	public void setMac_name(String mac_name) {
		this.mac_name = mac_name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getGn_1_times() {
		return gn_1_times;
	}

	public void setGn_1_times(String gn_1_times) {
		this.gn_1_times = gn_1_times;
	}

	public String getGn_2_times() {
		return gn_2_times;
	}

	public void setGn_2_times(String gn_2_times) {
		this.gn_2_times = gn_2_times;
	}

	public String getGn_3_times() {
		return gn_3_times;
	}

	public void setGn_3_times(String gn_3_times) {
		this.gn_3_times = gn_3_times;
	}

	public String getGn_4_times() {
		return gn_4_times;
	}

	public void setGn_4_times(String gn_4_times) {
		this.gn_4_times = gn_4_times;
	}

	public String getGn_5_times() {
		return gn_5_times;
	}

	public void setGn_5_times(String gn_5_times) {
		this.gn_5_times = gn_5_times;
	}

	public String getGn_6_times() {
		return gn_6_times;
	}

	public void setGn_6_times(String gn_6_times) {
		this.gn_6_times = gn_6_times;
	}

	public String getGn_7_times() {
		return gn_7_times;
	}

	public void setGn_7_times(String gn_7_times) {
		this.gn_7_times = gn_7_times;
	}

	public String getGn_8_times() {
		return gn_8_times;
	}

	public void setGn_8_times(String gn_8_times) {
		this.gn_8_times = gn_8_times;
	}
}
