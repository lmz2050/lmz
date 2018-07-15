package cn.lmz.mike.web.base.util;


import cn.lmz.mike.web.base.bean.Lmzadmin;

import java.util.HashMap;
import java.util.Map;

public class OnlineUserMap {
    public static Map<String , Lmzadmin> onlineuser = new HashMap<String , Lmzadmin >();


    public static Map<String , Lmzadmin > getOnlineuser(){
        return onlineuser;
    }


    public static void addOnlineUser(String userId, Lmzadmin userinfo){
        onlineuser.put(userId, userinfo);
    }


    public static void removeUser(String sessionId){
        for(String userId : onlineuser.keySet()){
            if(onlineuser.get(userId).getSessionId().equals(sessionId)){
                onlineuser.remove(userId);
                break;
            }
        }
    }

}