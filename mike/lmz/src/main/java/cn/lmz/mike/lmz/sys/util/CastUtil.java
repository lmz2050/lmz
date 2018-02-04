package cn.lmz.mike.lmz.sys.util;

import cn.lmz.mike.lmz.sys.exception.RunCodeException;

import java.math.BigDecimal;


public class CastUtil {

	public static Boolean getBoolean(Object v) throws RunCodeException {
		if(v!=null&&v instanceof Boolean){
			return (Boolean) v;
		}else{
			throw new RunCodeException(v+" can not cast to boolean ","");
		}
	}
	
	public static BigDecimal getBigDecimal(Object v) throws RunCodeException{
		if(v!=null&&v instanceof BigDecimal){
			return (BigDecimal) v;
		}else{
			throw new RunCodeException(v+" can not cast to BigDecimal ","");
		}
	}	
	public static Comparable getComparable(Object v) throws RunCodeException{
		if(v!=null&&v instanceof Comparable){
			return (Comparable) v;
		}else{
			throw new RunCodeException(v+" can not cast to Comparable ","");
		}
	}
	
}
