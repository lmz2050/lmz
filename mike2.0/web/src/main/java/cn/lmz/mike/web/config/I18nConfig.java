package cn.lmz.mike.web.config;

import cn.lmz.mike.common.web.resolver.I18nLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class I18nConfig extends WebMvcConfigurationSupport {

    /**
     * 配置自己的国际化语言解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

    /**
     * 配置自己的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
    }

}
