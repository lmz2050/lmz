package cn.lmz.mike.admin.system.action;

import java.util.List;
import java.util.Map;


import cn.lmz.mike.admin.system.bean.Lmzmenu;
import cn.lmz.mike.admin.system.bean.Lmzrolemenu;
import cn.lmz.mike.admin.system.util.SysU;
import cn.lmz.mike.admin.system.util.ltree.LTreeU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


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
	public String getRoleMenu()
	{
		try{
			PageUtil pu=getwService().searchMap(new DataBean(getInfo().getClass()),null,null);
			List<Map> tlist = LTreeU.convertTreeNodeList(pu.getList());
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
			
			infoL = LTreeU.getEasyList(getwService(), info.getClass().getSimpleName(), null);
			
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
