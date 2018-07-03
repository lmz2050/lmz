package cn.lmz.mike.data.support.session.db.sql.comon;

import cn.lmz.mike.common.M;

public class OrderBy extends LSqlExp{

    public static String DESC="desc";
    public static String ASC="asc";

    public static OrderBy instance(){
        return new OrderBy();
    }
    public static OrderBy instance(String sql){
        return instance().addOrderBy(sql);
    }
    public static OrderBy defaultOrderBy(){
        return OrderBy.instance("id desc");
    }

    public OrderBy addOrderBy(String sql){
        addSql(sql);
        return this;
    }
    public OrderBy addDesc(String... names){
        if(M.isEmpty(names)){
            return this;
        }
        for(String name:names){
            addSql(name).append(" ").append(DESC);
        }
        return this;
    }
    public OrderBy addAsc(String... names){
        if(M.isEmpty(names)){
            return this;
        }
        for(String name:names){
            addSql(name).append(" ").append(ASC);
        }
        return this;
    }


    @Override
    public String getPreString() {
        return " order by ";
    }

    @Override
    public String getSplitString() {
        return " , ";
    }
}
