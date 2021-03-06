package cn.lmz.mike.admin.system.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Lmzmenu  extends BaseBean {

	private String name;
	private String ename;
	private String img;
	private String pid;
	private Integer lev;
	private String url;
	private Integer type;
	private Integer isdev;
	private Integer ord;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getLev() {
		return lev;
	}
	public void setLev(Integer lev) {
		this.lev = lev;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrd() {
		return ord;
	}
	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsdev() {
		return isdev;
	}

	public void setIsdev(Integer isdev) {
		this.isdev = isdev;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
}
