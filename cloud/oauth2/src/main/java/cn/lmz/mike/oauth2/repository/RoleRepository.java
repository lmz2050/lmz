package cn.lmz.mike.oauth2.repository;

import cn.lmz.mike.oauth2.domain.L_SYS_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<L_SYS_Role, Long> {

    @Query("select r from L_SYS_Role r where r.id in (select ur.rid from L_SYS_UserRole ur where ur.uid=?1 )")
    public List<L_SYS_Role> findRolesByUserId(Integer uid);
}
