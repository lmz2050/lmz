package cn.lmz.mike.web.base.action;


import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.json.JsonU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.bean.Return;
import cn.lmz.mike.web.base.util.CookieUtil;
import cn.lmz.mike.web.base.util.WebSV;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BasicAction  extends ActionSupport {
	
	private static final long serialVersionUID = -6248256901595349057L;
	
	protected Return r = new Return();
	protected String msg;
	protected String url;
	protected String id;
	protected String ids;
	protected String error;
	protected String json;
	protected String m;
	protected String utype;
	protected String rparam;

	public Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
    public HttpServletRequest getRequest()
    {
    	return ServletActionContext.getRequest();
    }
    public HttpServletResponse getResponse()
    {
    	HttpServletResponse responce = ServletActionContext.getResponse();
    	responce.setContentType("text/html;charset=UTF-8");
    	return responce;
    }
    public void write(String str) throws IOException{
    	HttpServletResponse responce = getResponse();
    	responce.setContentType("text/html;charset=UTF-8");
    	responce.getWriter().write(str);
    	responce.getWriter().flush();
    	responce.getWriter().close();
    }
    
    public Lmzadmin getAdmin(){
    	return getAdmin(WebSV.admin);
    }
    public Lmzadmin getAdmin(String key){
    	return (Lmzadmin)getSession().get(key);	
    } 
    protected void setAdmin(Lmzadmin admin,String remember) throws LMZException {
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
	protected void handException(Exception e){
		O.error(e.getMessage(),e);
		error="失败!";
	}	
	public String error(String msg,boolean re){
		try{
			getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter w = getResponse().getWriter();
			String msgstr="";
			if(!StrU.isBlank(msg)){
				msgstr="alert('"+msg+"');";
			}
			msgstr="<script>"+msgstr+"";
			if(re){
				msgstr+="window.location.href=document.referrer;";
			}
			msgstr+="</script>";
			w.write(msgstr);
			w.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String error(){
		return error(null,true);
	}	
	public String jsonStr(String rt,Object str){
		try{
			String re="";
			//O.pp("rt:"+rt+",json:"+json);
			if(!StrU.isBlank(json)||WebSV.JSON.equals(rt)){
				if(str==null)return null;
				re = JsonU.obj2json(str);
				O.pn(re);
				getResponse().setContentType("text/html;charset=utf-8");
				PrintWriter w = getResponse().getWriter();
				w.write(re);
				w.flush();
				w.close();
			}else{
				return rt;
			}
		}catch(Exception e){
			O.error(e.getMessage(),e);
		}
		return null;
	}
	public String jsonStr(Object str){
		return jsonStr(WebSV.JSON,str);
	}	
	public String jsonStr(){
		return jsonStr(WebSV.JSON,r);
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg){
		try {
			msg = URLDecoder.decode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.msg = msg;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	public Return getR() {
		return r;
	}
	public void setR(Return r) {
		this.r = r;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getRparam() {
		return rparam;
	}

	public void setRparam(String rparam) {
		this.rparam = rparam;
	}
}
