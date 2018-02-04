package cn.lmz.mike.common;

import cn.lmz.mike.common.base.DateU;
import cn.lmz.mike.common.base.NumU;
import cn.lmz.mike.common.base.PropU;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.cmmd.CmdU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.msg.MsgU;
import cn.lmz.mike.common.cache.CacheU;
import cn.lmz.mike.common.excel.Excel;
import cn.lmz.mike.common.ftp.FtpU;
import cn.lmz.mike.common.invoke.InvokeU;
import cn.lmz.mike.common.io.FileU;
import cn.lmz.mike.common.io.ZipU;
import cn.lmz.mike.common.json.JsonU;
import cn.lmz.mike.common.mail.MailU;
import cn.lmz.mike.common.sec.SecurityU;

import java.lang.reflect.Method;
import java.util.*;

public class MC extends StrU {

    public static final class string extends StrU {};
    public static final class date extends DateU {};
    public static final class excel extends Excel {};
    public static final class ftp extends FtpU {};
    public static final class invoke extends InvokeU {};
    public static final class file extends FileU {};
    public static final class zip extends ZipU {};
    public static final class json extends JsonU {};
    public static final class log extends O {};
    public static final class mail extends MailU {};
    public static final class msg extends MsgU {};
    public static final class cmd extends CmdU {};
    public static final class sec extends SecurityU {};
    public static final class cache extends CacheU {};
    public static final class prop extends PropU {};
    public static final class number extends NumU {};


    public static void help(){
        System.out.println("S====================help()=====================");
        Class cs[] = MC.class.getClasses();
        for(Class c:cs){
            System.out.println("==>"+c.getName().split("\\$")[1]);
        }
        System.out.println("E====================help()=====================");
    }

    public static void help(Object o){
        help(o==null?null:o.getClass());
    }
    public static void help(Class c){
        System.out.println("S====================help("+c.getName()+")=====================");
        if(c==null){
            System.out.println(null+" class ");
            return;
        }
        List<Method> mlist = InvokeU.getAllPubMethods(c);
        for(Method method:mlist){
            String mstr = MC.string.getMethodString(method);
            System.out.println(mstr);
        }
        System.out.println("E====================help("+c.getName()+")=====================");
    }


    public static void main(String[] args){


    }

}
