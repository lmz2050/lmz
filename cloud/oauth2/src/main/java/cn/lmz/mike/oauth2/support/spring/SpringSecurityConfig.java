package cn.lmz.mike.oauth2.support.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SpringUserService springUserService;

    @Resource
    private SpringSecurityFilter springSecurityFilter;


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web)throws Exception {
        // 设置不拦截规则
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/font-awesome/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .sessionManagement().maximumSessions(1)
        /**
         * 自定义session过期策略，替代默认的{@link ConcurrentSessionFilter.ResponseBodySessionInformationExpiredStrategy}，
         * 复写onExpiredSessionDetected方法，默认方法只输出异常，没业务逻辑。这里需要返回json
         */
        .expiredSessionStrategy(springExpiredStrategy());
        http
        .addFilterBefore(springSecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
        .authorizeRequests()
        .antMatchers("/index","/home","/login","/logout","/error").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login").failureUrl("/login")
        .loginProcessingUrl("/loginV") //登录请求拦截的url,也就是form表单提交时指定的action
        //.defaultSuccessUrl("/main")    //登录后跳转地址 跟loginSuccessHandler不能共存
        .successHandler(loginSuccessHandler())//code3
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .and()
        .rememberMe()
        .tokenValiditySeconds(1209600);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        //启用内存用户存储
        /*auth.inMemoryAuthentication()
        .withUser("user1").password("123456").roles("USER").and()
        .withUser("admin").password("admin").roles("USER","ADMIN");*/
        //
        //给予数据库表认证
        /*auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username,password,enable from t_user where username=?")
        .authoritiesByUsernameQuery("select username,rolename from t_role where username=?");
        */
        //配置自定义的用户服务
        auth.userDetailsService(springUserService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public SpringExpiredStrategy springExpiredStrategy(){
        return new SpringExpiredStrategy();
    }

    // Code5----------------------------------------------
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    // Code3----------------------------------------------
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
