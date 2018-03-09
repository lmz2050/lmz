package cn.lmz.mike.oauth2.support.spring;

import cn.lmz.mike.oauth2.domain.L_SYS_Resource;
import cn.lmz.mike.oauth2.domain.L_SYS_User;
import cn.lmz.mike.oauth2.repository.ResourceRepository;
import cn.lmz.mike.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    //根据用户名查找用户能使用的资源key
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        L_SYS_User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        List<L_SYS_Resource> list = resourceRepository.findResourceByUserId(user.getId());
        for (L_SYS_Resource r : list) {
            authSet.add(new SimpleGrantedAuthority("ROLE_" +r.getCode()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getEnable()==1?true:false,
                true,
                true,
                true,
                authSet);
    }
}
