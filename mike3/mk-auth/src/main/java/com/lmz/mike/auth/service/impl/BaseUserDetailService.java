package com.lmz.mike.auth.service.impl;


import com.lmz.mike.auth.bean.LmzRole;
import com.lmz.mike.auth.bean.LmzUser;
import com.lmz.mike.auth.filter.MkLoginAuthenticationFilter;
import com.lmz.mike.auth.service.LmzRoleService;
import com.lmz.mike.auth.service.LmzUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fp295 on 2018/4/16.
 */
@Service
public class LmzUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private LmzUserService lmzUserService;

    @Resource
    private LmzRoleService lmzRoleService;

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        LmzUser lmzUser;
        String[] parameter;
        int index = var1.indexOf("&:@");
        if (index != -1) {
            parameter = var1.split("&:@");
        }else {
            // 如果是 refresh_token 不分割
            parameter = new String[]{MkLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_DEFAULT, var1};
        }

        // 手机验证码调用FeignClient根据电话号码查询用户
        if(MkLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(parameter[0])){
            lmzUser = lmzUserService.getUserByPhone(parameter[1]);
            if(lmzUser == null || StringUtils.isBlank(lmzUser.getUsername())){
                logger.error("找不到该用户，手机号码：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，手机号码：" + parameter[1]);
            }
        } else if(MkLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_QR.equals(parameter[0])){
            // 扫码登陆根据token从redis查询用户
            lmzUser = null;
        } else {
            // 账号密码登陆调用FeignClient根据用户名查询用户
            lmzUser = lmzUserService.getUserByUserName(parameter[1]);
            if(lmzUser == null || StringUtils.isBlank(lmzUser.getUsername())){
                logger.error("找不到该用户，用户名：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[1]);
            }
        }


        // 调用FeignClient查询角色
        List<LmzRole> rlist = lmzRoleService.getRoleByUserId(lmzUser.getId());

        //调用FeignClient查询菜单
        ResponseData<List<BaseModuleResources>> baseModuleResourceListResponseData = baseModuleResourceService.getMenusByUserId(baseUser.getId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roles);

        // 存储菜单到redis
        if( ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getCode()) && baseModuleResourceListResponseData.getData() != null){
            resourcesTemplate.delete(baseUser.getId() + "-menu");
            baseModuleResourceListResponseData.getData().forEach(e -> {
                resourcesTemplate.opsForList().leftPush(baseUser.getId() + "-menu", e);
            });
        }

        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(baseUser.getUserName(),
                baseUser.getPassword(), isActive(baseUser.getActive()), true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    private boolean isActive(int active){
        return active == 1 ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(BaseUser baseUser, List<BaseRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        redisTemplate.delete(baseUser.getId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
            authorities.add(authority);
            //存储角色到redis
            redisTemplate.opsForList().rightPush(baseUser.getId(), e);
        });
        return authorities;
    }
}
