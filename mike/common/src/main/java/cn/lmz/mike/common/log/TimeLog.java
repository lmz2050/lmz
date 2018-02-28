package cn.lmz.mike.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLog {

	private static final Logger log = LoggerFactory.getLogger(TimeLog.class);

	private long starttime;
	public static final SimpleDateFormat sfT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	protected void start(String msg){
		starttime=System.currentTimeMillis();
		log.info(msg+" started "+getCurrTimeStr());
	}
	
	protected void end(String msg){
		log.info(msg+" ended "+(System.currentTimeMillis()-starttime));
	}
	
	protected String getCurrTimeStr(){
		return sfT.format(new Date());
	}
}
