package cn.lmz.mike.oauth2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="L_SYS_Login") //登录日志
public class L_SYS_Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "username", length = 120)
    private String username; //用户名

    @Column(length = 32)
    private String loginIp;
    @Column(length = 64)
    private String token; //手机
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;  //创建时间

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}


