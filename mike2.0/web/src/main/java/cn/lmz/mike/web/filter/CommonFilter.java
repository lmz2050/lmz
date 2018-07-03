package cn.lmz.mike.web.filter;


import cn.lmz.mike.common.web.response.JsonRes;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.nanoTime();
        //添加日志追踪号
        String txId = UUID.randomUUID().toString().replaceAll("-", "").substring(0,14);
        MDC.put("txId", txId);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        try {
            //Wrap Request
            ServletRequestWrapper requestWrapper = new ServletRequestWrapper(httpRequest);
            String body = IOUtils.toString(requestWrapper.getInputStream(), "UTF-8");

            //MDC.put("channel", channel);

            filterChain.doFilter(requestWrapper, servletResponse);
        }finally {
            double invokeTime = (System.nanoTime() - startTime) / 1000 / 1000.0;
            log.info(">>>Complete Request[{}] 耗时: {} ms", httpRequest.getRequestURI(), invokeTime);
            MDC.remove("txId");
            MDC.remove("channel");
        }
    }

    @Override
    public void destroy() {

    }

    private void returnResponse(JsonRes jsonRes, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(JSON.toJSONString(jsonRes));
        response.setContentType("application/json");
    }
}
