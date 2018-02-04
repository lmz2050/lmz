package cn.lmz.mike.admin.business.util;

import cn.lmz.mike.admin.business.bean.Adm_ol_recv;

public class Test {

    public static void main(String[] args){
        Adm_ol_recv info = new Adm_ol_recv();

        int a = AdmU.addInteger(info.getOl_total(),null);

        System.out.println(a);
    }
}
