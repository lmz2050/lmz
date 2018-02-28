package cn.lmz.mike.common.db.util;

import cn.lmz.mike.common.io.FileU;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class JdbcSqlUtil {

	private static final Logger log = LoggerFactory.getLogger(JdbcSqlUtil.class);

	public static Map<String,String> sqlMap = new HashMap<String, String>();
	private static boolean inited = false;

	public static void init(String xmlPath){
		File path = new File(FileU.getPath(xmlPath));
		for(File file:path.listFiles()){
			if(file.getName().endsWith(".xml")){
				sqlMap.putAll(loadSqlConfig(file.getPath()));
			}
		}
	}

	public static String getSql(String sqlkey){
		if(!inited){
			init("sql");
			inited = true;
		}
		return sqlMap.get(sqlkey);
	}

	/**
	 * 解析sql
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> loadSqlConfig(String filePath) {
		Map<String,String> sqlMap = new HashMap<String,String>();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(filePath);
			if(null == fi){
				throw new RuntimeException("文件地址有误!"+filePath);
			}
			SAXReader reader = new SAXReader();
			Document doc = reader.read(fi);
			Element root = doc.getRootElement();
			String namespace = root.attributeValue("namespace");
			Iterator<Element> iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element e = iterator.next();
				sqlMap.put(namespace + "." + e.attributeValue("key"), e.getText());
			}
		} catch (Exception e) {
			throw new RuntimeException("文件解析异常!"+filePath,e);
		} finally {
			if(null !=fi){
				try {
					fi.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		return sqlMap;
	}


	public static void main(String[] args){
		String sql = JdbcSqlUtil.getSql("test.test");
		System.out.println(sqlMap.size());
		for(Map.Entry<String,String> en:sqlMap.entrySet()){
			System.out.println(en.getKey()+"=="+en.getValue());
		}
		System.out.println(sql);
	}

}