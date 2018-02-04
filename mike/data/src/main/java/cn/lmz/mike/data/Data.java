package cn.lmz.mike.data;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.annotation.LField;
import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.data.util.CUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

	private static final Map<Class<?>,Map<String,PropertyDescriptor>> map = new HashMap<Class<?>,Map<String,PropertyDescriptor>>();	
	private static final Map<Class<?>,Map<String,PropertyDescriptor>> mapAll = new HashMap<Class<?>,Map<String,PropertyDescriptor>>();
	public static final Map<Class<?>,String> selectMap = new HashMap<Class<?>,String>();	
	private static final Map<String,Class<?>> namemap = new HashMap<String,Class<?>>();	
	private static final Map<String,Object> regmap = new HashMap<String,Object>();
	
	static{
		reg("org.lmz");	
	}
	
	public static String getLName(Class<?> c){
		String name = c.getSimpleName();
		Lbean lb = c.getAnnotation(Lbean.class);
		if(lb!=null&&!StrU.isBlank(lb.sname())){
			name=lb.sname();
		}
		return name;
	}
	
	public static void reg(String pakg){
		if(!regmap.containsKey(pakg)){
			List<Class<?>> clist = CUtil.getClassesByAnnotation(pakg, Lbean.class);
			O.pn("init class:");
			if(clist!=null&&clist.size()>0){
				for(int i=0;i<clist.size();i++){
					Class<?> c = clist.get(i);
					String name = c.getSimpleName();
					Lbean lb = c.getAnnotation(Lbean.class);
					if(lb!=null&&!StrU.isBlank(lb.sname())){
						name=lb.sname();
					}
					O.pn(name+":"+c.getName());
					namemap.put(name, c);
					initC(c);
				}
			}
			regmap.put(pakg, "");
			
			List<Class<?>> ilist = CUtil.getClassesByInterface(pakg, IData.class);
			if(ilist!=null&&ilist.size()>0){
				for(int i=0;i<ilist.size();i++){
					Class<?> c = ilist.get(i);
					
					if(!c.isInterface()){
						try{
							IData d = (IData) c.newInstance();
							d.reg();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
	
	public static Class<?> getClass(String name){
//		System.out.println(namemap.size());
//		for(Map.Entry<String, Class<?>> entry:namemap.entrySet()){
//			System.out.println(entry.getKey()+":"+entry.getValue().getName());
//		}
		return namemap.get(name);
	}
	
	public static Map<String,PropertyDescriptor> getBeanInfo(Class<?> c){
		return getBeanInfo(c,false);
	}
	public static Map<String,PropertyDescriptor> getBeanInfo(Class<?> c,boolean all){
		Map<Class<?>,Map<String,PropertyDescriptor>> mm;
		if(all){
			mm=mapAll;
		}else{
			mm=map;
		}
		if(mm.get(c)==null){
			initC(c);
			return mm.get(c);
		}
		return mm.get(c);
	}	
	
	public static void init(String c){
		String [] ss = c.split(",");
		for(int i=0;i<ss.length;i++){
			initC(ss[i]);
		}
	}
	
	public static void initC(String name){
		try {
			Class<?> c = Class.forName(name);
			initC(c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public static void initC(Class<?> c){
		try {
			O.pn("initC "+c.getSimpleName()+ " start ");
			java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(c);
			Field[] fs = c.getDeclaredFields();
			Map<String,PropertyDescriptor> m = new HashMap<String,PropertyDescriptor>();
			String select="";
			Map<String,PropertyDescriptor> mall = new HashMap<String,PropertyDescriptor>();
			if(info!=null){
				PropertyDescriptor pd[] = info.getPropertyDescriptors();
				for(int i=0;i<pd.length;i++){
					boolean addselect = false;
					String fieldName = pd[i].getName();
				    if (fieldName != null && !fieldName.equals("class")) {
				    	Field f = null;
				    	for(int k=0;k<fs.length;k++){
				    		if(fieldName.equals(fs[k].getName())){
				    			f = fs[k];
				    			break;
				    		}
				    	}
				    	if(f!=null){
							LField lf = f.getAnnotation(LField.class);
							if(lf!=null){
								if(!StrU.isBlank(lf.name())){
									fieldName=lf.name();
								}
								if(!StrU.isBlank(lf.sql())){
									String sql=" ( "+lf.sql()+" ) "+fieldName;
									select+="".equals(select)? sql:","+sql;
									addselect=true;
								}
								if(!lf.useForDb()){
									mall.put(fieldName, pd[i]);
									continue;
								}
							}
				    	}
						 //O.pn("name = "+fieldName);
						if(pd[i].getReadMethod()!=null){
							 //O.pn(" rm = "+pd[i].getReadMethod().getName());
							 pd[i].getReadMethod().setAccessible(true);
						}
						if(pd[i].getWriteMethod()!=null){
							 //O.pn(" wm = "+pd[i].getWriteMethod().getName());
							 pd[i].getWriteMethod().setAccessible(true);
						}
						if(!addselect){
							select+="".equals(select)? fieldName:(","+fieldName);
						}
						m.put(fieldName, pd[i]);
				     }
				}
				mall.putAll(m);
			}
			map.put(c, m);
			mapAll.put(c, mall);
			O.pn(select);
			selectMap.put(c, select);
			O.pb("end p size =  "+m.size());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
	public static String getTab(Class<?> c){
		String tab=c.getSimpleName();
		Lbean b = c.getAnnotation(Lbean.class);
		if(b!=null&&!StrU.isBlank(b.name())){
			tab = b.name();
		}
		return tab;
	}
	public static String getOthField(Class<?> c){
		
		
		String tab=c.getSimpleName();
		Lbean b = c.getAnnotation(Lbean.class);
		if(b!=null&&!StrU.isBlank(b.name())){
			tab = b.name();
		}
		return tab;
	}
}
