package com.lmz.mike.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/2 17:22
 * 4
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Resource
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //return new BCryptPasswordEncoder();
//        String idForEncode = "bcrypt";
//        Map encoders = new HashMap<>();
//        encoders.put(idForEncode, new BCryptPasswordEncoder());
//        encoders.put("noop", NoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//        encoders.put("scrypt", new SCryptPasswordEncoder());
//        encoders.put("sha256", new StandardPasswordEncoder());
//
//        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
//        return passwordEncoder;
//    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    // 配置授权管理器
            .authorizeRequests().accessDecisionManager(getAccessDecisionManager())
            // 匹配全部请求鉴权认证
            .antMatchers("/**").authenticated()
            // 由于使用的是JWT，我们这里不需要csrf
            .and().csrf().disable();
    }

    @Bean
    public AccessDecisionManager getAccessDecisionManager() {
        return new AccessDecisionManagerIml();
    }

    @Bean
    public TokenExtractor getTokenExtractor() {
        return new BearerTokenExtractor();
    }
}
