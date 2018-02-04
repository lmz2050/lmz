package cn.lmz.mike.data.db.sql;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.data.Data;
import cn.lmz.mike.data.util.LanU;

import java.util.Map;


public class Update extends LQuery {
	
	public Update(Class<?> c,Map<String,Object> sets,Map<String,Object> param) throws Exception{
		init(c,sets,param);
	}
	
	public String getSet(Map<String,Object> sets){
		String setstr = "";
		if(sets!=null&&sets.size()>0){
			for(Map.Entry<String, Object> entry:sets.entrySet()){
				String key = entry.getKey();
				if(!LanU.LAN.equals(key)){
					setstr+=(setstr.length()==0?" SET ":" , ")+key +" = ? ";
					this.vals.add(entry.getValue());
				}
			}
		}
		return setstr;
	}
	
	public void init(Class<?> c,Map<String,Object> sets,Map<String,Object> param) throws Exception{
		if(sets==null||sets.size()==0){
			throw new LMZException(" update - set value size = 0");
		}
		this.sql = "UPDATE "+ Data.getTab(c)+ this.getSet(sets) + this.getWhere(param);
	}
}
