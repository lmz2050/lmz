package com.lmz.mike.auth.pojo;

import com.lmz.mike.auth.bean.LmzUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/2 23:38
 * 4
 */
public class LmzUserDetail implements UserDetails, CredentialsContainer {

    private final LmzUser lmzUser;
    private final org.springframework.security.core.userdetails.User user;

    public LmzUserDetail(LmzUser lmzUser, User user) {
        this.lmzUser = lmzUser;
        this.user = user;
    }


    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public LmzUser getLmzUser() {
        return lmzUser;
    }
}
