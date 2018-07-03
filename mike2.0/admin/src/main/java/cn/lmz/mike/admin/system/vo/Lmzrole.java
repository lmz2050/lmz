package cn.lmz.mike.admin.system.vo;

import cn.lmz.mike.common.web.annotation.Lbean;

@Lbean
public class Lmzrole {
    private String id;

    private String name;

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
}