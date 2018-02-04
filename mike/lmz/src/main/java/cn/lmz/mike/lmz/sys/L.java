package cn.lmz.mike.lmz.sys;


import cn.lmz.mike.common.MC;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.util.SysU;

public class L  extends MC {

    public L(){};
    public L(Context ctx){
        sys.setCtx(ctx);
    }

    public static final class sys extends SysU {};

    public static void alert(Object v){
        System.out.println(toStr(v));
    }

}
