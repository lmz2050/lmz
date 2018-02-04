package cn.lmz.mike.common.page;
import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	
	private Page page;
	private List list;
    
    
    public PageUtil(){}
    public PageUtil(List list,Page page){
    	this.list=list;
    	this.page = page;
    }
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	public boolean isListNull(){
		return list==null||list.size()==0;
	}
    
	
	public static void main(String[] args){
		
		PageUtil pu = new PageUtil();
		pu.setList(new ArrayList());
		
		List<PageUtil> list = pu.getList();
		
		System.out.println(list.size());
		
		
	}
}
