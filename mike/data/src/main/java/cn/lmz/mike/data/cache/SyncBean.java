package cn.lmz.mike.data.cache;


import cn.lmz.mike.data.bean.DataBean;

public class SyncBean extends DataBean {

	private static final long serialVersionUID = -1542883772583564180L;
	
	private Class<?> cls;
	private Integer key;
	private Integer op;
	private Integer inactive;
	
	public Class<?> getCls() {
		return cls;
	}
	public void setCls(Class<?> cls) {
		this.cls = cls;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public Integer getOp() {
		return op;
	}
	public void setOp(Integer op) {
		this.op = op;
	}
	public Integer getInactive() {
		return inactive;
	}
	public void setInactive(Integer inactive) {
		this.inactive = inactive;
	}
}
