package cn.lmz.mike.lmz.sys.lexer;

import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Row implements ICodeList,INode{

	private List<Code> rlist = new ArrayList<Code>();
	private String codeStr;
	
	public void reverse(){
		Collections.reverse(rlist);
	}

	public void add(Code code){
		if(code!=null){
			Code c = code.clone();
			/*
			Code prevCode = null;
			if(rlist.size()>0){
				prevCode = rlist.get(rlist.size()-1);
				prevCode.setNextCode(c);
			}
			c.setPrevCode(prevCode);
			*/
			
			rlist.add(c);
			c.setR(this);
		}
	}
	public void addAll(Row r){
		if(r!=null){
			for(int i=0;i<r.getList().size();i++){
				add(r.getList().get(i));
			}
		}
	}
	
	public void remove(Code code){
		for(int i=0;i<rlist.size();i++){
			Code ci = rlist.get(i);
			if(ci.getVal().equalsIgnoreCase(code.getVal())&&ci.getType().equalsIgnoreCase(code.getType())&&ci.getCh()==code.getCh()){
				rlist.remove(ci);
				break;
			}
		}
	}
	public List<Code> getList(){
		return rlist;
	}
	
	public String toString(){
		String msg="[";
		for(int i=0;i<rlist.size();i++){
			Code c = rlist.get(i);
			msg+=c.toString()+ Const.LINEBR;
		}
		msg+="]";
		return msg;
	}	
	public String toString(Context ctx){
		String msg="[";
		for(int i=0;i<rlist.size();i++){
			Code c = rlist.get(i);
			msg+=c.toString(ctx)+Const.LINEBR;
		}
		msg+="]";
		return msg;
	}
	
	public String getCodeStr() {
		if(codeStr==null||codeStr.trim().length()==0){
			codeStr="";
			for(int i=0;i<rlist.size();i++){
				Code c = rlist.get(i);
				codeStr+=" "+c.getCodeStr();
			}
		}
		return codeStr;	
	}

}
