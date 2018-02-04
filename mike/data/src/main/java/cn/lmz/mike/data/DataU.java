package cn.lmz.mike.data;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.invoke.InvokeBean;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.util.LanU;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataU {

	public static final String LAN = "lan";
	public static final String DEF_LAN = "zh";
	
	public static void setLan(DataBean bean, String lan){
		if(bean!=null){
			bean.put(LAN, lan);
		}
	}
	
	public static void setLan(Class<?> c,Map<String,Object> params,String lan){
		if(BeanUtil.containAttr(c, LAN)){
			if(params==null){
				params = new HashMap<String,Object>();		
			}
			params.put(LAN, lan);
		}
	}
	
	
	public static String getLan(Map session){
		Locale local = null;
		if(session!=null){
			local = (Locale)session.get(LanU.LAN_SESSION_KEY);
		}
	    String localStr = LanU.LOCALE_CN;
	    if(local!=null){
	    	localStr = local.getLanguage()+"_"+local.getCountry();
	    }
	    return localStr;
	}	
	
	@SuppressWarnings("unchecked")
	public static List<Object> createObjectList(Class<?> c,List<Map> list){
       List<Object> olist = new ArrayList<Object>();
       if(list!=null&&list.size()>0){
	       for(int i=0;i<list.size();i++){
	    	   Map m = list.get(i);
	    	   Object o = BeanUtil.createBean(c,m);
	    	   olist.add(o);
	       }
       }
	   return olist;
	}
	
	@SuppressWarnings("unchecked")
	public static List createMapList(List list){
	       List olist = new ArrayList();
	       if(list!=null&&list.size()>0){
		       for(int i=0;i<list.size();i++){
		    	   InvokeBean b = (InvokeBean)list.get(i);
		    	   Map m = BeanUtil.beanToMap(b);
		    	   olist.add(m);
		       }
	       }
		   return olist;
		}	
	
	@SuppressWarnings("unchecked")
	public static PageUtil doPage(List list, Page page)throws LMZException {
		try{
			PageUtil pu = new PageUtil(null,new Page());
			if(list!=null&&list.size()>0){
				page.init(list.size(), page.getIntCurrentPage());
				int s = page.getStartNum();
				int e = page.getStartNum()+page.getIntPageSize();		
				List rlist = getSEList(list,s,e);	
				pu.setList(rlist);
				pu.setPage(page);
			}
			return pu;
		}catch(Exception e){
			throw new LMZException("DataU","doPage(List list,Page page) ",e,list,page);
		}
	}	
	@SuppressWarnings("unchecked")
	public static List getSEList(List list,int s,int e)throws LMZException{
		try{
			List rlist = new ArrayList();
			
			if(s>list.size()||s>e){return rlist;}
			int end = e>list.size()?list.size():e;
			if(s<0){s=0;}
			for(int i=s;i<end;i++){
				rlist.add(list.get(i));
			}
			return rlist;
		}catch(Exception e1){
			throw new LMZException("DataU","getSEList(List list,int s,int e)",e1,s,e);
		}
	}	
	@SuppressWarnings("unchecked")
	public List search(List list, Map likes) throws LMZException {
		try{
			if(likes==null||likes.size()==0) return list;
			List rlist = new ArrayList();
			if(list!=null&&list.size()>0){
				Map<String,PropertyDescriptor> map = Data.getBeanInfo(list.get(0).getClass());
				for(int i=0;i<list.size();i++){
					Object oo = list.get(i);
					if(BeanUtil.checkBean(oo, likes,map,true)){
    					rlist.add(oo);
    				}
				}
			}
			return rlist;
		}catch(Exception e){
			throw new LMZException("DataU"," search(List list Map likes)",e,list,likes);
		}
	}

	@SuppressWarnings("unchecked")
	public static List doOrd(List rlist, String ord) {
		return rlist;
	}	
	
	
}
