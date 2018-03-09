package cn.lmz.mike.oauth2.repository;

import cn.lmz.mike.oauth2.domain.L_SYS_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<L_SYS_User, Long> {

    public L_SYS_User findByUsername(String userName);
}
