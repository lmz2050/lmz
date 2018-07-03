package cn.lmz.mike.data.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdU {


    public static Random random = new Random();
    public static String first="0";

    private static String getCurrTimeString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        Date date = new Date();
        return sdf.format(date);
    }
    public static String getDecimalString(long x){
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(x); //次id即为四位不重复的流水号
    }
    public static String getRandomString(){
        return getDecimalString(random.nextInt());
    }
    public static String getId(){
        return new StringBuilder(first).append(getCurrTimeString())
                .append(getDecimalString(Thread.currentThread().getId()))
                .append(getRandomString()).toString();
    }


    public static void main(String[] args) {
        System.out.println(getCurrTimeString());
    }
}
