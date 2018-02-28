package cn.lmz.mike.data.db.sql;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.data.Data;
import cn.lmz.mike.data.util.LanU;

import java.util.Map;

public class Search extends LQuery {

	public static String MAXID = " MAX( cast(id as signed) ) ";
	public static String FUN_COUNT = " COUNT(0) ";
	
	private String s = "";
	
	public Search(Class<?> c,Map<String,Object> param) throws Exception{
		init(c,param,null,null);
	}
	public Search(Class<?> c,Map<String,Object> param,String fun) throws Exception{
		s = fun;
		init(c,param,null,null);
	}
	public Search(Class<?> c, Map<String,Object> param, Page page, String ord) throws Exception{
		init(c,param,page,ord);
	}		
	public void init(Class<?> c,Map<String,Object> param,Page page,String ord) throws Exception{
		String order = "";
		String limit = "";
		if(!MC.string.isBlank(ord)){
			order = " ORDER BY "+ord;
		}
		if(page!=null){
			if(page.getStartNum()<0){page.setStartNum(0);}
			limit = " LIMIT "+page.getStartNum()+" , "+page.getIntPageSize();
		}
		
		if(MC.string.isBlank(s)){
			s = Data.selectMap.get(c);
			if(s.contains(":lan")){
				String lann= LanU.getLan();
				if(param.containsKey(LanU.LAN_C)){
					lann=(String)param.get(LanU.LAN_C);
				}
				s = s.replaceAll(":lan","'"+lann+"'");
			}
		}
		
		this.sql="SELECT "+s+" FROM "+Data.getTab(c) +" b "+ this.getWhere(param)+ order +limit;
	}

}
