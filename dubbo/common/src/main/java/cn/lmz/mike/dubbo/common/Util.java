package cn.lmz.mike.dubbo.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class Util {

    public static String getMsg(){
        String ip="";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String time = new Date().toString();

        return "IP:"+ip+",TIME:"+time;
    }




}
