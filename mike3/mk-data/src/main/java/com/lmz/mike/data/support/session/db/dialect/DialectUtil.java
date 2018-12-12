package com.lmz.mike.data.support.session.db.dialect;

import com.lmz.mike.common.M;
import com.lmz.mike.common.util.StrU;
import com.lmz.mike.data.support.session.db.IDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DialectUtil{

    private static Logger log = LoggerFactory.getLogger(DialectUtil.class);

    @Resource
    private DataSourceProperties dataSourceProperties;
    private IDialect dialect = null;
    private Map<String,IDialect> dmap = new HashMap<String,IDialect>();

    public IDialect getDialect(){
        String driverName = dataSourceProperties.getDriverClassName();
        if(dialect==null&&!StrU.isBlank(driverName)){
            log.info("getDialect - driverName:{}",driverName);
            if(driverName.toLowerCase().contains("oralce")){
                dialect = new Oracle();
            }else if(driverName.toLowerCase().contains("postgre")){
                dialect = new PostgreSQL();
            }else{
                dialect = new Mysql();
            }
        }
        if(dialect==null){
            dialect = new Mysql();
        }
        return dialect;
    }

}
