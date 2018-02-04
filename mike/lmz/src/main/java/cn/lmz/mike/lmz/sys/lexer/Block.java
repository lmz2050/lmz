package cn.lmz.mike.lmz.sys.lexer;

import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;

import java.util.ArrayList;
import java.util.List;


public class Block implements ICodeList,INode{

	private List<Row> blist = new ArrayList<Row>();
	private String codeStr;
	
	public void add(Row row){
		blist.add(row);
	}
	
	public List<Row> getList(){
		return blist;
	}	
	
	public String toString(){
		String msg="{";
		for(int i=0;i<blist.size();i++){			
			Row r = blist.get(i);
			msg+=r.toString()+ Const.LINEBR;
		}
		msg+="}";
		return msg;
	}
	public String toString(Context ctx){
		String msg="{";
		for(int i=0;i<blist.size();i++){			
			Row r = blist.get(i);
			msg+=r.toString(ctx)+Const.LINEBR;
		}
		msg+="}";
		return msg;
	}

	@Override
	public String getCodeStr() {
		if(codeStr==null||codeStr.trim().length()==0){
			codeStr="";
			for(int i=0;i<blist.size();i++){
				Row r = blist.get(i);
				codeStr+=" "+r.getCodeStr();
			}
		}
		return codeStr;	
	}
}
