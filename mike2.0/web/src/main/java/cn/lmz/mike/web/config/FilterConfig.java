package cn.lmz.mike.web.config;

import cn.lmz.mike.common.web.filter.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public CommonFilter commonFilter() {
        return new CommonFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(commonFilter());//添加过滤器
        registration.addUrlPatterns("/api/*");//设置过滤路径，/*所有路径
        //registration.addInitParameter("name", "alue");//添加默认参数
        //registration.setName("MyFilter");//设置优先级
        registration.setOrder(-999);//设置优先级
        return registration;
    }
}
