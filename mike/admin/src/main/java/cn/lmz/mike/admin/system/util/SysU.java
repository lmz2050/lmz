package cn.lmz.mike.admin.system.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.bean.Lmzweb;
import org.apache.struts2.ServletActionContext;

public class SysU {

	public static String DOMAIN=null;
	public static String MDOMAIN=null;
	
	public static String getIdString(List<BaseBean> list){
		String mids="";
		if(list!=null){
			mids+="@";
			for(BaseBean b:list){
				mids+=b.getId()+"@";
			}
		}
		return mids;
	}
	
	

	public static String getDomain(IWService service){
		return getDomain(service, null);
	}
	public static String getDomain(IWService service,String m){
		if(DOMAIN==null){
			HttpServletRequest request  =  ServletActionContext.getRequest();
			String serverName =request.getServerName();	
			int port  = request.getLocalPort();
			String contextPath = request.getContextPath();	
			String rt="http://"+serverName+":"+port+contextPath+"/";
			String rtm="http://"+serverName+":"+port+contextPath+"/";
			try{
				List list = service.search(Lmzweb.class);
				if(list!=null&&list.size()>0){
					Lmzweb w = (Lmzweb)list.get(0);
					if(w!=null&&w.getDomain()!=null){
						rt = "http://"+w.getDomain()+contextPath+"/";
						rtm = "http://"+w.getMdomain()+contextPath+"/";
					}
				}
			}catch(Exception e){e.printStackTrace();}
			DOMAIN = rt;
			MDOMAIN=rtm;
			O.debug(DOMAIN);
			O.debug(MDOMAIN);
		}
		if(StrU.isBlank(m)){
			return DOMAIN;
		}else{
			return MDOMAIN;
		}
	}
}
