package cn.lmz.mike.admin.system.vo;

import cn.lmz.mike.common.web.annotation.Lbean;

@Lbean
public class Lmzweb {
    private String id;

    private String name;

    private String domain;

    private String logo;

    private String titles;

    private String keywords;

    private Integer disabled;

    private String phone;

    private String tel;

    private String addr;

    private String mdomain;

    private String mailfromhost;

    private String mailfromuname;

    private String mailfrompwd;

    private String mailtohost;

    private String explains;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles == null ? null : titles.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getMdomain() {
        return mdomain;
    }

    public void setMdomain(String mdomain) {
        this.mdomain = mdomain == null ? null : mdomain.trim();
    }

    public String getMailfromhost() {
        return mailfromhost;
    }

    public void setMailfromhost(String mailfromhost) {
        this.mailfromhost = mailfromhost == null ? null : mailfromhost.trim();
    }

    public String getMailfromuname() {
        return mailfromuname;
    }

    public void setMailfromuname(String mailfromuname) {
        this.mailfromuname = mailfromuname == null ? null : mailfromuname.trim();
    }

    public String getMailfrompwd() {
        return mailfrompwd;
    }

    public void setMailfrompwd(String mailfrompwd) {
        this.mailfrompwd = mailfrompwd == null ? null : mailfrompwd.trim();
    }

    public String getMailtohost() {
        return mailtohost;
    }

    public void setMailtohost(String mailtohost) {
        this.mailtohost = mailtohost == null ? null : mailtohost.trim();
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains == null ? null : explains.trim();
    }
}