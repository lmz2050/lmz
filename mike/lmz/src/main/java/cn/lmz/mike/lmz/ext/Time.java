package cn.lmz.mike.lmz.ext;

import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.db.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Time {

	private static final Logger log = LoggerFactory.getLogger(Time.class);

	public String t="300000";
	public String cn="100";
	public int c=0;
	
	public void waitSql(DB db, String sql) throws Exception{
		Object obj = db.queryObj(sql);
		int cnn = Integer.valueOf(cn);
		long tt = Long.valueOf(t);
		while(obj==null&&c<cnn){
			c++;
			log.info("wating..."+c);
			Thread.sleep(tt);
			obj = db.queryObj(sql);
		}
		c=0;
	}
	
	public void waitTime(String time) throws Exception{
		String dt= DateU.SF_yyyyMMddHHmmss.format(new Date());
		int cnn = Integer.valueOf(cn);
		long tt = Long.valueOf(t);
		while(dt.substring(8,14).compareTo(time)<0&&c<cnn){
			c++;
			log.info(dt.substring(8,14)+"wating..."+c);
			Thread.sleep(tt);
			dt=DateU.SF_yyyyMMddHHmmss.format(new Date());
		}
		c=0;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

}
