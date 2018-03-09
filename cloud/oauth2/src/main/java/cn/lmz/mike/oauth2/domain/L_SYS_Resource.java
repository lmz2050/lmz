package cn.lmz.mike.oauth2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="L_SYS_Resource")
public class L_SYS_Resource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(length=400,nullable = false)
    private String name;//资源名称
    @Column(length=1000)
    private String url; //url
    @Column(nullable = false)
    private Integer type; //1 菜单,2按钮
    @Column(nullable = false)
    private Integer pid;
    private Integer sort;
    @Column(length = 32)
    private String code; //资源key

    @Column(name="remark",length=200)
    private String remark;//备注

    @Temporal(TemporalType.TIMESTAMP)
    private Date ctm;  //创建时间
    @Column(length = 32)
    private String cby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date utm;  //创建时间
    @Column(length = 32)
    private String uby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCtm() {
        return ctm;
    }

    public void setCtm(Date ctm) {
        this.ctm = ctm;
    }

    public String getCby() {
        return cby;
    }

    public void setCby(String cby) {
        this.cby = cby;
    }

    public Date getUtm() {
        return utm;
    }

    public void setUtm(Date utm) {
        this.utm = utm;
    }

    public String getUby() {
        return uby;
    }

    public void setUby(String uby) {
        this.uby = uby;
    }
}
