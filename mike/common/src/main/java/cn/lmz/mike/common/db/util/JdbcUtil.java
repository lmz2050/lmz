package cn.lmz.mike.common.db.util;

import cn.lmz.mike.common.base.StrU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * jdbcdao
 * @author qiulong.zhang
 * @param <T>
 */
public class JdbcUtil {

	private static final Logger log = LoggerFactory.getLogger(JdbcUtil.class);

	public static final Pattern pattern_fixed_args = Pattern.compile("\\#\\{([^}]+)\\}");

	/**
	 * 增/删/改
	 * (考虑Statement复用,此方法不会关闭Statement,请自行关闭)
	 * @param sqlKey
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public static void executeUpdate(PreparedStatement pstmt,String sql,Map<String,Object> argMap) throws SQLException{
		try {
			List<String> paramNames = getFixedArgs(sql);
			setParams(pstmt,paramNames,argMap);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			log.error("JdbcUtil.executeUpdate() 异常 -> sql:  【"+sql+"】  参数：【"+argMap+"】",e);
			throw e;
		}
	}

	/**
	 * 增/删/改 数据分批
	 * (考虑Statement复用,此方法不会关闭Statement,请自行关闭)
	 * @param sqlKey
	 * @param rowCount 行数
	 * @param argListMap 数据集
	 * @throws SQLException
	 */
	public static void executeBatchUpdate(PreparedStatement pstmt,String sql,int rowCount,List<Map<String,Object>> argListMap) throws SQLException{
		long time = System.currentTimeMillis();
		if(null !=argListMap && !argListMap.isEmpty()) {
			try{
				List<String> paramNames = getFixedArgs(sql);
				int i = 0;
				for (Map<String,Object> paramMap : argListMap) {
					i++;
					setParams(pstmt,paramNames,paramMap);
					pstmt.addBatch();
					if( i % rowCount == 0) {
						pstmt.executeBatch();
					}
				}
				if(i % rowCount != 0){
					pstmt.executeBatch();
				}
				log.info(" 数据分批提交 结束(条数："+argListMap.size()+",时间："+(System.currentTimeMillis() - time)+"ms)。。。");
			} catch (SQLException e) {
				log.error("JdbcUtil.executeBatchUpdate() 异常 -> sql:  【"+sql+"】",e);
				throw e;
			}
		}
	}

	/**
	 * 数据分批 (一次事务,分批事务，不提交事务)
	 * (考虑Statement复用,此方法不会关闭Statement,请自行关闭)
	 * @param connection
	 * @param preparedStatement
	 * @param sql 带 '命名参数' 占位符sql
	 * @param rowCount 行数
	 * @param argListMap 数据集
	 * @param  commitType 提交事务方式（notSubmit：不提交事务，endSubmit：一次性提交事务，batchSubmit： 分批提交事务)
	 * @throws SQLException
	 */
	public <T> void executeBatchUpdate(Connection connection,PreparedStatement pstmt,String sql,int rowCount,List<Map<String,Object>> argListMap,CommitType commitType) throws SQLException{
		commitType = (null == commitType) ? CommitType.notSubmit : commitType;
		long time = System.currentTimeMillis();
		if(null !=argListMap && !argListMap.isEmpty()) {
			try{
				List<String> paramNames = getFixedArgs(sql);
				int i = 0;
				for (Map<String,Object> paramMap : argListMap) {
					i++;
					setParams(pstmt,paramNames,paramMap);
					pstmt.addBatch();
					if( i % rowCount == 0) {
						pstmt.executeBatch();
						if(CommitType.batchSubmit.equals(commitType)){
							connection.commit();
						}
					}
				}
				if(i % rowCount != 0){
					pstmt.executeBatch();
					if(CommitType.batchSubmit.equals(commitType)){
						connection.commit();
					}
				}
				if(CommitType.endSubmit.equals(commitType)){
					connection.commit();
				}
				log.info(" 数据分批提交 结束(条数："+argListMap.size()+",时间："+(System.currentTimeMillis() - time)+"ms)。。。"+commitType.name());
			} catch (SQLException e) {
				log.error("JdbcUtil.executeBatchUpdate() 异常 -> sql:  【"+sql+"】",e);
				throw e;
			}
		}
	}
	/**
	 * 获取唯一单元（单行单列）
	 * (考虑Statement复用,此方法不会关闭Statement,请自行关闭)
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public  static String selectUnique(PreparedStatement pstmt,String sql,Map<String,Object> argMap) throws SQLException{
		ResultSet resultSet = null;
		Object obj = null;
		try {
			List<String> paramNames = getFixedArgs(sql);
			setParams(pstmt,paramNames,argMap);
			resultSet = pstmt.executeQuery();//返回查询结果
			if(resultSet.next()){
				obj = resultSet.getObject(1);
			}
			return null == obj ? null : obj.toString();
		}catch (SQLException e) {
			log.error("JdbcUtil.selectUnique() 异常 -> sql:  【"+sql+"】  参数：【"+argMap+"】",e);
			throw e;
		}finally {
			JdbcUtil.closeResultSet(resultSet);
		}
	}

	/**
	 * 查询单行
	 * (考虑Statement复用,此方法不会关闭Statement,请自行关闭)
	 * @param sqlKey
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> selectRow(PreparedStatement pstmt,String sql,Map<String,Object> argMap) throws SQLException{
		Map<String, Object> map = null;
		ResultSet resultSet = null;
		try {
			List<String> paramNames = getFixedArgs(sql);
			setParams(pstmt,paramNames,argMap);

			resultSet = pstmt.executeQuery();//返回查询结果
			ResultSetMetaData metaData = resultSet.getMetaData();
			if(resultSet.next()){
				map = getMap(metaData, resultSet);
			}
		}catch (SQLException e) {
			log.error("JdbcUtil.selectRow() 异常 -> sql:  【"+sql+"】  参数：【"+argMap+"】",e);
			throw e;
		}finally {
			JdbcUtil.closeResultSet(resultSet);
		}
		return map;
	}


	/**
	 * 查询列表
	 * (考虑Statement复用,此方法不会关闭Statement,请自行关闭)
	 * @param sqlKey
	 * @param argMap
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> selectRows(PreparedStatement pstmt,String sql,Map<String,Object> argMap) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet resultSet = null;
		try {
			List<String> paramNames = getFixedArgs(sql);
			setParams(pstmt,paramNames,argMap);
			resultSet = pstmt.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			while(resultSet.next()){
				Map<String, Object> map = getMap(metaData, resultSet);
				list.add(map);
			}
		}catch (SQLException e) {
			log.error("JdbcUtil.selectRows() 异常 -> sql:  【"+sql+"】  参数：【"+argMap+"】",e);
			throw e;
		}finally {
			JdbcUtil.closeResultSet(resultSet);
		}
		return list;
	}


	/**
	 * row 转换为map
	 * @param metaData
	 * @param rowResultSet
	 * @return
	 * @throws SQLException
	 */
	public static Map<String,Object> getMap(ResultSetMetaData metaData,ResultSet rowResultSet) throws SQLException {
		Map<String, Object>  map = new HashMap<String, Object>();
		int cols_len = metaData.getColumnCount();
		for(int i=0; i<cols_len; i++){
			String cols_name = metaData.getColumnName(i+1);
			Object cols_value = rowResultSet.getObject(cols_name);
			map.put(cols_name, cols_value);
		}
		return map;
	}

