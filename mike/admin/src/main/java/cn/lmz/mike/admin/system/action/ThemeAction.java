package cn.lmz.mike.admin.system.action;


import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmztheme;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



@Controller()
@Scope("prototype")
public class ThemeAction extends BaseAction {

	private static final long serialVersionUID = 134L;	
	private Lmztheme info = new Lmztheme();
	private Integer ord;
	
	public String upOrd(){
		try {
			info = getwService().search(getInfo().getClass(), id);
			info.setOrd(ord);
			getwService().update(info);
			r.setSuccess(true);
		} catch (LMZException e) {
			handException(e);
		}
		return jsonStr();
	}

	public Lmztheme getInfo() {
		return info;
	}
	public void setInfo(BaseBean info) {
		this.info = (Lmztheme)info;
	}
	public void setInfo(Lmztheme info) {
		this.info = info;
	}


	public Integer getOrd() {
		return ord;
	}


	public void setOrd(Integer ord) {
		this.ord = ord;
	}

}
