package cn.lmz.mike.admin.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.common.sec.MD5U;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.bean.NotParams;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.util.LoginMsg;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



@Controller()
@Scope("prototype")
public class AdminAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected Lmzadmin info = new Lmzadmin();

	public String home()
	{
		try{
			
		}catch(Exception e){}
		return  WebSV.SUCCESS;
	}

	public String toUppwd(){
		try{
			info= this.getAdmin();
		}catch(Exception e){
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	public String apage()
	{
		try {
			if(page==null||page<1)page=1;
			if(rows==null)rows=0;
			Page pg = new Page();
			pg.setIntCurrentPage(page);
			
			String ord = " id asc ";

			Lmzadmin adm = this.getAdmin();
			DataBean b = new DataBean(getInfo().getClass());
			if(adm==null||0!=adm.getType()){
				b.put("type", new NotParams(0));
			}

			PageUtil pu = getwService().searchMap(b, pg, ord);
			
			Map<String, Object> jsonMap = new HashMap<String, Object>();
	        jsonMap.put("total", pu.getPage().getIntRowCount());
	        jsonMap.put("rows", pu.getList());
	        
	        return jsonStr(jsonMap);
		} catch (Exception e) {	
			handException(e);
		}
		return WebSV.LOGIN;
	}
	
	public String uppwd(){
		try{
			Lmzadmin admin1= this.getAdmin();
			if(admin1.getUserpwd().equals(MD5U.calc(info.getUsername()))){
				admin1.setUserpwd(MD5U.calc(info.getUserpwd()));
				try {
					getwService().update(admin1);
					r.setSuccess(true);
				} catch (Exception e) {		
					handException(e);
				}
			}else{
				r.setMsg(LoginMsg.INCORRECT_PWD);
				r.setSuccess(false);
		}
		}catch(Exception e){
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
//	public String reg(){
//		try {
//			List list = systemService.searchCO(getInfo().getClass(), "username",info.getUsername());
//			if(list!=null&&list.size()>0){
//				msg=MSG.EXIST;
//				return WebSV.SUCCESS;
//			}else{
//				info.setLastip(ServletActionContext.getRequest().getRemoteAddr());
//				info.setLastlogin(DateUtil.getCurrentDateTimeString());
//				O.pn(info.getUsername()+":"+info.getUsername());
//				info.setUserpwd(MD5Util.calc(info.getUserpwd()));
//				info.setType(0);
//				info=(Lmzadmin)systemService.create(info);
//				setAdmin(info,null);
//				msg=MSG.SUCCESS;
//				return WebSV.SUCCESS;
//			}
//		} catch (Exception e) {		
//			handException(e);
//		}
//
//		return WebSV.SUCCESS;		
//	}
	
	public String update(){
		try {
			if(info!=null){
				if(info.getType()==null){info.setType(2);}
				if(StrU.isBlank(info.getId())){
					List ulist = getwService().searchCO(Lmzadmin.class, "username",info.getUsername());
					if(ulist!=null&&ulist.size()>0){
						msg = LoginMsg.EXIST;
						r.setSuccess(false);
						r.setMsg(msg);
					}else{
						O.pn(info.getUsername()+":"+info.getUsername());
						info.setUserpwd(MD5U.calc(info.getUserpwd()));
						info= getwService().create(info);
						r.setSuccess(true);
					}
				}else{
					info.setUserpwd(MD5U.calc(info.getUserpwd()));
					getwService().update(info);
					r.setSuccess(true);
				}
			}
		} catch (LMZException e) {
			handException(e);
		}
		return WebSV.SUCCESS;
	}

	
	public String del(){
		try {
			if(id!=null){
				Lmzadmin admin = this.getAdmin();
				if(id==admin.getId()){
					msg="不能删除自己";
					r.setSuccess(false);
				}else{
					getwService().delete(Lmzadmin.class, id);
					msg="已删除";
					String sql=" delete from lmzroleuser where uid = ? ";
					getwService().execute(sql,id);
					r.setSuccess(true);
				}
				r.setMsg(msg);
			}
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}


	public Lmzadmin getInfo() {
		return info;
	}

	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Lmzadmin)bean;
	}

	public void setInfo(Lmzadmin info) {
		this.info = info;
	}
}