	/**
	 * 设置参数
	 * @param sql
	 * @throws SQLException
	 */
	public  static PreparedStatement getPreparedStatement(Connection connection,String sql) throws SQLException{
		sql = sql.endsWith(";") ? sql.substring(0,sql.length() - 1) : sql;
		sql = getClearArgsSql(sql);
		PreparedStatement pstmt = connection.prepareStatement(sql);
		return pstmt;
	}

	/**
	 *
	 * @param pstmt
	 * @param sql
	 * @param params
	 * @param argMap
	 * @throws SQLException
	 */
	private static void setParams(PreparedStatement pstmt,List<String> params,Map<String,Object> argMap) throws SQLException{
		StringBuffer sqlParams = new StringBuffer();
		int index = 1;
		if(params != null && !params.isEmpty() && null != argMap && !argMap.isEmpty()){
			for (Object param : params) {
				sqlParams.append(param.toString()).append("=").append(argMap.get(param)).append(",");
				pstmt.setObject(index++,argMap.get(param));
			}
		}

	}


	/**
	 * 获取in() 参数列表
	 * @param list
	 * @return
	 */
	public static String getSqlInVals(List<?> list,boolean isChar) {
		if(null ==list || list.isEmpty()){
			return "";
		}
		if(isChar) {
			return "'"+ StrU.join(list, "','")+"'";
		}else{
			return StrU.join(list, ",");
		}
	}
	/**
	 * 获取固定参数
	 * @param sql
	 * @return
	 */
	public static List<String> getFixedArgs(String sql) {
		return findArgs(pattern_fixed_args, sql);
	}
	/**
	 * 查找固定参数
	 * @param sql
	 * @param str
	 * @return
	 */
	private static List<String> findArgs(Pattern pattern,String str) {
		Matcher matcher = pattern.matcher(str);
		List<String> list = new ArrayList<String>();
		while(matcher.find()){
			list.add(matcher.group(1));
		}
		return list;
	}

	/**
	 * 替换参数，绑定动态参数
	 * @param sql
	 * @return
	 */
	public static String getClearArgsSql(String sql) {
		sql = sql.replaceAll("\\#\\{([^}]+)\\}", "?");
		return sql;
	}


	public static void closeStatement(Statement sta) {
		try {
			if (null != sta) {
				sta.close();
			}
		} catch (SQLException e) {
			log.error("Statement.close()", e);
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		try {
			if (null != resultSet) {
				resultSet.close();
			}
		} catch (SQLException e) {
			log.error("ResultSet.close()", e);
			e.printStackTrace();
		}

	}


	public enum CommitType {
		/** 一次性提交**/
		endSubmit,
		/** 分批提交 **/
		batchSubmit,
		/** 不提交 **/
		notSubmit;
	}
}