package cn.lmz.mike.web.base.service;

import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.support.IService;
import cn.lmz.mike.web.base.bean.BaseBean;

import java.util.List;
import java.util.Map;



public interface IWService extends IService {
	public <T extends BaseBean> T create(T b) throws LMZException;
	public <T extends BaseBean> void delete(Class<T> c, Map params) throws LMZException;
	public <T extends BaseBean> void delete(T b) throws LMZException;
	public <T extends BaseBean> void delete(Class<T> c, Object id) throws LMZException;
	public <T extends BaseBean> int searchCount(Class<T> c, Map params) throws LMZException;
	public <T extends BaseBean> int searchMaxID(Class<T> c) throws LMZException;
	public <T extends BaseBean> List<T> search(Class<T> b, Map params) throws LMZException;
	public <T extends BaseBean> T search(Class<T> c, Object id) throws LMZException;
	public <T extends BaseBean> Map searchMap(Class<T> c, Object id) throws LMZException;
	public <T extends BaseBean> List<T> search(Class<T> c) throws LMZException;
	public <T extends BaseBean> List<T> searchCO(Class<T> c, Object... objects) throws LMZException;
	public <T extends BaseBean> PageUtil search(Class<T> c, Map params, Page page, String ord) throws LMZException;
	public <T extends BaseBean> void update(Class<T> c, Map sets, Map params) throws LMZException;
	public <T extends BaseBean> void update(T b) throws LMZException;
	public String executeR(String sql) throws LMZException;
	
}
