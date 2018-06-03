package cn.lmz.mike.web.base.util;


import cn.lmz.mike.web.base.bean.Lmzadmin;

import java.util.HashMap;
import java.util.Map;

public class OnlineUserMap {
    public static Map<String , Lmzadmin> onlineuser = new HashMap<String , Lmzadmin >();

    /**
     * 得到在线用户
     * @return
     */
    public static Map<String , Lmzadmin > getOnlineuser(){
        return onlineuser;
    }

    /**
     * 添加在线用户
     * @param sessionId
     * @param userName
     * @return
     */
    public void addOnlineUser(String userId, Lmzadmin userinfo){
        onlineuser.put(userId, userinfo);
    }

    /**
     * 移除用户
     * @param userName
     */
    public void removeUser(String sessionId){
        for(String userId : onlineuser.keySet()){
            if(onlineuser.get(userId).getSessionId().equals(sessionId)){
                onlineuser.remove(userId);
                break;
            }
        }
    }

}