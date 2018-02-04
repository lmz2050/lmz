package cn.lmz.mike.common.excel.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import cn.lmz.mike.common.log.O;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import cn.lmz.mike.common.V;
import cn.lmz.mike.common.excel.Excel;
import cn.lmz.mike.common.log.TimeLog;

public class SXExcel extends TimeLog implements IExcel{

	private Map params = new HashMap();
	private int stindex=0;
	private int rindex=0;
	private int count=0;
	private Object[] title=null;
	private Object[] ttype=null;
	private CellStyle titleStyle=null;
	public static final int lineC=1000000;
    
	@Override
	public List<Object[]> read(String xls) throws Exception{
		File xlsF = new File(xls);
		if(!xlsF.exists()){
			throw new Exception("文件不存在："+xls);
		}
		
		List<Object[]> list = new ArrayList<Object[]>();

		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(xlsF));
		try {
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			for(int j=0;j<sheet.getLastRowNum()+1;j++) {
				//创建一个行对象
				XSSFRow row = sheet.getRow(j);
				//把一行里的每一个字段遍历出来
				String[] rr = new String[row.getLastCellNum()];
				for(int i=0;i<row.getLastCellNum();i++) {
				//创建一个行里的一个字段的对象，也就是获取到的一个单元格中的值
					XSSFCell cell = row.getCell(i);
					//在这里我们就可以做很多自己想做的操作了，比如往数据库中添加数据等
					//System.out.println(cell.getRichStringCellValue());
					rr[i]=cell.getRichStringCellValue().getString();
				}
				list.add(rr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			workbook.close();
		}
		return list;
	}
	@Override
	public List<String> readWithTemp(String xls, String tmp)  throws Exception{

		List<String> list = new LinkedList<String>();
		String re="";
		BigDecimal idxS1 = BigDecimal.ZERO;
		if(getCfgVal(startIndex).trim().length()!=0){
			idxS1 = new BigDecimal(getCfgVal(startIndex));
		}
		int sIndex = 0;
		if(getCfgVal(startLine).trim().length()!=0){
			sIndex=new Integer(getCfgVal(startLine));
		}
		
		List<Object[]> olist = read(xls);
		for(int i=sIndex;i<olist.size();i++){
			Object[] oo = olist.get(i);
			
	    	StringBuffer sb = new StringBuffer();
			Matcher m = p.matcher(tmp);
			while(m.find()){
				int col = getCode(m.group(1));
				String strv = new String(oo[col]+"").trim();
				m.appendReplacement(sb, strv);
			}
			m.appendTail(sb);

			re =sb.toString().replaceAll("%INDEX%", idxS1.toPlainString());
			idxS1 = idxS1.add(BigDecimal.ONE);
			String lineSStr=getCfgVal(lineS);
			String lineEStr="";
			if(params.get(lineE)!=null){
				lineEStr = getCfgVal(lineE);
			}
			if(i==sIndex){
				if(params.get(lineS1)!=null){
					lineSStr=getCfgVal(lineS1);
				}
				if(params.get(lineE1)!=null){
					lineEStr=getCfgVal(lineE1);
				}
			}
			list.add(lineSStr+re+lineEStr);
			
		}
		

		if(list.size()>0){
			if(params.get(forS)!=null){
				list.add(0, getCfgVal(forS));
			}
			
			String lineSStr=getCfgVal(lineS);
			String lineEStr=V.LINEBR;
			if(params.get(lineE)!=null){
				lineEStr = getCfgVal(lineE);
			}
			if(params.get(lineSn)!=null){
				lineSStr=getCfgVal(lineSn);
			}
			if(params.get(lineEn)!=null){
				lineEStr=getCfgVal(lineEn);
			}
			list.remove(list.size()-1);
			list.add(lineSStr+re+lineEStr);
			
			if(params.get(forE)!=null){
				list.add(getCfgVal(forE));
			}else{
				list.add(V.LINEBR);
			}
		}

		return list;
	}
	
	private SXSSFSheet createSh(SXSSFWorkbook wb,String name,List<Object[]> tlist) throws Exception{
		if (tlist == null || tlist.size() == 0) {
			throw new Exception("读取表头失败！");
		}
		if(title==null){
			title = tlist.get(0);
		}
		if(ttype==null){
			if(tlist.size()>1){
				ttype = tlist.get(1);
			}else{
				ttype = new Object[title.length];
			}
		}
		
		if(titleStyle==null){
			titleStyle = getTitleStyle(wb);
		}
		
		SXSSFSheet sh = wb.createSheet(name); 
		return sh;
	}
	
	private Sheet writeTitle(SXSSFWorkbook wb,String name,List<Object[]> tlist) throws Exception{

		SXSSFSheet sh = createSh(wb,name,tlist);
		sh.trackAllColumnsForAutoSizing();
		Row row = sh.createRow(0);
		row.setHeight((short) 450);
		
		for(int i=0;i<title.length;i++ ){
			String titleContent = String.valueOf(title[i]);
			Cell cell = row.createCell(i);
			cell.setCellValue(titleContent);
			cell.setCellStyle(titleStyle);
			sh.autoSizeColumn(i,true);
		}
		return sh;
	}
	
	@Override
	public void write(String xls, List<Object[]> tlist, List<Object[]> list)  throws Exception{
		// 声明一个工作薄
		start(" write:"+xls+","+list.size());
		SXSSFWorkbook wb = new SXSSFWorkbook(5000);
		try {	
			Sheet sh = writeTitle(wb,SHEET_NAME,tlist);
			
			start(" write xlsx : "+xls);
			for(int i=0;i<list.size();i++){
				rindex++;
				count++;
				Row row = sh.createRow(rindex);
				Object[] rr = list.get(i);
				
				for(int j=0;j<rr.length;j++){
					String content = (String)rr[j];
					if(content==null){
						content="";
					}
					String typeA = (String)ttype[j];
					Cell cell = row.createCell(j);
					
					if("Number".equalsIgnoreCase(typeA)){
						if("".equals(content)){
							content="0";
						}
						Double val = new Double(content+"");
						cell.setCellValue(val);
					}else{
						cell.setCellValue(content);
					}
					
					//sheet.autoSizeColumn(j,true);
				}
				
				if(count!=0&&count%lineC==0){
					end(" write line : "+count);
					stindex++;
					rindex=0;
					start(" write sheet:"+SHEET_NAME+stindex);
					sh = createSh(wb,SHEET_NAME+stindex,tlist);  
				}
				
				if(count%5000==1){
					end(" write line :"+count);
				}
						
			}

			FileOutputStream os = new FileOutputStream(xls);
			wb.write(os);
			os.close();
			wb.close();
			end("write end:"+xls+",count:"+count);
			
		} catch (Exception e) {
			O.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public void writeXlsWithRs(String xls,ResultSet rs)  throws Exception{
		start(" writeXlsWithRs,"+xls);
		SXSSFWorkbook wb = new SXSSFWorkbook(5000);
		try {
			List<Object[]> tlist = Excel.getTListWithRs(rs);
			Sheet sh = writeTitle(wb,SHEET_NAME,tlist);
			
			start(" write xlsx : "+xls);
			while(rs.next()){

				rindex++;
				count++;
				
				Row row = sh.createRow(rindex);
				for(int col=0;col<title.length;col++){
					String content = rs.getString(col+1);
					if(content==null){content="";}
					String typeA = (String)ttype[col];
					Cell cell = row.createCell(col);
					
					if("Number".equalsIgnoreCase(typeA)){
						if("".equals(content)){
							content="0";
						}
						Double val = new Double(content+"");
						cell.setCellValue(val);
					}else{
						cell.setCellValue(content);
					}
					
				}
				
				if(count!=0&&count%lineC==0){
					end(" write line : "+count);
					stindex++;
					rindex=0;
					start(" write sheet:"+SHEET_NAME+stindex);
					sh = writeTitle(wb,SHEET_NAME+stindex,tlist);  
					
				}
				
				if(count%5000==1){
					end("write line :"+count);
				}
						
			}

			FileOutputStream os = new FileOutputStream(xls);
			wb.write(os);
			os.close();
			wb.close();
			end("write end:"+xls+",count:"+count);
			
		} catch (Exception e) {
			O.error(e.getMessage(),e);
			throw e;
		}			
	}

	public static CellStyle getTitleStyle(SXSSFWorkbook workbook) {
		// 产生Excel表头
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillBackgroundColor(HSSFColor.GREY_80_PERCENT.index);
		return titleStyle;
	}	
	
	public int getCode(String c){
		int re=0;
		for(int i=0;i<c.length();i++){
			re+=(c.length()-i-1)*26+(int)c.charAt(i)-65;
		}
		return re;
	}	
	public String getCfgVal(String key){	
		String val=(String)params.get(key);
		return val;
	}

	@Override
	public void setParams(Map params) {
		this.params = params;
	}
}
