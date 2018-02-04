package cn.lmz.mike.common.excel.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface IExcel{

	public static final String startLine = "startLine";
	public static final String startIndex = "startIndex";
	public static final String lineS = "lineS";
	public static final String lineE = "lineE";
	public static final String lineS1 = "lineS1";
	public static final String lineE1 = "lineE1";
	public static final String lineSn = "lineSn";
	public static final String lineEn = "lineEn";
	public static final String forS = "forS";
	public static final String forE = "forE";
	public static final String inST = "inST";
	public static final String outST = "outST";
	public static final String TITLE = "title";
	public static final String SHEET_NAME="数据导出";
	public static final String T_FORMAT="T_FORMAT";
	public static final String USE_INDEX="USE_INDEX";
	public static int LEN = 15;
	public static int MAX_LEN=40;
	public static int ADD_LEN=1;
	public static Pattern p = Pattern.compile("\"&([A-Za-z]+)\\d*&\"");
	public static final String SP="@@";
	
	
	
	public void setParams(Map params);
	public List<Object[]> read(String xls)  throws Exception;
	public List<String> readWithTemp(String xls, String tmp)  throws Exception;
	public void write(String xls, List<Object[]> tlist, List<Object[]> list)  throws Exception;
	public void writeXlsWithRs(String xls, ResultSet rs)  throws Exception;
	
	
	
}
