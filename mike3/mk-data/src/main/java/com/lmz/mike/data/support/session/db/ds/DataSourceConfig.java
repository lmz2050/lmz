package com.lmz.mike.data.support.session.db.ds;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/9 17:26
 * 4
 */
//@Configuration
public class DataSourceConfig {

//    spring.datasource.url=jdbc:mysql://localhost:3306/test1
//    spring.datasource.username=root
//    spring.datasource.password=root
//    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
//
//    spring.datasource.s.url=jdbc:mysql://localhost:3306/test2
//    spring.datasource.s.username=root
//    spring.datasource.s.password=root
//    spring.datasource.s.driver-class-name=com.mysql.jdbc.Driver

//    @Bean(name = "dataSource")
//    @Qualifier("dataSource")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "dataSourceS")
//    @Qualifier("dataSourceS")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.s")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "jdbcTemplate")
//    public JdbcTemplate primaryJdbcTemplate(
//            @Qualifier("dataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean(name = "jdbcTemplateS")
//    public JdbcTemplate jdbcTemplateS(
//            @Qualifier("dataSourceS") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

}

