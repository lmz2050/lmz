package cn.lmz.mike.data.support.session.db.sql.comon;

import cn.lmz.mike.common.M;
import java.util.Set;

public class Select extends LSqlExp{

    public final String COUNT_SQL=" SELECT COUNT(0) FROM (%s) as ctab ";
    public final String MAX_SQL = " ifnull(MAX( cast(%s as signed) ),0)  ";

    public static String MAXID = " MAX( cast(id as signed) ) ";
    public static String FUN_COUNT = " COUNT(0) ";

    public static Select instance(){
        return new Select();
    }
    public static Select fun(String name){
        return instance().addSelect(name);
    }


    public Select addSelect(String sql){
        addSql(sql);
        return this;
    }

    public Select addSelect(String... names) {
        if(!M.array.isEmpty(names)){
            for(String key:names){
                addSql(key);
            }
        }
        return this;
    }
    public Select addSelect(Set<String> sets) {
        return addSelect((String[])sets.toArray());
    }

    @Override
    public String getPreString() {
        return " ";
    }

    @Override
    public String getSplitString() {
        return " , ";
    }


}
