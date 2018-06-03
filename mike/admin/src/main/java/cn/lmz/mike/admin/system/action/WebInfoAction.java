package cn.lmz.mike.admin.system.action;

import java.util.List;

import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.util.WebSV;
import cn.lmz.mike.web.bean.Lmzweb;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller()
@Scope("prototype")
public class WebInfoAction extends BaseAction {

	private Lmzweb info = new Lmzweb();

	public String webinfo(){
		try{
			List list =getwService().search(getInfo().getClass());
			if(list!=null&&list.size()>0){
				info = (Lmzweb)list.get(0);
			}
		}catch(Exception e){
			handException(e);
		}
		return WebSV.SUCCESS;
	}
	
	
	public Lmzweb getInfo() {
		return info;
	}
	public void setInfo(Lmzweb info) {
		this.info = info;
	}
	
	@Override
	protected void setInfo(BaseBean bean) {
		this.info = (Lmzweb)bean;
	}

}
