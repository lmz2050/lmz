package cn.lmz.mike.oauth2.domain;

import javax.persistence.*;

@Entity
@Table(name = "L_SYS_RoleRes")
public class L_SYS_RoleRes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer roleId; //角色ID

    @Column(nullable = false)
    private Integer resId;//资源ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }
}
