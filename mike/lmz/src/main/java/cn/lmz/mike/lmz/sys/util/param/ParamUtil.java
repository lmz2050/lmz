package cn.lmz.mike.lmz.sys.util.param;

import cn.lmz.mike.lmz.sys.exception.ErrCodeException;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.Row;

import java.util.ArrayList;
import java.util.List;

public class ParamUtil {

	
	public static Row getListRow(List<Row> list, int i){
		if(list.size()>i){
			return list.get(i);
		}else{
			return new Row();
		}
	}
	
	public static ParamBean getParamBean(Code nc,IParamLexer pl) throws Exception{
		int in_cdt = 1;
		Row r = new Row();
		nc = nc.getNext();
		while(nc!=null&&in_cdt>0){
			
			if(nc.getCh()=='('){
				in_cdt++;
			}
			if(nc.getCh()==')'){
				in_cdt--;
			}
			if(in_cdt>0){
				if(pl!=null){
					pl.lexerParam(nc,r);
				}else{
					r.add(nc);
				}
			}
			if(in_cdt>0){
			   nc = nc.getNext();
			}
		}
		
		if(nc==null&&in_cdt>0){
			throw new ErrCodeException(nc,ErrCodeException.ERR_CODE_200);
		}
		
		
		ParamBean pb = new ParamBean(r,nc);
		return pb;
		
	}
	
	public static List<Row> splitRow(Row r){
		List<Row> rlist = new ArrayList<Row>();
		if(r!=null&&r.getList().size()>0){
			Row ri = new Row();
			for(int i=0;i<r.getList().size();i++){
				Code nc = r.getList().get(i);
				if(';'==nc.getCh()){
					rlist.add(ri);
					ri = new Row();
				}else{
					ri.add(nc);
				}
			}
			rlist.add(ri);
		}
		return rlist;
		
	}
	
}
