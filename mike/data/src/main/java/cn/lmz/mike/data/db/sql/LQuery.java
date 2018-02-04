package cn.lmz.mike.data.db.sql;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.bean.InParams;
import cn.lmz.mike.data.bean.NotParams;
import cn.lmz.mike.data.bean.OrParams;
import cn.lmz.mike.data.util.LanU;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public abstract class LQuery {
	
	protected String sql;
	protected List<Object> vals = new LinkedList<Object>();

	protected String getWhere(Map<String,Object> params){
		String keys="";
		if(params!=null&&params.size()>0){
			params.remove(LanU.LAN_C);
			for(Map.Entry<String, Object> entry:params.entrySet()){
				String key = entry.getKey();
				Object val = entry.getValue();
				if(val!=null&& val instanceof InParams){
					keys+=(keys.length()==0?" WHERE ":" AND ")+key +" in ("+val.toString()+") ";
				}else if(val!=null&&val instanceof NotParams){
					keys+=(keys.length()==0?" WHERE ":" AND ")+key +" not in ("+val.toString()+") ";
				}else if(val!=null&&val instanceof OrParams){
					keys+=(keys.length()==0?" WHERE ":" AND ")+" ("+val.toString()+") ";
				}else{
					keys+=(keys.length()==0?" WHERE ":" AND ")+key +" = ? ";
					this.vals.add(entry.getValue());
				}
			}
		}
		return keys;
	}	
	
	public Object[] getParam() {
		O.info(StrU.getString(vals.toArray()));
		return vals.toArray();
	}

	public String getSql() {
		O.info(sql);
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getVals() {
		return vals;
	}

	public void setVals(List<Object> vals) {
		this.vals = vals;
	}
	
	public String toString(){
		return "<"+this.getSql()+"["+this.getParam()+"]>";
	}
}
