package cn.lmz.mike.lmz.sys.util;


import cn.lmz.mike.common.MC;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;

import java.util.Map;

public class SysU {

    private static Context ctx;

    public static void setCtx(Context ctx) {
        SysU.ctx = ctx;
    }

    public static void showPkgs(){
        Map<String,Class<?>> pkgMap = (Map<String,Class<?>>)ctx.getCfg().get(Const.PKG_MAP);
        for(Map.Entry<String,Class<?>> en:pkgMap.entrySet()){
            System.err.println(en.getKey()+"=="+getClsStr(en.getValue()));
        }
    }
    public static void showPkg(String name){
        Map<String,Class<?>> pkgMap = (Map<String,Class<?>>)ctx.getCfg().get(Const.PKG_MAP);
        System.out.println(name+"=="+getClsStr(pkgMap.get(name)));
    }

    public static void showCfg(){
        System.out.println(MC.string.toStr(ctx.getCfg().getCfg()));
    }

    public static void showCtx(){
        System.out.println(MC.string.toStr(ctx.getCtx()));
    }


    public static String getClsStr(Class<?> c){
        if(c==null) return null;
        return c.getName();
    }


}
