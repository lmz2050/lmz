package cn.lmz.mike.web.base.action;

import cn.lmz.mike.common.date.DateU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.BeanUtil;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.bean.Lmztheme;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.util.CookieUtil;
import cn.lmz.mike.web.base.util.WebSV;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


public abstract class BaseAction extends BasicAction{

	private static final long serialVersionUID = 2671098963181654417L;
	protected String TPATH;
	protected static String THEME;
	protected Integer page;
	protected Integer rows;
	@Resource
	protected IWService wService;
	protected List infoL =new ArrayList();
	protected PageUtil pu = new PageUtil();
	
	protected abstract BaseBean getInfo();
	protected abstract void setInfo(BaseBean bean);

	public String getRA(String rt,Object str){
		getTplPath();
		String rtt=jsonStr(rt,str);
		if(rtt!=null){
			if(!StrU.isBlank(url)){
				if(url.contains(",")){
					url = url.split(",")[0];
				}
				if(url.contains("@")){
					url = url.replaceAll("@id@", id);
				}
				O.pb(url);
				url = URLDecoder.decode(url);
				O.pb(url);
				if(!StrU.isBlank(utype)){
					return WebSV.RURL;
				}
				return WebSV.URL;
			}
		}
		return rt;
	}
	public void getTplPath(){
		if(THEME==null){
			try{
				List tlist = wService.search(Lmztheme.class,null,null," ord ").getList();
				if(tlist!=null&&tlist.size()>0){
					for(int i=0;i<tlist.size();i++){
						Lmztheme lt = (Lmztheme)tlist.get(i);
						if("Y".equalsIgnoreCase(lt.getDef())){
							THEME = lt.getName();break;
						}
					}
					if(THEME==null){
						Lmztheme lt = (Lmztheme)tlist.get(0);
						THEME = lt.getName();
					}
				}
				O.pn("THEME:"+THEME);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(THEME==null)THEME="";
		}
		if(!StrU.isBlank(THEME)){
			TPATH="/tpl/"+THEME+"/";
			if("1".equals(m)){
				TPATH=TPATH+"mobile";
			}else if(!StrU.isBlank(m)){
				TPATH=TPATH+m;
			}else{
				TPATH=TPATH+"web";
			}
		}
	}

	protected String getDefOrd(){
		return " id+0 asc ";
	}
	
	public String toAdd(){
		return WebSV.SUCCESS;
	}
	public String toUpdate(){
		try {
			if(id!=null){
				setInfo(getwService().search(getInfo().getClass(), id));
			}
		} catch (LMZException e) {
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	public String update(){
		try {
			if(getInfo()!=null){
				BeanUtil.setBean(getInfo(), LmzU.getParams("uby",this.getAdmin().getUsername(),"utm", DateU.getCurrentDateTimeString()));
				if(StrU.isBlank(getInfo().getId())){
					BeanUtil.setBean(getInfo(), LmzU.getParams("cby",this.getAdmin().getUsername(),"ctm",DateU.getCurrentDateTimeString()));
					setInfo(getwService().create(getInfo()));
					msg="add";
				}else{
					getwService().update(getInfo());
					msg="update";
				}
				r.setMsg(msg);
				r.setSuccess(true);
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
				r.setMsg(WebSV.SUCCESS);
				r.setSuccess(true);
			}
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}	
	
	public String apage()
	{
		try {
			if(page==null||page<1)page=1;
			if(rows==null)rows=0;
			Page pg = new Page();
			pg.setIntCurrentPage(page);
			
			String ord = getDefOrd();
			
			PageUtil pu = getwService().search(getInfo().getClass(), null, pg, ord);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
	        jsonMap.put("total", pu.getPage().getIntRowCount());
	        jsonMap.put("rows", pu.getList());
	        
	        return jsonStr(jsonMap);
		} catch (Exception e) {	
			handException(e);
		}
		return WebSV.LOGIN;
	}
	
	public String findAll()
	{
		try {
			infoL = getwService().search(getInfo().getClass());
		} catch (Exception e) {	
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	protected void setAdmin(Lmzadmin admin, String remember) throws LMZException{
		getSession().put(WebSV.admin, admin);

		if("1".equals(remember)){
			CookieUtil.setCookie(WebSV.NAME_PWD, admin.getUsername()+WebSV.SPX+admin.getUserpwd());
		}
	}
	protected void removeAdmin() throws LMZException{
		CookieUtil.delCookie(WebSV.NAME_PWD);
		getSession().put(WebSV.admin, null);
		getSession().remove(WebSV.admin);
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public List getInfoL() {
		return infoL;
	}
	public void setInfoL(List infoL) {
		this.infoL = infoL;
	}
	public PageUtil getPu() {
		return pu;
	}
	public void setPu(PageUtil pu) {
		this.pu = pu;
	}
	public IWService getwService() {
		return wService;
	}
	public void setwService(IWService wService) {
		this.wService = wService;
	}

	
}
