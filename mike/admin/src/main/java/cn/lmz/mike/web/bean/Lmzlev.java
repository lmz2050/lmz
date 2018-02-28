package cn.lmz.mike.web.bean;


import cn.lmz.mike.data.annotation.Lbean;
import cn.lmz.mike.web.base.bean.BaseBean;

@Lbean
public class Lmzlev extends BaseBean {

	private String name;
	private String def;
	private Integer points;
	private Integer discount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
