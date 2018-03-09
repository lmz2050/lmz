package cn.lmz.mike.oauth2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "L_SYS_User")//用户表
public class L_SYS_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "username",nullable = false, length = 120)
    private String username; //用户名
    @Column(name = "password",nullable = false, length = 120)
    private String password;//用户密码

    @Column(length = 120)
    private String name; //姓名
    @Column(length = 20)
    private String phone; //手机
    @Column(length = 30)
    private String tel;  //电话
    @Column(length = 50)
    private String email; //邮箱

    @Column(nullable = false)
    private Integer enable;//1:有效，2:无效

    @Temporal(TemporalType.TIMESTAMP)
    private Date ctm;  //创建时间
    @Column(length = 30)
    private String cby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date utm;  //创建时间
    @Column(length = 30)
    private String uby;

    public L_SYS_User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
