package cn.lmz.mike.admin.system.util.ltree;

import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.Data;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.base.util.WebSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class LTreeU {
	
	public static final String ID="id";
	public static final String CHILDREN = "children";
	public static final String PID="pid";
	public static final String STATUS="status";
	public static final String STATUS_CLOSED="closed";

	public static List<Map> getOption(List<Map> tlist,Integer ii){
		List<Map> mlist = new ArrayList<Map>();
		ii++;
		for(int i=0;i<tlist.size();i++){
			Map node = tlist.get(i);
			mlist.add(node);
			List clist = (List)node.get(CHILDREN);
			if(clist!=null&&clist.size()>0){
				mlist.addAll(getOption(clist,ii));
			}
		}
		ii--;
		return mlist;
	}
	
	public static List<Map> buildTree(List<Map> treeNodes,Integer rootId) {
		List<Map> results = new ArrayList<Map>();

		Map<String, Map> aidMap = new LinkedHashMap<String, Map>();
		for (Map node : treeNodes) {
			aidMap.put(node.get(ID)+"", node);
		}
		treeNodes = null;

		Set<Entry<String, Map>> entrySet = aidMap.entrySet();
		for (Entry<String, Map> entry : entrySet) {
			String pid = entry.getValue().get(PID)+"";
			//entry.getValue().put("text", entry.getValue().get("name"));
			Map node = aidMap.get(pid);
			if (node == null||pid.equals(rootId+"")) {
				results.add(entry.getValue());
			} else {
				List<Map> children = (List<Map>)node.get(CHILDREN);
				if (children == null) {
					children = new ArrayList<Map>();
					node.put(CHILDREN,children);
					node.put(STATUS, STATUS_CLOSED);
				}
				children.add(entry.getValue());
			}
		}
		aidMap = null;
		return results;
	}
			
	public static List getList(IWService service, String clz, Integer pid) throws LMZException {
		if(pid==null) pid=0;
		Integer ii=0;
		
		Class<?> c = Data.getClass(clz);
		PageUtil pu = service.searchMap(new DataBean(c),null,null);
		List<Map> tlist = buildTree(pu.getList(), pid);
		
		Map root = new HashMap();
		root.put(ID, 0);
		root.put("name","根目录");
		root.put("grade", -1);
		root.put("text", "根目录");
		
		List list = new ArrayList();
		list.add(root);
		
		ii++;
		for(int i=0;i<tlist.size();i++){
			Map node = tlist.get(i);
			list.add(node);
			List<Map> children = (List<Map>)node.get(CHILDREN);
			if(children!=null&&children.size()>0){
				list.addAll(LTreeU.getOption(children,ii));
			}
		}
		
		return list;
		
	}
	
	
	public String getPosition(IWService service,String clzC,String clz,Integer id) throws LMZException{
		Class<?> c = null;
		String str="";
		if(StrU.isBlank(clz)){
			c = Data.getClass(clzC);
			PageUtil pu = service.searchMap(new DataBean(c, WebSV.ID,id),null,null);
			if(pu!=null&&!pu.isListNull()){
				Map m = (Map)pu.getList().get(0);
				String pid = (String)m.get(WebSV.PID);
				str=">"+m.get("name")+str;
				if(StrU.isBlank(pid)&&!"0".equals(pid)){
					
				}
			}
		}
		return null;
		
	}

	
	
	public static List getEasyList(IWService service,String clz,Integer pid) throws LMZException{
		if(pid==null) pid=0;
		Integer ii=0;
		
		Class<?> c = Data.getClass(clz);
		PageUtil pu = service.searchMap(new DataBean(c),null,null);
		List<Map> tlist = LTreeU.convertTreeNodeList(pu.getList());
		tlist = buildTree(tlist, pid);
		
		List infoL = new ArrayList<Map>();
		Map n = new HashMap();
		n.put(WebSV.ID, "0");
		n.put("text", "根目录");
		Map<String,Object> attr = new HashMap<String,Object>();
		attr.put("lev", -1);
		n.put("attributes", attr);
		infoL.add(n);
		ii++;
		for(int i=0;i<tlist.size();i++){
			Map node = tlist.get(i);
			infoL.add(node);
			if(node.get("children")!=null&&((List)node.get("children")).size()>0){
				infoL.addAll(LTreeU.getOption((List)node.get("children"),ii));
			}
		}
		
		return infoL;
		
	}	
	//EasyUi
	public static List<Map> convertTreeNodeList(List<Map> mlist) {
		List<Map> nodes = null;
		
		if(mlist != null){
			int idx=0;
			nodes = new ArrayList<Map>();
			for(Map m:mlist){
				
				Map node = new HashMap();
				node.put(WebSV.ID, m.get(WebSV.ID));
				node.put(WebSV.PID, m.get(WebSV.PID));
				node.put("text", m.get(WebSV.NAME));
				node.put("checked", false);

				Map<String,Object> atrs = new LinkedHashMap<String,Object>();
				atrs.put("url",m.get("url"));
				atrs.put("iconCls",m.get("img"));
				atrs.put("lev",m.get("lev"));
				if("1".equals(m.get("lev"))){
					atrs.put("idx", idx);
					idx++;
				}else{
					atrs.put("idx", -1);
				}
				node.put("attributes", atrs);
				
				nodes.add(node);
			}
		}
		
		return nodes;
	}

	public static void convertWithRole(List<Map> mlist,String mids) {
		if(mlist != null){
			for(Map node:mlist){
				if(mids!=null&&mids.contains("@"+node.get(WebSV.ID)+"@")){
					node.put("checked", true);
				}else{
					node.put("checked", false);
				}
				if(node.get(CHILDREN)!=null&&((List)node.get(CHILDREN)).size()>0){
					convertWithRole((List)node.get(CHILDREN),mids);
				}
			}
		}
	}	
	
	
	
}
