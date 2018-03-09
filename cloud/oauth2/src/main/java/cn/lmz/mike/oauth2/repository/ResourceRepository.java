package cn.lmz.mike.oauth2.repository;

import cn.lmz.mike.oauth2.domain.L_SYS_Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<L_SYS_Resource, Long> {

    @Query(value= "select re.* from L_SYS_Resource re,L_SYS_Role_Res rr,L_SYS_User_Role ur where re.id=rr.res_id and rr.role_id = ur.rid and ur.uid=?1 ",nativeQuery = true)
    public List<L_SYS_Resource> findResourceByUserId(Integer uid);
}
