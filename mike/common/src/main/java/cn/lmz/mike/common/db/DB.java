package cn.lmz.mike.common.db;

import cn.lmz.mike.common.support.IFunction;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DB extends JdbcDao {

	private static final Logger log = LoggerFactory.getLogger(DB.class);

	private String dbname = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public DB(){}
	public DB(String db) throws Exception{
		setDb(db);
	}
	public DB(Connection con){
		this.connection = con;
	}
	
	public void close(Connection con,PreparedStatement stmt,ResultSet rs){
		if(rs!=null){
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			rs = null;
		}
		if(stmt!=null){
			try{stmt.close();}catch(Exception e){e.printStackTrace();}
			stmt = null;
		}
		if(con!=null){
			try{con.close();}catch(Exception e){e.printStackTrace();}
			con = null;
		}
	}
	
	public void close(){
		if(rs!=null){
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			rs = null;
		}
		if(stmt!=null){
			try{stmt.close();}catch(Exception e){e.printStackTrace();}
			stmt = null;
		}
		if(connection!=null){
			try{connection.close();}catch(Exception e){e.printStackTrace();}
			connection = null;
		}

	}
	
	public void commit() throws Exception{
		if(connection!=null){
			try{
				connection.commit();
				log.info("commit");
			}catch(Exception e){
				log.error(e.getMessage());
				throw e;
			}
		}
	}
	public void rollback() throws Exception{
		if(connection!=null){
			try{
				connection.rollback();
				log.info("rollback");
			}catch(Exception e){
				log.error(e.getMessage());
				throw e;
			}
		}
	}

	//sync from db2 to db
	public void sync(String db2,String select,String insert) throws Exception{
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		Connection con2 = null;
		try{
			con2 = getCon();
			
			//get insert
			stmt = getCon().prepareStatement(insert);
			
			//select for insert
			stmt2 = con2.prepareStatement(select);
			int ii=0;
			int jj=0;
			rs2 = stmt2.executeQuery();
			ResultSetMetaData rsmt = rs2.getMetaData();
			while(rs2.next()){
				ii++;
				jj++;
				for(int i=0;i<rsmt.getColumnCount();i++){
					//String name = rsmt.getColumnName(i+1);
					Object val = rs2.getObject(i+1);
					//log.info(name+":"+val);
					stmt.setObject(i+1, val);
				}
				stmt.executeUpdate();
				if(ii>5000){
					log.info("completed! "+jj);
					getCon().commit();
					ii=0;
				}
			}
			getCon().commit();
			log.info("completed! "+jj);
		}catch(Exception e){
			log.info(e.getMessage());
			getCon().rollback();
			throw e;
		}finally{
			close(null, stmt, rs);
			close(con2, stmt2, rs2);
		}
	}
	
	private void setParams(PreparedStatement stmt,Object... obj) throws SQLException {
		if(obj.length>0){
			for(int i=0;i<obj.length;i++){
				stmt.setObject(i+1, obj[i]);
			}
		}
	}
	
	public List<Object[]> queryHeader(String sql, Object... obj) throws Exception{
		
		log.info(" queryHeader ");
		List<Object[]> tlist = new ArrayList<Object[]>();
		try{
			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			rs  = stmt.executeQuery();
			if(rs!=null){
				ResultSetMetaData rsmt = rs.getMetaData();
				Object[] t = new Object[rsmt.getColumnCount()];
				for(int i=0;i<rsmt.getColumnCount();i++){
					t[i] = rsmt.getColumnName(i+1);
				}
				tlist.add(t);
			}
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close(null,stmt,rs);
		}
		return tlist;
	}

	public List<Object[]> queryList(String sql,Object... obj) throws Exception{
		List<Object[]> list = new ArrayList<Object[]>();

		log.info(" queryList ");
		try{
			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			rs = stmt.executeQuery();
			int c =rs.getMetaData().getColumnCount();

			while(rs.next()){
				Object[] objs = new Object[c];
				for(int k=0;k<c;k++){
					objs[k] = rs.getObject(k+1);
				}
				list.add(objs);
			}
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close(null,stmt,rs);
		}

		return list;
	}

	public ResultSet query(String sql,Object... obj) throws Exception{
		log.info(" query ");

		try{
			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			return stmt.executeQuery();
		}catch(Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public List<Map<String,Object>> queryMap(String sql,Object... obj) throws Exception{
		log.info(" queryMap ");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		try{
			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			rs = stmt.executeQuery();
			int c =rs.getMetaData().getColumnCount();

			while(rs.next()){
				Map<String,Object> map = new LinkedHashMap<String, Object>();
				for(int k=0;k<c;k++){
					String name = rs.getMetaData().getColumnName(k+1);
					Object val = rs.getObject(k+1);
					map.put(name, val);
				}
				list.add(map);
			}

		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close(null,stmt,rs);
		}

		return list;
	}
	
	public void queryFun(String sql, IFunction fun, Object... obj) throws Exception{
		try{
			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			rs = stmt.executeQuery();

			for(Map.Entry<String, Object> en:fun.getParams().entrySet()){
				String key = en.getKey();
				fun.getParams().put(key, rs);
			}

			fun.executeFun();

		}catch(Exception e){
			log.info(e.getMessage());
			throw e;
		}finally{
			close(null,stmt,rs);
		}
	}
	
	public void executeList(List<String> sqls) throws Exception{
		if(sqls!=null&&sqls.size()>0){
			Statement  stmt =null;
			try{
				
				stmt = getCon().createStatement();
				for(int i=0;i<sqls.size();i++){
					String sql = (String)sqls.get(i);
					if(sql==null||sql.trim().length()==0)continue;
					stmt.addBatch(sql);
					if((i+1)%3==0){
						stmt.executeBatch();
					}
				}
				stmt.executeBatch();
				
			}catch(Exception e){
				log.error(e.getMessage());
				throw e;
			}finally{
				if(stmt!=null){stmt.close();}
				//close(null,null,null);
			}
		}
	}
	
	public void execute(String sql,Object... obj) throws Exception{
		try{
			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			stmt.executeUpdate();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close(null,stmt,null);
		}
	}

	public Object queryObj(String sql,Object... obj) throws Exception{
		try{

			stmt = getCon().prepareStatement(sql);
			if(obj!=null&&obj.length>0) {
				setParams(stmt, obj);
			}
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getObject(1);
			}

		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close(null,stmt,rs);
		}
		return null;
	}
	
	public void executeP(String sqlp,Object[] obj) throws Exception{
		CallableStatement stmtp = null;
		try{

			int pnum = sqlp.split("\\?").length-1;
			stmtp = getCon().prepareCall(sqlp);

			if(obj!=null&&obj.length>0) {
				setParams(stmtp, obj);
			}
			if(pnum>obj.length){
				for(int i=obj.length;i<pnum;i++){
					stmtp.registerOutParameter(i+1, OracleTypes.JAVA_OBJECT);
				}
			}
			log.info(" executeP ");
			stmtp.execute();
			log.info(" executeP ");

		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close(null,null,null);
			if(stmtp!=null) stmtp.close();
		}
	}
	

	public void setDb(String db) throws Exception {
		this.dbname = db;
		if(dbname==null||dbname.trim().length()==0){
			throw new Exception("dbname is null:"+dbname);
		}
		connection = getCon(dbname);
	}

	public Connection getCon() throws Exception {
		boolean connected = DS.testConnectionWithTimeOut(connection);
		if(!connected){
			connection = getCon(dbname);
		}
		return connection;
	}
	public Connection getCon(String dbname) throws Exception {
		Connection con = DS.getConnection(dbname);
		con.setAutoCommit(false);
		return con;
	}
}
