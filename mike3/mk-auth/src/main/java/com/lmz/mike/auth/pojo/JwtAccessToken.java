package com.lmz.mike.auth.pojo;

import com.alibaba.fastjson.JSON;
import com.lmz.mike.auth.bean.LmzUser;
import com.lmz.mike.auth.constant.AuthConst;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * Created by fp295 on 2018/4/16.
 * 自定义JwtAccessToken转换器
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        // 设置额外用户信息
        LmzUser baseUser = ((LmzUserDetail) authentication.getPrincipal()).getLmzUser();
        baseUser.setUserpwd(null);
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put(AuthConst.USER_INFO, baseUser);

        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(AuthConst.USER_INFO,convertUserData(map.get(AuthConst.USER_INFO)));

    }

    private LmzUser convertUserData(Object map) {
        String json = JSON.toJSONString(map);
        LmzUser user = new LmzUser();
        return user;
    }
}
