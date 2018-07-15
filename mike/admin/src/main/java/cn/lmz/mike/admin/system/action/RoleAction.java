package cn.lmz.mike.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.lmz.mike.admin.system.bean.Lmzrole;
import cn.lmz.mike.admin.system.bean.Lmzroleuser;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.bean.NotParams;
import cn.lmz.mike.data.bean.OrParams;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.util.WebSV;
import com.mysql.fabric.xmlrpc.base.Params;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



@Controller()
@Scope("prototype")
public class RoleAction extends SysAction{

	private static final long serialVersionUID = 134L;	
	private Lmzrole info = new Lmzrole();

	public String apage()
	{
		try {
			if(page==null||page<1)page=1;
			if(rows==null)rows=10;
			Page pg = new Page();
			pg.setIntPageSize(rows);
			pg.setIntCurrentPage(page);

			String ord = getDefOrd();

			Map paramss = getApageParams();
			paramss.put("id", new NotParams(0));
			PageUtil pu = getwService().search(getInfo().getClass(), paramss, pg, ord);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", pu.getPage().getIntRowCount());
			jsonMap.put("rows", pu.getList());

			return jsonStr(jsonMap);
		} catch (Exception e) {
			handException(e);
		}
		return WebSV.LOGIN;
	}

	public String del(){
		try {
			if(id!=null){

				Lmzadmin admin = this.getAdmin();

				if(id.compareTo(admin.getType()+"")<=0){
					msg=getText("login.msg.without_permission");
					r.setSuccess(false);
					r.setMsg(msg);
				}else {
					systemService.delete(Lmzrole.class, id);
					msg = "delete";
					String sql = " delete from lmzrolemenu where rid = ? ";
					systemService.execute(sql, id);
					refreshMenu();
					r.setSuccess(true);
					r.setMsg(msg);
				}
			}
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}
	
	public String setRoleMenu(){
		try {
			if(id!=null){
				Lmzadmin admin = this.getAdmin();

				if(id.compareTo(admin.getType()+"")<=0){
					msg=getText("login.msg.without_permission");
					r.setSuccess(false);
					r.setMsg(msg);
				}else {
					systemService.setRoleMenu(id, msg);
					refreshMenu();
					r.setSuccess(true);
				}
			}
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}	
	
	public String findUserRole()
	{
		try {
			DataBean db = new DataBean(Lmzrole.class);
			db.put("id", new NotParams(0));
			infoL = systemService.searchObj(db, null, null).getList();

			DataBean db1 = new DataBean(Lmzroleuser.class);
			db1.put("uid",id);
			
			List rlist = systemService.searchObj(db1, null, null).getList();
					//.searchCO(Lmzroleuser.class,"uid",id);
			String rids = "@";
			if(rlist!=null&&rlist.size()>0){
				for(int i=0;i<rlist.size();i++){
					Lmzroleuser ru = (Lmzroleuser)rlist.get(i);
					rids+=ru.getRid()+"@";
				}
			}
			List<Map> nlist = new ArrayList<Map>();
			if(infoL!=null&&infoL.size()>0){
				for(int i=0;i<infoL.size();i++){
					Lmzrole r = (Lmzrole)infoL.get(i);
					Map node = new HashMap();
					node.put("id",r.getId()+"");
					node.put("text",r.getName());
					if(rids.contains(r.getId()+"")){
						node.put("checked", true);
					}else{
						node.put("checked", false);
					}
					nlist.add(node);
				}
			}
	        return jsonStr(nlist);
		} catch (Exception e) {	
			handException(e);
		}
		return null;
	}
	
	public String setUserRole(){
		try {
			if(id!=null){
				Lmzadmin admin = this.getAdmin();

				Lmzadmin updmin = systemService.search(Lmzadmin.class,id);
				if (updmin != null&&updmin.getType().compareTo(1)<=0&&updmin.getType()<=admin.getType()) {
					msg=getText("login.msg.without_permission");
					r.setSuccess(false);
					r.setMsg(msg);
				}else {
					systemService.setUserRole(id, msg);
					refreshMenu();
					r.setSuccess(true);
				}
			}
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}

	public Lmzrole getInfo() {
		return info;
	}

	public void setInfo(BaseBean info) {
		this.info = (Lmzrole)info;
	}

	public void setInfo(Lmzrole info) {
		this.info = info;
	}
	
}
