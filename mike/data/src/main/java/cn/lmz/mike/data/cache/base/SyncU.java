package cn.lmz.mike.data.cache.base;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.cache.CacheDao;
import cn.lmz.mike.data.cache.ISyncBean;
import cn.lmz.mike.data.cache.SyncBean;
import cn.lmz.mike.data.util.define.DataSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncU {

	private static final Logger log = LoggerFactory.getLogger(SyncU.class);

	public static void addCync(DataBean b, Integer op) throws Exception {
		try{
			if((b instanceof ISyncBean)){
				show(b,op);
				CacheDao cd = new CacheDao();
				
				SyncBean sb = findInSynB(b,cd);
				if(sb==null){
					sb = getNew(b,cd);
					sb.setOp(op);
				}else if(op!= DataSV.UPDATE){
					sb.setOp(op);
				}

				cd.create(sb);
			}
		}catch(Exception e){
			throw new LMZException("SyncU","addCync(BaseBean b, Integer op)",e,b,op);
		}	
	}
	
	public static void show(DataBean b,Integer op){
		log.info("add to sync - [name:"+b.getClass().getName()+",id:"+b.getId()+",op:"+op);
	}
	
	@SuppressWarnings("unchecked")
	public static SyncBean findInSynB(DataBean b,CacheDao cd) throws LMZException{
		SyncBean sb = null;
		Map params = new HashMap();
		params.put("cls", b.getClass());
		params.put("key", b.getId());
		params.put("inactive", 0);
		List list = cd.search(DataSV.SYNCBEAN,params);
		if(list!=null&&list.size()>0){
			sb = (SyncBean)list.get(0);
		}
		return sb;
	}
	
	public static SyncBean getNew(DataBean b,CacheDao cd) throws LMZException{
		SyncBean sb = new SyncBean();
		b.setClz(DataSV.SYNCBEAN);
		int id = cd.searchMax(DataBean.ID,b)+1;
		sb.setId(id);
		sb.setCls(b.getClass());
		sb.setKey(id);
		sb.setInactive(0);
		
		return sb;
	}
	
}
