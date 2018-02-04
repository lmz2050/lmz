package cn.lmz.mike.common.db;

import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.sec.SecurityU;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class DS implements DataSource{

    /** JDBC 驱动 MySQL */
    public final static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    /** JDBC 驱动 Oracle */
    public final static String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    /** JDBC 驱动 PostgreSQL */
    public final static String DRIVER_POSTGRESQL = "org.postgresql.Driver";
    /** JDBC 驱动 SQLLite3 */
    public final static String DRIVER_SQLLITE3 = "org.sqlite.JDBC";
    /** JDBC 驱动 Hive */
    public final static String DRIVER_HIVE = "org.apache.hadoop.hive.jdbc.HiveDriver";
    /** JDBC 驱动 Hive */
    public final static String DRIVER_HIVE2 = "org.apache.hive.jdbc.HiveDriver";

    public final static String TEST_SQL=" SELECT 0 FROM DUAL ";

    private String url;
    private String user;
    private String pass;
    private String driver;

    public static Connection getConnection(String dbstr) throws Exception {
        dbstr = SecurityU.getDeValue(dbstr);
        String[] dbs = dbstr.split("\\|");
        return getConnection(dbs[0],dbs[1],dbs[2]);
    }

    public static Connection getConnection(String url,String username, String password) throws SQLException {
        String driver = identifyDriver(url);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Get jdbc driver from [{"+url+"}] error!",e);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        if(testConnection(con)){
            O.info("connected system:"+url+"---"+username);
        }else{
            O.info("connected system:"+url+"---"+username);
            throw new SQLException("连接失败!"+url+"---"+username);
        }
        return con;
    }

    public static boolean testConnection(Connection con) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement(TEST_SQL);

            rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }
        }finally {
            if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
            if(stmt!=null){try{stmt.close();}catch(Exception e){e.printStackTrace();}}
            rs=null;
            stmt=null;
        }
        return false;
    }

    public static boolean testConnectionWithTimeOut(final Connection connection) {
        boolean bdStatus = false;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = (FutureTask<String>) executor.submit(
        new Callable<String>() {//使用Callable接口作为构造参数
            public String call() throws SQLException {
                Statement statement = null;
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(TEST_SQL);
                    return "true";
                }finally {
                    if(statement!=null){ try{statement.close();}catch(Exception e){e.printStackTrace();}}
                }
            }});

        try {

            String obj = future.get(1000 * 10, TimeUnit.MILLISECONDS);
            bdStatus = Boolean.parseBoolean(obj);
            System.out.println("the return value from call is :" + obj);
        } catch (TimeoutException ex) {
            System.out.println("====================task time out===============");
            ex.printStackTrace();
            bdStatus = false;
        } catch (Exception e) {
            System.out.println("failed to handle.");
            e.printStackTrace();
            bdStatus = false;
        }
        // close thread pool
        executor.shutdown();

        return bdStatus;
    }


    public void init(String url, String user, String pass) throws Exception {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = identifyDriver(url);
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            throw new Exception("Get jdbc driver from [{"+url+"}] error!",e);
        }
    }
    public void init(String dbstr) throws Exception {
        dbstr = SecurityU.getDeValue(dbstr);
        String[] dbs = dbstr.split("\\|");
        init(dbs[0],dbs[1],dbs[2]);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(this.user,this.pass);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection(this.url,username,password);
    }

    public void close(){
        this.url = null;
        this.user = null;
        this.pass = null;
        this.driver = null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return DriverManager.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        DriverManager.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        DriverManager.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return DriverManager.getLoginTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("Can't support unwrap method!");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException("Can't support isWrapperFor method!");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException("DataSource can't support getParentLogger method!");
    }

    public static String identifyDriver(String nameContainsProductInfo) {
        if(StrU.isBlank(nameContainsProductInfo)) {
            return null;
        }
        nameContainsProductInfo = nameContainsProductInfo.toLowerCase();

        String driver = null;
        if(nameContainsProductInfo.contains("mysql")) {
            driver = DS.DRIVER_MYSQL;
        }else if(nameContainsProductInfo.contains("oracle")) {
            driver = DS.DRIVER_ORACLE;
        }else if(nameContainsProductInfo.contains("postgresql")) {
            driver = DS.DRIVER_POSTGRESQL;
        }else if(nameContainsProductInfo.contains("sqlite")) {
            driver = DS.DRIVER_SQLLITE3;
        }else if(nameContainsProductInfo.contains("hive")) {
            driver = DS.DRIVER_HIVE;
        }else if(nameContainsProductInfo.contains("hive2")) {
            driver = DS.DRIVER_HIVE2;
        }

        return driver;
    }
}
