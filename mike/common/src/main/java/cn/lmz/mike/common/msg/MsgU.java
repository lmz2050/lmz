package cn.lmz.mike.common.msg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.sec.SecurityU;

public class MsgU {

	public static final SimpleDateFormat hmsFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static String host = null;
	
	public MsgU(String host){
		setHost(SecurityU.getDeValue(host));
	}
	public MsgU(){}	
	
	public void setHost(String hoststr){	
		host =SecurityU.getDeValue(hoststr);
	}

	
	public static String send(String content,String mobile) throws Exception {
		String result = "";
		URL url;
		HttpURLConnection conn = null;
		try {
			content = "："+content;
			if(content!=null&&content.length()>150){
				content = content.substring(0,150);
			}
			content = content+hmsFormat.format(new Date());
			
			content = URLEncoder.encode(content, "UTF-8");
			url = new URL(host + "&message=" + content + "&phone="+ mobile );
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
			O.info("发送短信消息结果：" + result+"-"+mobile);
		} catch (Exception e) {
			O.error("发送短信失败！"+content,e);
			throw e;
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return result;
	}
	
}
