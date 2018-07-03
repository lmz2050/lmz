package cn.lmz.mike.data.support.session.db.sql.comon;

import cn.lmz.mike.common.M;
import cn.lmz.mike.data.support.session.db.sql.ISql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public abstract class LSql extends LSqlExp implements ISql {

	private static final Logger log = LoggerFactory.getLogger(LSql.class);

	protected List<Object> params = null;

	public LSql(){
		params = new LinkedList<Object>();
	}
	public LSql(StringBuilder sb,List<Object> params){
		this.sqlsb = sb;
		this.params = params;
	}


	/*
	protected String getWhere(Map<String,Object> params){

		StringBuilder wsb = new StringBuilder();
		if(params!=null&&params.size()>0){
			wsb.append(" WHERE ");
			params.remove(LanU.LAN_C);
			int i=0;
			for(Map.Entry<String, Object> entry:params.entrySet()){
				String key = entry.getKey();
				Object val = entry.getValue();
				if(i>0){
					wsb.append(" AND ");
				}
				if(val!=null&& val instanceof IWhere){
					wsb.append(((IWhere)val).getSql(key));
				}else{
					wsb.append(key).append(" = ? ");
					this.params.add(entry.getValue());
				}
				i++;
			}
		}
		return wsb.toString();
	}
	*/

	public List<Object> getParams() {
		return params;
	}

	public String toString(){
		return "<"+this.getSql()+">["+ M.string.toString(this.getParams())+"]";
	}

}
