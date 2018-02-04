package cn.lmz.mike.data.bean;

import cn.lmz.mike.data.util.LmzU;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AParams implements Serializable,Cloneable{


	private List<Object> list = null;
	
	public AParams(){
		list = new ArrayList<Object>();
	}
	
	public AParams(Object...objs){
		list = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			for(Object o:objs){
				list.add(o);
			}
		}
	}
	public void add(Object o){
		list.add(o);
	}
	public void addAll(List lista){
		list.addAll(lista);
	}
	
	public String toString(){
		return list2String(list,",");
	}
	
	public static String list2String(List list,String sp){
		String re = "";
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object v = list.get(i);
				v = LmzU.getSqlString(v);
				if(i!=list.size()-1){	
					re+=v+sp;
				}else{
					re+=v;
				}
			}
		}
		return re;
	}	
	
}
