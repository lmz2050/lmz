package cn.lmz.mike.admin.system.service.impl;

import cn.lmz.mike.admin.system.bean.Lmzmenu;
import cn.lmz.mike.admin.system.bean.Lmzrolemenu;
import cn.lmz.mike.admin.system.bean.Lmzroleuser;
import cn.lmz.mike.admin.system.service.ISystemService;
import cn.lmz.mike.admin.system.util.ltree.LTreeU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.util.LanU;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.web.base.service.impl.WService;
import cn.lmz.mike.web.base.util.WebSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SystemService extends WService implements ISystemService {

	private static final Logger log = LoggerFactory.getLogger(SystemService.class);

	private Set<String> findMenuAndParentId(Lmzmenu m) throws LMZException {
		Set<String> mpidSet = new HashSet<String>();
		if(m!=null){
			mpidSet.add(m.getId());
			if(!StrU.isBlank(m.getPid())&&!"0".equalsIgnoreCase(m.getPid())){
				mpidSet.add(m.getPid());
				Lmzmenu mi = search(Lmzmenu.class, m.getPid());
				mpidSet.addAll(findMenuAndParentId(mi));
			}
		}
		return mpidSet;
	}
	
	
	public void setRoleMenu(String rid,String midss) throws LMZException{
		if(rid!=null){
			delete(Lmzrolemenu.class, LmzU.getParams("rid",rid));
			String[] mids = midss.split(",");
			for(int i=0;i<mids.length;i++){
				Lmzrolemenu rm = new Lmzrolemenu();
				rm.setMid(mids[i]);
				rm.setRid(rid);
				create(rm);
			}
			/*
			Set<String> mpidSet = new HashSet<String>();
			//后面 easyui tree实现
			for(int i=0;i<mids.length;i++){
				Lmzmenu m = (Lmzmenu)search(Lmzmenu.class, mids[i]);
				if(m!=null){
					mpidSet.addAll(findMenuAndParentId(m));
				}
			}

			for(String mid:mpidSet){
				Lmzrolemenu rm = new Lmzrolemenu();
				rm.setMid(mid);
				rm.setRid(rid);
				create(rm);
			}
			*/
		}
	}
	public void setUserRole(String uid,String midss) throws LMZException{
		if(uid!=null){
			delete(Lmzroleuser.class, LmzU.getParams("uid",uid));
			String[] mids = midss.split(",");
			for(int i=0;i<mids.length;i++){
				Lmzroleuser rm = new Lmzroleuser();
				rm.setRid(mids[i]);
				rm.setUid(uid);
				create(rm);
			}
		}
	}
	
	public List<Map> getUserMenu(String id,Map<String,Object> session) throws LMZException{
		String sql=" select m.* from lmzmenu m where m.id in(select mid from lmzrolemenu where rid in(select rid from lmzroleuser where uid=?)) order by m.ord ";
		PageUtil pu = searchMap(sql, null, id);
		List<Map> btnlist = new ArrayList<Map>();

		Locale locale = LanU.getLocale(session);
		List<Map> tlist = LTreeU.convertTreeNodeList(pu.getList(),btnlist,locale);
		tlist =	LTreeU.buildTree(tlist, 0);
		for(int i=0;i<tlist.size();i++){
			log.info(tlist.get(i).get("text")+"");
		}

		session.put(WebSV.LTREE,tlist);
		session.put(WebSV.BTNLIST,btnlist);
		return tlist;
	}
}
