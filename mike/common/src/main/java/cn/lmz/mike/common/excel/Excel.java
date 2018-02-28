package cn.lmz.mike.common.excel;

import cn.lmz.mike.common.excel.impl.HSSFExcel;
import cn.lmz.mike.common.excel.impl.IExcel;
import cn.lmz.mike.common.excel.impl.XSSFExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Excel implements IExcel {

	private static final Logger log = LoggerFactory.getLogger(Excel.class);

	public static IExcel getExcel(String xls) {
		if ((xls != null) && (xls.endsWith(".xlsx"))) {
			return new XSSFExcel();
		}
		return new HSSFExcel();
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

	@Override
	public void setParams(Map params) {

	}

	public List<Object[]> read(String xls) throws Exception
	{
		return getExcel(xls).read(xls);
	}

	public List<String> readWithTemp(String xls, String tmp)
			throws Exception
	{
		return getExcel(xls).readWithTemp(xls, tmp);
	}

	public void write(String xls, List<Object[]> tlist, List<Object[]> list)
			throws Exception
	{
		getExcel(xls).write(xls, tlist, list);
	}

	public void writeXlsWithRs(String xls, ResultSet rs) throws Exception
	{
		getExcel(xls).writeXlsWithRs(xls, rs);
	}
}
