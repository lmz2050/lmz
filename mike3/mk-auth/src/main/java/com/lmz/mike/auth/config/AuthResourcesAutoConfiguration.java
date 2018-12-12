package com.lmz.mike.auth.config;

import com.lmz.mike.auth.pojo.JwtAccessToken;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/3 1:11
 * 4
 */
@Configuration
public class AuthResourcesAutoConfiguration implements JwtAccessTokenConverterConfigurer {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessToken();
    }

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(jwtAccessTokenConverter());
    }
}
