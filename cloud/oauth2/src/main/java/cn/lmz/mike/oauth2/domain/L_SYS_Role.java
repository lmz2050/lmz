package cn.lmz.mike.oauth2.domain;

import javax.persistence.*;
import java.util.Date;

//角色表
@Entity
@Table(name="L_SYS_Role")
public class L_SYS_Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name="code",nullable = false,length=100)
    private String code;//角色编码
    @Column(name="name",nullable = false,length=200)
    private String name;//角色名称

    @Temporal(TemporalType.TIMESTAMP)
    private Date ctm;  //创建时间
    @Column(length = 30)
    private String cby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date utm;  //创建时间
    @Column(length = 30)
    private String uby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
