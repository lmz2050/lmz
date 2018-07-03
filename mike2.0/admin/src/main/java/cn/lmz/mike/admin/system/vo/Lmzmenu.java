package cn.lmz.mike.admin.system.vo;

import cn.lmz.mike.common.web.annotation.Lbean;

@Lbean
public class Lmzmenu {
    private String id;

    private String name;

    private String img;

    private String pid;

    private Integer lev;

    private Integer ord;

    private String url;

    private Integer type;

    private Integer isdev;

    private String ename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        this.ename = ename == null ? null : ename.trim();
    }
}