package cn.lmz.mike.data.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.common.str.StrU;
import cn.lmz.mike.data.DataU;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.bean.IdBean;
import cn.lmz.mike.data.support.IEntity;
import cn.lmz.mike.data.support.IService;
import org.springframework.stereotype.Service;

@Service("lService")
public class LService implements IService {
	@Resource
	protected IEntity en;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public String getID(String tab) throws LMZException {
		return getID(tab,"Y",15);
	}
	public String getID(String tab,String usedate,int len) throws LMZException{
		DataBean bb = new DataBean(IdBean.class,"tab",tab);
		List list = searchMap(bb,null,null).getList();
		String id="";
		IdBean idb = new IdBean();
		if(list!=null&&list.size()>0){
			idb=(IdBean)list.get(0);
		}else{
			idb.setTab(tab);
			idb.setUseDate(usedate);
			idb.setLen(len);
			String maxid=getLenStr(len);
			if("Y".equalsIgnoreCase(usedate)&&len>8){
				maxid=sdf.format(new Date())+maxid.substring(8);
			}
			idb.setMaxid(maxid);
			if(IdBean.class.getSimpleName().equals(tab)){
				idb.setId(new Integer(maxid));
			}
			DataBean bean = DataBean.getData(idb);
			
			idb=(IdBean)create(bean).toObject();
		}

		String maxId = idb.getMaxid();
		if("Y".equalsIgnoreCase(idb.getUseDate())&&idb.getLen()>8){
			String date = sdf.format(new Date());
			if(!maxId.startsWith(date)){
				id=date+getLenStr(idb.getLen()-8);
			}else{
				id=new BigDecimal(maxId).add(BigDecimal.ONE).toPlainString();
			}
		}else{
			id=new BigDecimal(maxId).add(BigDecimal.ONE).toPlainString();
			id=getLenStr(idb.getLen()-id.length())+id;
		}
		idb.setMaxid(id);
		DataBean bean = new DataBean(idb.getClass(),"id",idb.getId());
		Map sets = DataBean.getDataNull(idb);
		update(bean,sets);
		return id;
	}
	private String getLenStr(Integer len){
		String re="0";
		for(int i=0;i<len-1;i++){
			re="0"+re;
		}
		return re;
	}	
	
	public DataBean create(DataBean b) throws LMZException{
		try{
			if(StrU.isBlank(b.getId())){
				DataBean b1 = new DataBean(b.getClz());
				int mid = searchMaxID(b1);
				//String mid =getID(b.getClass().getSimpleName());
				b.setId(mid+1);
			}
			en.create(b);
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"create",e,b);
		}
		return b;
	}

	public void delete(DataBean b) throws LMZException {
		en.delete(b);
	}
	public void update(DataBean b, Map sets) throws LMZException {
		en.update(b, sets);
	}
	
	public int searchCount(DataBean b) throws LMZException {
		return en.searchCount(b);
	}
	public int searchMax(String atr,DataBean b) throws LMZException {
		return en.searchMax(atr,b);
	}	
	public int searchMaxID(DataBean b) throws LMZException {
		return en.searchMax("id",b);
	}

	@SuppressWarnings("unchecked")
	public PageUtil searchMap(DataBean b, Page page, String ord) throws LMZException {
		return en.searchMap(b, page,ord);
	}
	public PageUtil searchObj(DataBean b, Page page,String ord) throws LMZException {
		PageUtil pu = en.searchMap(b, page,ord);
		if(pu!=null&&!pu.isListNull()){
			List list = DataU.createObjectList(b.getClz(), pu.getList());
			pu.setList(list);
		}
		return pu;
	}

	public void execute(String sql,Object...objs) throws LMZException {
		en.execute(sql,objs);
	}
	public String executeR(String sql,Object...objs) throws LMZException {
		try{
			en.execute(sql,objs);
		}catch(Exception e){
			return e.getMessage();
		}
		return "success";
	}
	
	@Override
	public PageUtil searchMap(String sql, Page page, Object...objs)throws LMZException {
		return en.searchMap(sql, page,objs);
	}
	@Override
	public PageUtil searchObj(Class<?> c, String sql, Page page,Object...objs)throws LMZException {
		PageUtil pu = en.searchMap(sql, page,objs);
		if(pu!=null&&!pu.isListNull()){
			List list = DataU.createObjectList(c, pu.getList()); 
			pu.setList(list);
		}
		return pu;
	}
	public Integer searchInt(String sql,Object...objs) throws LMZException {
		return en.searchInt(sql, objs);
	}
	
	
	public IEntity getEn() {
		return en;
	}

	public void setEn(IEntity en) {
		this.en = en;
	}
	@Override
	public void sync(Class<?> c) throws LMZException {
		en.sync(c);
	}

}
