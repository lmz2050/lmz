package cn.lmz.mike.common.excel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lmz.mike.common.excel.impl.IExcel;
import cn.lmz.mike.common.excel.impl.SXExcel;
import cn.lmz.mike.common.log.O;


public class Excel implements IExcel {

	private IExcel exe = null;

	public Excel(){
		init("x");
	}
	public Excel(String fix){
		init(fix);
	}
	public void init(String fix){
		if(fix.endsWith("x")){
			exe = new SXExcel();
		}else{
			exe = new SXExcel();
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
					O.info(name+":"+type);
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
				O.error(e.getMessage(),e);
				throw e;
			}
		}
		return tlist;
	}
	
	@Override
	public void setParams(Map params) {
		exe.setParams(params);
	}
	@Override
	public List<Object[]> read(String xls) throws Exception {
		return exe.read(xls);
	}
	@Override
	public List<String> readWithTemp(String xls, String tmp) throws Exception {
		return exe.readWithTemp(xls, tmp);
	}
	@Override
	public void write(String xls, List<Object[]> tlist, List<Object[]> list) throws Exception {
		exe.write(xls, tlist, list);
	}
	@Override
	public void writeXlsWithRs(String xls, ResultSet rs) throws Exception {
		exe.writeXlsWithRs(xls, rs);
	}
}
