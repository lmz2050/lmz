package cn.lmz.mike.oauth2.repository;

import cn.lmz.mike.oauth2.domain.L_SYS_Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository  extends JpaRepository<L_SYS_Login, Long> {

}
