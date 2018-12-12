package com.lmz.mike.auth.utils;

import com.lmz.mike.auth.bean.LmzUser;
import com.lmz.mike.auth.constant.AuthConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class AccessTokenUtils {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 从token获取用户信息
     * @return
     */
    public LmzUser getUserInfo(){
        return (LmzUser) getAccessToken().getAdditionalInformation().get(AuthConst.USER_INFO);
    }

    public List getRoleInfo(){
        String redisKey = AuthUtil.getRedisKey(getUserInfo().getId());
        String userId = getUserInfo().getId();
        long size = redisTemplate.opsForList().size(redisKey);
        return redisTemplate.opsForList().range(redisKey, 0, size);
    }

    public List getMenuInfo(){
        String redisKey = AuthUtil.getRedisKey(getUserInfo().getId() + "-menu");
        long size = redisTemplate.opsForList().size(redisKey);
        return redisTemplate.opsForList().range(redisKey, 0, size);
    }

    private OAuth2AccessToken getAccessToken() throws AccessDeniedException {
        OAuth2AccessToken token;
        // 抽取token
        Authentication a = tokenExtractor.extract(request);
        try {
            // 调用JwtAccessTokenConverter的extractAccessToken方法解析token
            token = tokenStore.readAccessToken((String) a.getPrincipal());
        } catch(Exception e) {
            throw new AccessDeniedException("AccessToken Not Found.");
        }
        return token;
    }
}
