package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.admin.business.bean.Adm_drvices;
import cn.lmz.mike.admin.business.service.IdrvicesService;
import cn.lmz.mike.admin.business.util.AdmU;
import cn.lmz.mike.common.excel.Excel;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Return;
import cn.lmz.mike.web.base.upload.FileUploadProgress;
import cn.lmz.mike.web.base.util.WebSV;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller()
@Scope("prototype")
public class DrvicesAction extends BusBasAction{

	private static final Logger log = LoggerFactory.getLogger(DrvicesAction.class);

	private static final long serialVersionUID = 1L;
	protected Adm_drvices info = new Adm_drvices();
	@Resource
	private IdrvicesService drvicesServiceImpl;
	
	private String oem_type;
	private String deverce_name;
	private String wired_mac_name;
	private String wireless_mac_name;
	private String function_type;
	private boolean returm=false;

	public String apage()
	{
		try {
			if(page==null||page<1)page=1;
			if(rows==null)rows=10;
			Page pg = new Page();
			pg.setIntPageSize(rows);
			pg.setIntCurrentPage(page);

			Map params = getQyParams();
			Map pageParams = getApageParams();
			pageParams.putAll(params);

			PageUtil pu = getwService().search(getInfo().getClass(), pageParams, pg, getDefOrd());

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", pu.getPage().getIntRowCount());
			jsonMap.put("rows", pu.getList());

			return jsonStr(jsonMap);
		} catch (Exception e) {
			handException(e);
		}
		return WebSV.LOGIN;
	}

	public String getImportProgress() throws Exception {
		//System.out.println("获取上传文件进度信息");
		Map jsonData = new HashMap<>();//初始化jsonData!!!!
		String filename=id;
		FileUploadProgress p = AdmU.importMap.get(filename);
		if(null == p){
			jsonData.put("completed", false);
			jsonData.put("isStarted", false);
			return jsonStr(jsonData);
		}
		if(!StringUtils.isEmpty(p.getMsg())){
			jsonData.put("msg", p.getMsg());
			jsonData.put("isErrored", true);
			jsonData.put("isStarted", true);
			jsonData.put("completed",true);
			log.info("已异常，重置ImportProgress");
			AdmU.importMap.remove(filename);
			return jsonStr(jsonData);
		}

		long time = (System.currentTimeMillis() - p.getStartTime())/ 1000 + 1; //已传输的时间 单位:s
		double v = ((double)p.getCurrentLength()) / (double)time; // b/s
		int percent = (int)(100 * (double)p.getCurrentLength()/ (double)p.getTotalLength());

		jsonData.put("percent",percent);
		jsonData.put("remain", (int)(p.getTotalLength()/v-time));
		jsonData.put("isStarted", true);
		jsonData.put("completed", p.isFlag());
		if (p.isFlag()){
			log.info("已完成，重置ImportProgress");
			AdmU.importMap.remove(filename);
		}

		return jsonStr(jsonData);
	}

	public String importxls(){
		String filename=id;
		try{
			r.setSuccess(true);
			drvicesServiceImpl.importxls(filename,this.getAdmin());
		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg(e.getMessage());
			handException(e);
		}
		return jsonStr();
		
	}

	public String update(){
		try{

			r = drvicesServiceImpl.update(info, this.getAdmin().getUsername());

		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg(e.getMessage());
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	public String activate(){		
		try{
			//if(this.getAdmin().getUsername().equalsIgnoreCase(oem_type)) {

				Return re = drvicesServiceImpl.upActivate(wired_mac_name,wireless_mac_name, oem_type, function_type);
				if(re.isSuccess()){
					returm = true;
				}else{
					log.error(re.getMsg());
					returm = false;
				}
			//}else{
				//O.pn(AdmMsg.DIF_CUS_NAME+"{"+this.getAdmin().getUsername()+"}"+"{"+oem_type+"}");
				//returm = false;
			//}

			printXML(re);

		}catch (Exception e) {
			r.setSuccess(false);
			r.setMsg(e.getMessage());
			handException(e);
			returm = false;
		}
		log.info("activate:"+wired_mac_name+","+wireless_mac_name+","+oem_type+","+function_type+"==>"+returm);
		return null;
	}

	public Adm_drvices getInfo() {
		return info;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Adm_drvices)bean;
	}

	public void setInfo(Adm_drvices info) {
		this.info = info;
	}

	public IdrvicesService getDrvicesServiceImpl() {
		return drvicesServiceImpl;
	}

	public void setDrvicesServiceImpl(IdrvicesService drvicesServiceImpl) {
		this.drvicesServiceImpl = drvicesServiceImpl;
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

	public String getWired_mac_name() {
		return wired_mac_name;
	}

	public void setWired_mac_name(String wired_mac_name) {
		this.wired_mac_name = wired_mac_name;
	}

	public String getWireless_mac_name() {
		return wireless_mac_name;
	}

	public void setWireless_mac_name(String wireless_mac_name) {
		this.wireless_mac_name = wireless_mac_name;
	}

	public String getFunction_type() {
		return function_type;
	}

	public void setFunction_type(String function_type) {
		this.function_type = function_type;
	}

	public boolean isReturm() {
		return returm;
	}

	public void setReturm(boolean returm) {
		this.returm = returm;
	}
}
