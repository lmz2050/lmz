package cn.lmz.mike.admin.system.action;

import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmztpl;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller()
@Scope("prototype")
public class TplAction extends BaseAction {

	private static final long serialVersionUID = 134L;	
	private Lmztpl info = new Lmztpl();
	
	public String findAll()
	{
		try {
			infoL = getwService().search(getInfo().getClass());
			
			return jsonStr(infoL);
		} catch (Exception e) {	
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	public Lmztpl getInfo() {
		return info;
	}
	public void setInfo(BaseBean info) {
		this.info = (Lmztpl)info;
	}
	public void setInfo(Lmztpl info) {
		this.info = info;
	}

}
