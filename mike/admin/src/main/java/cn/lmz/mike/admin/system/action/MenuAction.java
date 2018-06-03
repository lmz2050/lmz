package cn.lmz.mike.admin.system.action;

import cn.lmz.mike.admin.system.bean.Lmzmenu;
import cn.lmz.mike.admin.system.bean.Lmzrolemenu;
import cn.lmz.mike.admin.system.util.SysU;
import cn.lmz.mike.admin.system.util.ltree.LTreeU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.util.LanU;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller()
@Scope("prototype")
public class MenuAction extends SysAction{

	private static final long serialVersionUID = 134L;	
	private Lmzmenu info = new Lmzmenu();

	public String home()
	{
		try{
			
		}catch(Exception e){}
		return  WebSV.SUCCESS;
	}
	
	public String getUserMenu()
	{
		infoL=(List)this.getSession().get(WebSV.LTREE);
		if(StrU.isBlank(url)){url = WebSV.SUCCESS;}
		if(WebSV.JSON.equalsIgnoreCase(url)){
			return jsonStr(infoL);
		}
		return  url;
	}



	public String getBtn(){
		List btnlist=(List)this.getSession().get(WebSV.BTNLIST);
		String msg="";
		if(btnlist!=null&&btnlist.size()>0) {
			for (int i=0;i<btnlist.size();i++) {
				Map m = (Map) btnlist.get(i);
				String pid = (String)m.get("pid");
				String type = m.get("type")+"";
				if(id!=null&&id.equalsIgnoreCase(pid)&&"1".equalsIgnoreCase(type)){
					msg+=m.get("url")+"@";
				}
			}
		}
		r.setSuccess(true);
		r.setObj(msg);
		return jsonStr();
	}
	public String getRoleMenu()
	{
		try{
			DataBean db = new DataBean(getInfo().getClass());
			if(!"0".equalsIgnoreCase(this.getAdmin().getId())){
				db.setParams("isdev",0);
			}
			PageUtil pu=getwService().searchMap(db,null,null);
			List<Map> tlist = LTreeU.convertTreeNodeList(pu.getList(),null, LanU.getLocale(getSession()));
			tlist =	LTreeU.buildTree(tlist, 0);
			if(id!=null){
				String sql=" select m.* from lmzmenu m where m.id in(select mid from lmzrolemenu where rid =?) order by m.ord ";
				PageUtil pu1 = getwService().searchObj(getInfo().getClass(),sql, null, id);
				String mids= SysU.getIdString(pu1.getList());
				LTreeU.convertWithRole(tlist, mids);
			}
			return jsonStr(tlist);
		}catch(Exception e){
			handException(e);
		}
		return null;
	}	

	
	public String toAdd(){
		if(id!=null){
			info.setPid(id);
		} 
		return WebSV.SUCCESS;
	}
	public String update(){
		try {
			if(info!=null){
				if(info.getOrd()==null){
					info.setOrd(0);
				}
				if(StrU.isBlank(info.getUrl())){
					info.setUrl("#");
				}
				if(info.getType()==null){
					info.setType(0);
				}
				if(info.getIsdev()==null){
					info.setIsdev(0);
				}
				Lmzmenu pm = getwService().search(getInfo().getClass(),info.getPid());
				if(pm!=null){
					info.setLev(pm.getLev()+1);
				}else{
					info.setLev(0);
				}
				if(StrU.isBlank(info.getId())){
					info = getwService().create(info);
					Lmzrolemenu rm = new Lmzrolemenu();
					rm.setMid(info.getId()+"");
					rm.setRid("0");
					getwService().create(rm);
					msg="add";
				}else{
					getwService().update(info);
					msg="update";
				}
				refreshMenu();
				r.setSuccess(true);
				r.setMsg(msg);
			}
		} catch (LMZException e) {
			handException(e);
		}
		return WebSV.SUCCESS;
	}

	
	public String del(){
		try {
			if(id!=null){
				getwService().delete(getInfo().getClass(), id);
				msg="delete";
				String sql=" delete from lmzrolemenu where mid = ? ";
				getwService().execute(sql,id);
				refreshMenu();
				r.setSuccess(true);
				r.setMsg(msg);
			}
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}	
	
	public String findAll()
	{
		try{
			
			infoL = LTreeU.getEasyList(getwService(), info.getClass().getSimpleName(), null,getSession());

			jsonStr(infoL);
		}catch(Exception e){
			handException(e);
		}
		return jsonStr();
	}

	public Lmzmenu getInfo() {
		return info;
	}
	public void setInfo(BaseBean info) {
		this.info = (Lmzmenu)info;
	}
	public void setInfo(Lmzmenu info) {
		this.info = info;
	}

}
