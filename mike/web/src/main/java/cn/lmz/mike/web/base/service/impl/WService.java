package cn.lmz.mike.web.base.service.impl;

import java.util.List;
import java.util.Map;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.BeanUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.service.LService;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.stereotype.Service;

@Service("wService")
public class WService extends LService implements IWService {

	public <T extends BaseBean> T create(T b) throws LMZException {
		try{
			DataBean bb = DataBean.getData(b);
			create(bb);
			b.setId(bb.getId());
		}catch(Exception e){
			throw new LMZException(this.getClass().getName(),"create",e,b);
		}
		return b;
	}
	public <T extends BaseBean> void delete(Class<T> c, Map params) throws LMZException {	
		delete(DataBean.getData(c, params));
	}
	public <T extends BaseBean> void delete(T b) throws LMZException {
		try{
			O.pNull(b, "null param b");
			O.pNull(b.getId(), "null param b.id");
			delete(b.getClass(), LmzU.getParams(WebSV.ID,b.getId()));
		}catch(Exception e){
			throw new LMZException(e.getMessage(),e);
		}
	}
	public <T extends BaseBean> void delete(Class<T> c, Object id) throws LMZException {
		delete(c, LmzU.getParams(WebSV.ID,id));
		System.out.println(id);
	}
		
	public <T extends BaseBean> int searchCount(Class<T> c, Map params) throws LMZException {
		return searchCount(DataBean.getData(c, params));
	}
	
	public <T extends BaseBean> int searchMaxID(Class<T> c) throws LMZException {
		return searchMaxID(DataBean.getData(c,null));
	}

	public <T extends BaseBean> List<T> search(Class<T> c, Map params) throws LMZException {
		PageUtil pu = search(c, params,null,null);
		if(pu!=null){
			return pu.getList();
		}
		return null;
	}
	
	public <T extends BaseBean> T search(Class<T> c, Object id) throws LMZException {
		List list;
		if(WebSV.AUTO.equals(id)){
			list = search(c);
		}else{
			list = search(c, LmzU.getParams(WebSV.ID,id));
		}
		if(list!=null&&list.size()>0){
			return (T)list.get(0);
		}
		return null;
	}

	public <T extends BaseBean> Map searchMap(Class<T> c, Object id) throws LMZException {
		List list;
		if(WebSV.AUTO.equals(id)){
			list = searchMap(new DataBean(c),null,null).getList();
		}else{
			list = searchMap(new DataBean(c,WebSV.ID,id),null,null).getList();
		}
		if(list!=null&&list.size()>0){
			return (Map)list.get(0);
		}
		return null;
	}

	public <T extends BaseBean> List<T> search(Class<T> c) throws LMZException {
		PageUtil pu = search(c,null,null,null);
		if(pu!=null){
			return pu.getList();
		}
		return null;
	}
	public <T extends BaseBean> List<T> searchCO(Class<T> c, Object... objects) throws LMZException {
		PageUtil pu = search(c, LmzU.getParams(objects),null,null);
		if(pu!=null){
			return pu.getList();
		}
		return null;
	}
	public <T extends BaseBean> PageUtil search(Class<T> c, Map params, Page page, String ord) throws LMZException {
		return searchObj(DataBean.getData(c, params), page,ord);
	}
	
	public <T extends BaseBean> void update(Class<T> c, Map sets, Map params) throws LMZException {
		update(DataBean.getData(c, params), sets);
	}
	public <T extends BaseBean> void update(T b) throws LMZException {
		try{
			O.pNull(b, "null param b");
			O.pNull(b.getId(), "null param b.id");
			update(b.getClass(), BeanUtil.beanToMap(b),LmzU.getParams(WebSV.ID,b.getId()));
		}catch(Exception e){
			throw new LMZException(e.getMessage(),e);
		}
	}

	@Override
	public String executeR(String sql) throws LMZException {
		return executeR(sql,null);
	}	
}
