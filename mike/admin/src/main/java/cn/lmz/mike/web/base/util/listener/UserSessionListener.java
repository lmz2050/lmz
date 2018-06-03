package cn.lmz.mike.web.base.util.listener;

import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.util.OnlineUserMap;
import cn.lmz.mike.web.base.util.WebSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class UserSessionListener implements HttpSessionAttributeListener {

    private static Logger logger = LoggerFactory.getLogger(UserSessionListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        String name = se.getName();
        String sessionId = se.getSession().getId();
        if (name == WebSV.admin){
            Lmzadmin userInfo = (Lmzadmin)se.getSession().getAttribute(WebSV.admin);
            logger.info("add session:"+userInfo.getUsername()+"--"+sessionId);
            new OnlineUserMap().addOnlineUser(userInfo.getId(), userInfo);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        String name = se.getName();
        String sessionId = se.getSession().getId();
        if (name == WebSV.admin){
            logger.info("remove session:"+"--"+sessionId);
            new OnlineUserMap().removeUser(sessionId);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        String name = se.getName();
        String sessionId = se.getSession().getId();
        if (name == WebSV.admin){
            Lmzadmin userInfo = (Lmzadmin)se.getSession().getAttribute(WebSV.admin);
            logger.info("replace session:"+userInfo.getUsername()+"--"+sessionId);
            new OnlineUserMap().addOnlineUser(userInfo.getId(), userInfo);
        }
    }

}