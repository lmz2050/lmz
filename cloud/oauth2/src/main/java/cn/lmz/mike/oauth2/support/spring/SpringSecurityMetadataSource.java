package cn.lmz.mike.oauth2.support.spring;

import cn.lmz.mike.oauth2.domain.L_SYS_Resource;
import cn.lmz.mike.oauth2.repository.ResourceRepository;
import cn.lmz.mike.oauth2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 加载资源与权限的对应关系
 */
@Component
public class SpringSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    //初始化准备资源url及key
    @PostConstruct//被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
    private void loadResourceDefine() {  //一定要加上@PostConstruct注解
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<L_SYS_Resource> list = resourceRepository.findAll();
            for (L_SYS_Resource resources : list) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                // 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resources.getCode());
                configAttributes.add(configAttribute);
                resourceMap.put(resources.getUrl(), configAttributes);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    // 根据URL，找到相关的权限配置key
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //  System.out.println("requestUrl is " + requestUrl);
        if(resourceMap == null) {
            loadResourceDefine();
        }
        //System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
        if(requestUrl.indexOf("?")>-1){
            requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
        }
        Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
        return configAttributes;
    }
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
