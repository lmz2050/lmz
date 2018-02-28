package cn.lmz.mike.web.base.util.listener;

import cn.lmz.mike.common.V;
import cn.lmz.mike.common.log.O;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

public class Log4j2ConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String fileName = getContextParam(sce);
        O.initLog(fileName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @SuppressWarnings("unchecked")
    private String getContextParam(ServletContextEvent event) {
        Enumeration<String> names = event.getServletContext().getInitParameterNames();
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            String value = event.getServletContext().getInitParameter(name);
            if(name.trim().equals(V.LOG_CFG))
            {
                return value;
            }
        }
        return null;
    }
}
