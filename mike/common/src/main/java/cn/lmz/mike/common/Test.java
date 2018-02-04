package cn.lmz.mike.common;


import cn.lmz.mike.common.invoke.InvokeU;
import cn.lmz.mike.common.db.DB;

public class Test {

    public static void main(String[] args){
        testA();
    }


    public static void testA(Object... objs){

        try {
            String sql=" select 1 from dual ";
            DB dbo = new DB("{D}15f611d53c3a14d7e26a5b7e2c1796e709b8a88445719488e1ccefd852c0975b06110b8ec737c0b1ed8f9b0dd7a01700b1a3d883cacedf42");
            Object v = dbo.queryObj(sql,null);

            //DS.testConnectionWithTimeOut(dbo.getCon("{D}15f611d53c3a14d7e26a5b7e2c1796e709b8a88445719488e1ccefd852c0975b06110b8ec737c0b1ed8f9b0dd7a01700b1a3d883cacedf42"));
            System.out.println(MC.string.toStr(v));

            Object v1 = InvokeU.invokeMethod(dbo,"queryObj",new Object[]{sql,null});

            System.out.println(MC.string.toStr(v1));

            dbo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //InvokeU.invokeMethod()

    }


}
