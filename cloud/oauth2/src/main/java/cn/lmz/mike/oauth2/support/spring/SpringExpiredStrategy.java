package cn.lmz.mike.oauth2.support.spring;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SpringExpiredStrategy implements SessionInformationExpiredStrategy {

    private static final String msg="{'success':false,'msg':'This session has been expired'}";

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(msg);
        response.flushBuffer();
    }
}
