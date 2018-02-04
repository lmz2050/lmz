package cn.lmz.mike.data.util;

public class LanU {

	public static final String LAN_SESSION_KEY = "WW_TRANS_I18N_LOCALE";
	public static final String LOCALE_EN = "en_US";
	public static final String LOCALE_CN = "zh_CN";
	public static final String LOCALE_EN_NAME = "English";
	public static final String LOCALE_CN_NAME = "简体中文";
	public static final String LAN = "lan";
	public static final String LAN_C = "lan_c";
	
	private static final ThreadLocal<String> lanValue = new ThreadLocal<String>();
	
	public static String getLan(){
		return lanValue.get();
	}
	
	public static void setLan(String lan){
		lanValue.set(lan);
	}
	
	
}
