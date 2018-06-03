package cn.lmz.mike.admin.business.service.impl;

import cn.lmz.mike.admin.business.bean.Adm_drvices;
import cn.lmz.mike.admin.business.service.IdrvicesService;
import cn.lmz.mike.admin.business.util.AdmMsg;
import cn.lmz.mike.common.V;
import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.bean.OrParams;
import cn.lmz.mike.web.base.bean.Return;
import cn.lmz.mike.web.base.service.impl.WService;
import cn.lmz.mike.web.base.util.WebMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrvicesServiceImpl extends WService implements IdrvicesService {

	private static final Logger log = LoggerFactory.getLogger(DrvicesServiceImpl.class);

	@Override
	public Return update(Adm_drvices info, String uname) throws Exception{
		Return r = new Return();
		String msg="";
		if(!"admin".equalsIgnoreCase(uname)&&!uname.equalsIgnoreCase(info.getCus_name())){
			msg =  WebMsg.getI18nMsg("admin.msg.DIF_CUS_NAME")+"{"+uname+"}"+"{"+info.getCus_name()+"}";
			r.setSuccess(false);
			r.setMsg(msg);
			return r;
		}
		try{
			if(info!=null){
				DataBean db = new DataBean(Adm_drvices.class);
				
				OrParams op = new OrParams().set("mac1",info.getMac1()).set("mac2",info.getMac2());

				db.put("mac1_or_mac2_exist", op);
	
				//检查mac1 mac2是否存在
				List dlist = searchObj(db, null, null).getList();
	
				info.setUby(uname);
				info.setUtm(DateU.getTimeString());
				if(V.Y.equalsIgnoreCase(info.getActive())&& StrU.isBlank(info.getActive_time())){
					info.setActive_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn1())&&StrU.isBlank(info.getGn1_time())){
					info.setGn1_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn2())&&StrU.isBlank(info.getGn2_time())){
					info.setGn2_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn3())&&StrU.isBlank(info.getGn3_time())){
					info.setGn3_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn4())&&StrU.isBlank(info.getGn4_time())){
					info.setGn4_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn5())&&StrU.isBlank(info.getGn5_time())){
					info.setGn5_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn6())&&StrU.isBlank(info.getGn6_time())){
					info.setGn6_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn7())&&StrU.isBlank(info.getGn7_time())){
					info.setGn7_time(DateU.getTimeString());
				}
				if(V.Y.equalsIgnoreCase(info.getGn8())&&StrU.isBlank(info.getGn8_time())){
					info.setGn8_time(DateU.getTimeString());
				}
				
				if(!StrU.isBlank(info.getId())){
					if(dlist!=null&&dlist.size()>0){
						//更新时候只能查询到>1个说明重复
						if(dlist.size()>1){
							msg = WebMsg.getI18nMsg("admin.msg.DEV_MAC_EXISTS")+":"+info.getMac1()+","+info.getMac2()+",len:"+dlist.size();
							r.setSuccess(false);
							r.setMsg(msg);
						}else{
							Adm_drvices dev = (Adm_drvices)dlist.get(0);
							//查询到1个，且id不同，说明重复
							if(!info.getId().equalsIgnoreCase(dev.getId())){
								msg = WebMsg.getI18nMsg("admin.msg.DEV_MAC_EXISTS")+":"+info.getMac1()+","+info.getMac2()+",id:"+dev.getId();
								r.setSuccess(false);
								r.setMsg(msg);
							}
						}
					}
					
					if(StrU.isBlank(r.getMsg())){
						update(info);
						r.setSuccess(true);
					}
	
				}else{
					//创建时只要存在说明重复
					if(dlist!=null&&dlist.size()>0){
						msg = WebMsg.getI18nMsg("admin.msg.DEV_MAC_EXISTS")+":"+info.getMac1()+","+info.getMac2()+",when add:"+dlist.size();
						r.setSuccess(false);
						r.setMsg(msg);
					}else{					
						info= create(info);
						r.setSuccess(true);
					}
				}
			}
		}catch(Exception e){
			r.setMsg(e.getMessage());
			r.setSuccess(false);
			log.error(e.getMessage(),e);
			throw e;
		}
		return r;
	}


	private String toStringVal(Object[] strs,int i){
		Object v="";
		if(i<strs.length){
			v = strs[i];
		}
		if(v==null) return "";
		return v.toString();
	}
	
	@Override
	public void createList(List<Object[]> list,String uname) throws Exception {
		String msg="";
		if(list!=null&&list.size()>1){
			List<Adm_drvices> adlist = new ArrayList<Adm_drvices>();
			Map<String,Integer> macMap = new HashMap<String,Integer>();
			for(int i=1;i<list.size();i++){
				
				Object[] r = list.get(i);

				Adm_drvices ad = new Adm_drvices();
				ad.setCus_name(toStringVal(r,0));
				ad.setDev_name(toStringVal(r,1));
				ad.setMac1(toStringVal(r,2));
				ad.setMac2(toStringVal(r,3));
				ad.setActive(toStringVal(r,4));
				ad.setGn1(toStringVal(r,5));
				ad.setGn2(toStringVal(r,6));
				ad.setGn3(toStringVal(r,7));
				ad.setGn4(toStringVal(r,8));
				ad.setGn5(toStringVal(r,9));
				ad.setGn6(toStringVal(r,10));
				ad.setGn7(toStringVal(r,11));
				ad.setGn8(toStringVal(r,12));

				
				if(StrU.isBlank(ad.getCus_name())){
					msg+= WebMsg.getI18nMsg("admin.msg.EMPTY_CUS_NAME")+";";
				}
				if(StrU.isBlank(ad.getDev_name())){
					msg+=WebMsg.getI18nMsg("admin.msg.EMPTY_DEV_NAME")+";";
				}
				if(StrU.isBlank(ad.getMac1())){
					msg+=WebMsg.getI18nMsg("admin.msg.EMPTY_MAC1")+";";
				}
				if(StrU.isBlank(ad.getMac2())){
					msg+=WebMsg.getI18nMsg("admin.msg.EMPTY_MAC2")+";";
				}
				if(macMap.containsKey(ad.getMac1())){
					msg+=WebMsg.getI18nMsg("admin.msg.DEV_MAC_EXISTS_MAC1")+" line["+macMap.get(ad.getMac1())+"]"+";";
				}else{
					macMap.put(ad.getMac1(), i);
				}
				if(macMap.containsKey(ad.getMac2())){
					msg+=WebMsg.getI18nMsg("admin.msg.DEV_MAC_EXISTS_MAC2")+" line["+macMap.get(ad.getMac2())+"]"+";";
				}else{
					macMap.put(ad.getMac2(), i);
				}

				
				if(StrU.isBlank(msg)){
					adlist.add(ad);
				}else{
					break;
				}

			}
			
			if(StrU.isBlank(msg)){
				
				for(int i=0;i<adlist.size();i++){
					
					Return ri = update(adlist.get(i),uname);
					if(!ri.isSuccess()){
						msg = ri.getMsg();
						break;
					}
					
				}
			}
			
			
			if(!StrU.isBlank(msg)){
				throw new Exception(msg);
			}
		}
	}


	@Override
	public Return upActivate(String mac1,String mac2, String uname, String function_type) throws Exception {
		// TODO Auto-generated method stub
		Return re = new Return();
		DataBean db = new DataBean(Adm_drvices.class);
		OrParams op = new OrParams().set("mac1",mac1).set("mac2",mac2);

		db.put("search", op);
		
		List dlist = searchObj(db, null, null).getList();
		StringBuilder sb= new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><functions>");

		if(dlist!=null&&dlist.size()>0){
			re.setSuccess(true);
			Adm_drvices ad = (Adm_drvices)dlist.get(0);

			if(StrU.isBlank(function_type)){
				ad.setActive("Y");
			}else{
				if("001".equalsIgnoreCase(function_type)){
					ad.setGn1("Y");
				}else if("002".equalsIgnoreCase(function_type)){
					ad.setGn2("Y");
				}else if("003".equalsIgnoreCase(function_type)){
					ad.setGn3("Y");
				}else if("004".equalsIgnoreCase(function_type)){
					ad.setGn4("Y");
				}else if("005".equalsIgnoreCase(function_type)){
					ad.setGn5("Y");
				}else if("006".equalsIgnoreCase(function_type)){
					ad.setGn6("Y");
				}else if("007".equalsIgnoreCase(function_type)){
					ad.setGn7("Y");
				}else if("008".equalsIgnoreCase(function_type)){
					ad.setGn8("Y");
				}else {
					re.setSuccess(false);
				}
			}
			if(re.isSuccess()) {
				re = update(ad, uname);
			}

			getFunction(sb,"JBGN",ad.getActive(),ad.getActive_time());
			getFunction(sb,"GN1",ad.getGn1(),ad.getGn1_time());
			getFunction(sb,"GN2",ad.getGn2(),ad.getGn2_time());
			getFunction(sb,"GN3",ad.getGn3(),ad.getGn3_time());
			getFunction(sb,"GN4",ad.getGn4(),ad.getGn4_time());
			getFunction(sb,"GN5",ad.getGn5(),ad.getGn5_time());
			getFunction(sb,"GN6",ad.getGn6(),ad.getGn6_time());
			getFunction(sb,"GN7",ad.getGn7(),ad.getGn7_time());
			getFunction(sb,"GN8",ad.getGn8(),ad.getGn8_time());

		}
		sb.append("</functions>");
		re.setObj(sb.toString());
		return re;
	}

	private void getFunction(StringBuilder sb,String name,String actived,String time){
		sb.append("<function><name>");
		sb.append(name);
		sb.append("</name><authorized>");
		if("Y".equalsIgnoreCase(actived)){
			sb.append(true);
		}else{
			sb.append(false);
		}
		sb.append("</authorized><authorizedtime>");
		sb.append(time==null?"":time);
		sb.append("</authorizedtime></function>");
	}

}
