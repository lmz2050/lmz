package cn.lmz.mike.web.base.util;

import cn.lmz.mike.data.util.LanU;
import com.opensymphony.xwork2.ActionContext;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class WebMsg {

//	public static final String SAVED = "已保存";
//	public static final String UPDATED = "已更新";
//	public static final String SUCCESS = "成功";
//	public static final String ERROR = "失败";
//	public static final String INCORRECT_SYS="系统异常!";


	public static String getI18nMsg(String key){
		Map session= ActionContext.getContext().getSession();
		Locale locale = LanU.getLocale(session);
		ResourceBundle bundle = ResourceBundle.getBundle("web",locale);
		return bundle.getString(key);
	}

}
