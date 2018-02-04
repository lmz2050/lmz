package cn.lmz.mike.common.db;

import cn.lmz.mike.common.db.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class JdbcDao {
	
	protected Connection connection = null;
	private Map<String,PreparedStatement> statementMap = new HashMap<String, PreparedStatement>();

	public JdbcDao(){}
	public JdbcDao(Connection connection){
		this.connection = connection;
	}

	/**
	 * 增/删/改
	 * (考虑Statement复用,处理完一批业务后，调用 closeAllStatements())
	 * @param SqlKeyEnum
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public  void executeUpdate(String sql,Map<String,Object> argMap) throws SQLException{
    	PreparedStatement pstmt = null;
    	try {
	    	pstmt = statementMap.containsKey(sql) ? statementMap.get(sql) : JdbcUtil.getPreparedStatement(this.connection,sql);
	    	JdbcUtil.executeUpdate(pstmt, sql, argMap);
    	} finally {
    		pstmt.clearParameters();
    	}
    }

	/**
	 * 增/删/改 数据分批
	 * @param SqlKeyEnum
	 * @param rowCount 行数
	 * @param argListMap 数据集
	 * @throws SQLException
	 */
	public void executeBatchUpdate(String sql,int rowCount,List<Map<String,Object>> argListMap) throws SQLException{
		PreparedStatement pstmt = null;
    	try {
			pstmt = statementMap.containsKey(sql) ? statementMap.get(sql) : JdbcUtil.getPreparedStatement(this.connection,sql);
			JdbcUtil.executeBatchUpdate(pstmt, sql,rowCount,argListMap);
    	} finally {
    		pstmt.clearParameters();
    	}
	}


	/**
	 * 获取唯一单元（一行一列）
	 * @param SqlKeyEnum
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public  String selectUnique(String sql,Map<String,Object> argMap) throws SQLException{
		PreparedStatement pstmt = null;
    	try {
			pstmt = statementMap.containsKey(sql) ? statementMap.get(sql) : JdbcUtil.getPreparedStatement(this.connection,sql);
			return JdbcUtil.selectUnique(pstmt, sql,argMap);
    	} finally {
    		pstmt.clearParameters();
    	}
    }

	/**
	 * 查询单行
	 * @param SqlKeyEnum
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public  Map<String, Object> selectRow(String sql,Map<String,Object> argMap) throws SQLException{
    	PreparedStatement pstmt = null;
    	try {
	    	pstmt = statementMap.containsKey(sql) ? statementMap.get(sql) : JdbcUtil.getPreparedStatement(this.connection,sql);
	    	return JdbcUtil.selectRow(pstmt, sql,argMap);
    	} finally{
    		pstmt.clearParameters();
    	}
    }

	/**
	 * 查询列表
	 * @param SqlKeyEnum
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectRows(String sql,Map<String,Object> argMap) throws SQLException{
    	PreparedStatement pstmt = null;
    	try {
	    	pstmt = statementMap.containsKey(sql) ? statementMap.get(sql) : JdbcUtil.getPreparedStatement(this.connection,sql);
	    	return JdbcUtil.selectRows(pstmt, sql,argMap);
    	} finally{
    		pstmt.clearParameters();
    	}
    }  
    
    
    /**
     * Statement
     */
	public void closeStatements(String sql) {
		PreparedStatement preparedStatement = this.statementMap.get(sql);
		if(null != preparedStatement){
			JdbcUtil.closeStatement(preparedStatement);
			this.statementMap.remove(sql);
		}
	}
	
    /**
     * Statement
     */
	public void closeAllStatements() {
		for (Iterator<Entry<String, PreparedStatement>> it = this.statementMap.entrySet().iterator();it.hasNext();) {
			Entry<String, PreparedStatement>  sta = it.next();
			JdbcUtil.closeStatement(sta.getValue());
		}
		this.statementMap.clear();
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}