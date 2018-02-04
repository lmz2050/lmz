package cn.lmz.mike.data;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.util.LanU;

import java.util.Map;


public class EntityLan extends Entity {

	@SuppressWarnings("unchecked")
	public void delete(DataBean b) throws LMZException {
		DataU.setLan(b.getClz(), b, LanU.getLan());
		super.delete(b);
	}
	@SuppressWarnings("unchecked")
	public int searchCount(DataBean b) throws LMZException {
		DataU.setLan(b.getClz(), b, LanU.getLan());
		return super.searchCount(b);
	}
	public int searchMax(String atr,DataBean b) throws LMZException {
		//DataU.setLan(b.getClz(), b, DataU.getLan());
		return super.searchMax(atr,b);
	}
	@SuppressWarnings("unchecked")
	public PageUtil searchMap(DataBean b, Page page, String ord)throws LMZException {
		DataU.setLan(b.getClz(), b, LanU.getLan());
		PageUtil pu = super.searchMap(b,page, ord);
		return pu;
	}
	public DataBean create(DataBean b) throws LMZException {
		DataU.setLan(b.getClz(), b, LanU.getLan());
		return super.create(b);
	}

	@SuppressWarnings("unchecked")
	public void update(DataBean b, Map sets) throws LMZException {
		DataU.setLan(b.getClz(), b, LanU.getLan());
		super.update(b, sets);
	}
	
	public void sync(Class<?> c) throws LMZException {
		PageUtil pu = super.searchMap(new DataBean(c,LanU.LAN,LanU.LOCALE_CN,LanU.LAN_C,LanU.LOCALE_CN),null, null);
		if(pu!=null&&!pu.isListNull()){
			for(int i=0;i<pu.getList().size();i++){
				Map<String,Object> m = (Map<String,Object>)pu.getList().get(i);
				DataBean bean = new DataBean(c);
				try{
					for(Map.Entry<String,Object> en:m.entrySet()){
						if(BeanUtil.containAttr(c,en.getKey())){
							bean.put(en.getKey(), en.getValue());
						}
					}
					create(bean);
				}catch(Exception e){
					O.pn(e.getMessage());
				}
			}
		}
	}
}
