package cn.lmz.mike.web.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.web.base.bean.Lmztpl;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.util.WebSV;
import cn.lmz.mike.web.base.util.WebU;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller()
@Scope("prototype")
public class LmzAction extends BasicAction {

	protected Map p = new HashMap();
	protected String rparam;
	protected Lmztpl t = new Lmztpl();
			
	@Resource
	protected IWService wService;
	
	public String l(){
		try{
			Lmztpl tpl = null;
			if(!StrU.isBlank(t.getName())){
				tpl = WebU.tplMap.get(t.getName());
			}
			if(tpl==null){
				List tlist = wService.search(Lmztpl.class, LmzU.getParams("name",t.getName()));
				if(tlist!=null&&tlist.size()>0){
					tpl = (Lmztpl)tlist.get(0);
					WebU.tplMap.put(t.getName(), tpl);
				}
			}
			if(tpl!=null){
				t=(Lmztpl)tpl.clone();
			}
			if(t.getUrl().contains(",")){
				t.setUrl(t.getUrl().split(",")[0]);
			}
			/*
			if(t.getUrl().indexOf(".jsp")!=-1){
				t.setUrl(WebU.getTplRoot(serviceWeb, getSession(), m)+WebU.getString(t.getUrl()));
			}
			if(p.containsKey("page")){
				String page=WebU.getString(p.get("page"));
				p.put("pg.intCurrentPage", page);
				p.remove("page");
			}
			*/
			rparam = LmzU.getMapParam(p);
			if(p.containsKey("url")){
				t.setUrl(WebU.getString(p.get("url")));
			}
			
			if(!StrU.isBlank(t.getAction())){
				String atype = t.getAtype();
				if(!StrU.isBlank(atype)){
					return WebSV.RACTION;
				}else{
					return WebSV.ACTION;
				}
			}
			
			String utype=t.getUtype();
			if(!StrU.isBlank(utype)){
				return WebSV.RURL;
			}else{
				return WebSV.URL;
			}
			
		}catch(Exception e){
			handException(e);
		}
		return WebSV.URL;
	}

	public Map getP() {
		return p;
	}

	public void setP(Map p) {
		this.p = p;
	}

	public String getRparam() {
		return rparam;
	}

	public void setRparam(String rparam) {
		this.rparam = rparam;
	}

	public Lmztpl getT() {
		return t;
	}

	public void setT(Lmztpl t) {
		this.t = t;
	}

	public IWService getwService() {
		return wService;
	}

	public void setwService(IWService wService) {
		this.wService = wService;
	}


}
