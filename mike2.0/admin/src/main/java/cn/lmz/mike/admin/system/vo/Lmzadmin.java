package cn.lmz.mike.admin.system.vo;

import cn.lmz.mike.common.web.annotation.Lbean;

@Lbean
public class Lmzadmin {
    private String id;

    private String username;

    private String userpwd;

    private String useremail;

    private String lastip;

    private String lastlogin;

    private Integer type;

    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip == null ? null : lastip.trim();
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin == null ? null : lastlogin.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}