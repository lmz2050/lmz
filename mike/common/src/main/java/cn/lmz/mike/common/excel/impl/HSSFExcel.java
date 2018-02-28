package cn.lmz.mike.common.excel.impl;

import cn.lmz.mike.common.V;
import cn.lmz.mike.common.log.TimeLog;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.*;
import java.util.regex.Matcher;

public class HSSFExcel extends TimeLog implements IExcel{

	private static final Logger log = LoggerFactory.getLogger(HSSFExcel.class);

	private Map params = new HashMap();
	private int stindex=0;
	private int rindex=0;
	private int count=0;
	private Object[] title=null;
	private Object[] ttype=null;
	private CellStyle titleStyle=null;
	public static final int lineC=1000000;
	
	@Override
	public void setParams(Map params) {
		this.params = params;
	}
	@Override
	public List<Object[]> read(String xls) throws Exception {
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(xls));
        HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        List<Object[]> list = new ArrayList<Object[]>();
        
        for (int k = 0; k < sheet.getLastRowNum()+1; k++) {
            HSSFRow row = sheet.getRow(k);
            String[] rr = new String[row.getLastCellNum()];
			for(int i=0;i<row.getLastCellNum();i++) {
			//创建一个行里的一个字段的对象，也就是获取到的一个单元格中的值
				HSSFCell cell = row.getCell(i);
				//在这里我们就可以做很多自己想做的操作了，比如往数据库中添加数据等
				//System.out.println(cell.getRichStringCellValue());
				rr[i]=cell.getRichStringCellValue().getString();
			}
			list.add(rr);
        }
        return list;
	}
	@Override
	public List<String> readWithTemp(String xls, String tmp) throws Exception {

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
			String lineEStr= V.LINEBR;
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

	private HSSFSheet createSh(HSSFWorkbook wb,String name,List<Object[]> tlist) throws Exception{
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
		
		HSSFSheet sh = wb.createSheet(name); 
		return sh;
	}
	
	private Sheet writeTitle(HSSFWorkbook wb,String name,List<Object[]> tlist) throws Exception{

		HSSFSheet sh = createSh(wb,name,tlist);
		sh.autoSizeColumn(1, true);
		Row row = sh.createRow(0);
		//row.setHeight((short) 450);
		
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
	public void write(String xls, List<Object[]> tlist, List<Object[]> list)
			throws Exception {
		// 声明一个工作薄
				log.info(" write:"+xls+","+list.size());
				HSSFWorkbook wb = new HSSFWorkbook();
				try {	
					Sheet sh = writeTitle(wb,SHEET_NAME,tlist);

					log.info(" write xlsx : "+xls);
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
							log.info(" write line : "+count);
							stindex++;
							rindex=0;
							log.info(" write sheet:"+SHEET_NAME+stindex);
							sh = createSh(wb,SHEET_NAME+stindex,tlist);  
						}
						
						if(count%5000==1){
							log.info(" write line :"+count);
						}
								
					}

					FileOutputStream os = new FileOutputStream(xls);
					wb.write(os);
					os.close();
					wb.close();
					log.info("write end:"+xls+",count:"+count);
					
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					throw e;
				}
	}
	@Override
	public void writeXlsWithRs(String xls, ResultSet rs) throws Exception {
		log.info(" writeXlsWithRs,"+xls);
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			List<Object[]> tlist = getTListWithRs(rs);
			Sheet sh = writeTitle(wb,SHEET_NAME,tlist);

			log.info(" write xlsx : "+xls);
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
					log.info(" write line : "+count);
					stindex++;
					rindex=0;
					log.info(" write sheet:"+SHEET_NAME+stindex);
					sh = writeTitle(wb,SHEET_NAME+stindex,tlist);  
					
				}
				
				if(count%5000==1){
					log.info("write line :"+count);
				}
						
			}

			FileOutputStream os = new FileOutputStream(xls);
			wb.write(os);
			os.close();
			wb.close();
			log.info("write end:"+xls+",count:"+count);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw e;
		}	
	}
	
	public static List<Object[]> getTListWithRs(ResultSet rs) throws Exception{
		List<Object[]> tlist = new ArrayList<Object[]>();

		if(rs!=null){
			try{
			ResultSetMetaData rsmt = rs.getMetaData();
				Object[] tt = new Object[rsmt.getColumnCount()];
				Object[] types = new Object[rsmt.getColumnCount()];
				for(int i=0;i<rsmt.getColumnCount();i++){
					String name = rsmt.getColumnName(i+1);
					int type = rsmt.getColumnType(i+1);
					log.info(name+":"+type);
					if(Types.INTEGER==type||Types.DECIMAL==type||Types.DOUBLE==type||Types.NUMERIC==type||Types.FLOAT==type||Types.REAL==type){
						types[i]="Number";
					}else{
						types[i]="String";
					}
					tt[i]=name;
				}
				tlist.add(tt);
				tlist.add(types);

			}catch(Exception e){
				log.error(e.getMessage(),e);
				throw e;
			}
		}
		return tlist;
	}
	
	public static CellStyle getTitleStyle(HSSFWorkbook workbook) {
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

}
