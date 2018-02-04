package cn.lmz.mike.common.base;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateU extends DateUtils {

	public static final SimpleDateFormat SF_YYYY = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat SF_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat SF_yyyyMM = new SimpleDateFormat("yyyyMM");
	public static final SimpleDateFormat SF_yyyy_MM_dd = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat SF_yyyy_MM = new SimpleDateFormat("yyyy/MM");
	public static final SimpleDateFormat SF_yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	public static final SimpleDateFormat SF_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static Date getCurDate() {
		return new Date();
	}

	public static Date strToDate(String date) throws ParseException{
		if(date!=null&&date.indexOf("/")!=0){
			return SF_yyyy_MM_dd.parse(date);
		}else{
			if(date.trim().length()==8){
				return SF_yyyyMMdd.parse(date);
			}else if(date.trim().length()==12){
				return SF_yyyyMMddHHmm.parse(date);
			}else return SF_yyyyMMddHHmmss.parse(date);
		}
	}
	
	public static String getDateString(){
		return SF_yyyyMMdd.format(getCurDate());
	}
	public static String getTimeString(){
		return SF_yyyyMMddHHmmss.format(getCurDate());
	}	

	public static String getString(String fmt){
		return new SimpleDateFormat(fmt).format(getCurDate());
	}
	
	public static String dateToStr(Date date,String fmt){
		return new SimpleDateFormat(fmt).format(date);
	}	
	
	public static Date getPMonth(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}

	
}
