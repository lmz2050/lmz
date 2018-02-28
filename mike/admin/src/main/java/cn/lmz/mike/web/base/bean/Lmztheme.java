package cn.lmz.mike.web.base.bean;


import cn.lmz.mike.data.annotation.Lbean;

@Lbean
public class Lmztheme  extends BaseBean{

	private String name;
	private String cname;
	private String img;
	private Integer ord;
	private String def;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getOrd() {
		return ord;
	}
	public void setOrd(Integer ord) {
		this.ord = ord;
	}
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
}
