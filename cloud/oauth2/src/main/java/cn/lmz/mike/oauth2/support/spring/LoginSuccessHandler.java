package cn.lmz.mike.oauth2.support.spring;

import cn.lmz.mike.oauth2.domain.L_SYS_Login;
import cn.lmz.mike.oauth2.repository.LoginLogRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    public static final String TO_URL="TO_URL";  //登录后跳转地址key
    public static final String TO_TYPE="TO_TYPE"; //跳转方式key F,R

    private Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
    private String defaultTargetUrl="/main"; //登录后跳转地址
    private String defaultTargetType = "F"; //跳转方式 F,R

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private LoginLogRepository loginLogRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        User user = (User)authentication.getPrincipal();
        String ip = this.getIpAddress(request);

        String r_to_url = (String)request.getParameter(LoginSuccessHandler.TO_URL);
        String r_to_type = (String)request.getParameter(LoginSuccessHandler.TO_TYPE);
        if(StringUtils.isBlank(r_to_url)){
            r_to_url = defaultTargetUrl;
        }
        if(StringUtils.isBlank(r_to_type)){
            r_to_type = defaultTargetType;
        }
        //保存登录日志
        this.saveLoginInfo(user,ip);

        if("F".equalsIgnoreCase(r_to_type)){
            logger.info("user:{}-ip:{} Login success,Forwarding to {} ",user.getUsername(),ip,r_to_url);

            request.getRequestDispatcher(r_to_url).forward(request, response);
        }else{
            logger.info("user:{}-ip:{} Login success,Redirecting to {} ",user.getUsername(),ip,r_to_url);

            this.redirectStrategy.sendRedirect(request, response,r_to_url);
        }
    }

    @Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
    public void saveLoginInfo(User user,String ip){
        try {
            L_SYS_Login login = new L_SYS_Login();
            login.setUsername(user.getUsername());
            login.setLoginIp(ip);
            login.setLast_used(new Date());

            loginLogRepository.save(login);

        } catch (DataAccessException e) {
            if(logger.isWarnEnabled()){
                logger.info("无法更新用户登录信息至数据库");
            }
        }
    }

    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}