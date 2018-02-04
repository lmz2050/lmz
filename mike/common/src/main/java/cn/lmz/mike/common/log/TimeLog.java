package cn.lmz.mike.common.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLog {
	private long starttime;
	public static final SimpleDateFormat sfT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	protected void start(String msg){
		starttime=System.currentTimeMillis();
		O.info(msg+" started "+getCurrTimeStr());
	}
	
	protected void end(String msg){
		O.info(msg+" ended "+(System.currentTimeMillis()-starttime));
	}
	
	protected String getCurrTimeStr(){
		return sfT.format(new Date());
	}
}
