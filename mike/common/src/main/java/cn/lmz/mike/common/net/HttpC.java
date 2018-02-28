package cn.lmz.mike.common.net;

import cn.lmz.mike.common.base.MapU;
import cn.lmz.mike.common.log.O;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpC {

    private static final Logger log = LoggerFactory.getLogger(HttpC.class);

    public static void get(String url, Map<String, String> params){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            String ps = "";
            for (String pKey : params.keySet()) {
                if(!"".equals(ps)){
                    ps = ps + "&";
                }
                ps = pKey+"="+params.get(pKey);
            }
            if(!"".equals(ps)){
                url = url + "?" + ps;
            }
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void post(String url, Map<String, String> params){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            List<NameValuePair> ps = new ArrayList<NameValuePair>();
            for (String pKey : params.keySet()) {
                ps.add(new BasicNameValuePair(pKey, params.get(pKey)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(ps));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpPost!=null){
                    httpPost.releaseConnection();
                }
                if(httpClient!=null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String sendPostJson(String urlStr,String data) throws Exception {
        return sendUrl(urlStr, data, HttpC.RequestMethodEnum.POST, 60000, 60000, HttpC.CharsetNameEnum.UTF8, HttpC.CharsetNameEnum.UTF8, HttpC.ContentTypeEnum.json);
    }

    public static String sendPostJson(String urlStr,String data,Integer connectTimeout,Integer readTimeout) throws Exception {
        return sendUrl(urlStr, data, HttpC.RequestMethodEnum.POST, connectTimeout, readTimeout, HttpC.CharsetNameEnum.UTF8, HttpC.CharsetNameEnum.UTF8, HttpC.ContentTypeEnum.json);
    }

    public static String sendUrlPost(String urlStr, String data, HttpC.CharsetNameEnum requestCharsetEnum, HttpC.CharsetNameEnum responseCharsetEnum, HttpC.ContentTypeEnum contentTypeEnum) throws Exception {
        return sendUrl(urlStr, data, HttpC.RequestMethodEnum.POST, 60000, 60000, requestCharsetEnum, responseCharsetEnum,contentTypeEnum);
    }

    public static String sendUrl(String urlStr, String data, HttpC.RequestMethodEnum requestMethodEnum, Integer connectTimeout, Integer readTimeout, HttpC.CharsetNameEnum requestCharsetEnum, HttpC.CharsetNameEnum responseCharsetEnum, HttpC.ContentTypeEnum contentTypeEnum) throws Exception {
        long time = System.currentTimeMillis();
        StringBuffer result = new StringBuffer("");
        HttpURLConnection conn = null;
        OutputStream outputStream = null;
        BufferedReader reader = null;
        try {
            log.info(" ({})-> url:{}",time,urlStr);
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(null == connectTimeout ? 60000 : connectTimeout);
            conn.setReadTimeout(null == readTimeout ? 60000 : readTimeout);
            requestMethodEnum = (null == requestMethodEnum) ? HttpC.RequestMethodEnum.POST : requestMethodEnum;
            conn.setRequestMethod(requestMethodEnum.getVal());
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type",null == contentTypeEnum ? HttpC.ContentTypeEnum.json.getVal() : contentTypeEnum.getVal());
            outputStream = conn.getOutputStream();
            requestCharsetEnum = (null == requestCharsetEnum)  ? HttpC.CharsetNameEnum.UTF8 : requestCharsetEnum;
            outputStream.write(data.toString().getBytes(requestCharsetEnum.getVal()));
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = conn.getInputStream();
            responseCharsetEnum = (null == responseCharsetEnum)  ? HttpC.CharsetNameEnum.UTF8 : responseCharsetEnum;
            reader = new BufferedReader(new InputStreamReader(inputStream,responseCharsetEnum.getVal()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            inputStream.close();
            reader.close();
            log.info("请求结束({})-> url:{} 内容长度：{} 耗时：",time,urlStr,result.length(),(System.currentTimeMillis() - time));
        } catch (Throwable e) {
            log.error(" url: "+urlStr+" 请求异常! 参数->"+data, e);
            throw new Exception(e);
        } finally {
            if(null != conn) {
                conn.disconnect();
            }
        }
        return result.toString();
    }

    public enum RequestMethodEnum {
        GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

        private RequestMethodEnum(String val) {
            this.val = val;
        }

        private String val;

        public static HttpC.RequestMethodEnum getEnum(String val) {
            for (HttpC.RequestMethodEnum bean : HttpC.RequestMethodEnum.values()) {
                if (bean.getVal().equals(val)) {
                    return bean;
                }
            }
            return null;
        }

        public String getVal() {
            return val;
        }

    }

    public enum CharsetNameEnum {

        UTF8("UTF-8"), GBK("GBK"), ISO88591("ISO-8859-1"), GB2312("gb2312");

        private CharsetNameEnum(String val) {
            this.val = val;
        }

        private String val;

        public static HttpC.CharsetNameEnum getEnum(String val) {
            for (HttpC.CharsetNameEnum bean : HttpC.CharsetNameEnum.values()) {
                if (bean.getVal().equals(val)) {
                    return bean;
                }
            }
            return null;
        }

        public String getVal() {
            return val;
        }
    }

    public enum ContentTypeEnum {

        json("application/json"), xml("text/xml");

        private ContentTypeEnum(String val) {
            this.val = val;
        }

        private String val;

        public static HttpC.ContentTypeEnum getEnum(String val) {
            for (HttpC.ContentTypeEnum bean : HttpC.ContentTypeEnum.values()) {
                if (bean.getVal().equals(val)) {
                    return bean;
                }
            }
            return null;
        }

        public String getVal() {
            return val;
        }
    }

    public static void main(String[] args){

        String url="http://120.25.63.8:9000/admin/api/activate.action";
       Map params = MapU.getMap("oem_type","MTC","deverce_name","bbb2","wired_mac_name","23:21:6c:9b","wireless_mac_name","5d:2a:6c:9b","function_type","006");
       post(url,params);

        //String url="http://120.25.63.8:9000/admin/api/checkUpdate.action";
        //Map params = MapU.getMap("oem_type","aaa","deverce_name","bbb2","version","0.0.0.0");
        //post(url,params);

        //String url="http://120.25.63.8:9000/admin/api/erorrLog.action";
        //Map params = MapU.getMap("oem_type","aaa","deverce_name","bbb2","mac_name","cc2","version","0.0.0.0","log","asfdsdfsdfsdfsdf");
        //post(url,params);

        //String url="http://120.25.63.8:9000/admin/api/receiveOnlineInfo.action";
        //Map params = MapU.getMap("oem_type","aaa","deverce_name","bbb2","version","0.0.0.0","mac_name","cc2","login_time","2018/01/20 18:15","online_times","10","gn_1_times","5","gn_2_times","3");
        //post(url,params);

        //String url="http://120.25.63.8:9000/admin/api/sendOnlineInfo.action";
        //Map params = MapU.getMap("oem_type","aaa","deverce_name","bbb2","system_version","0.0.0.0","system_name","cc2","app_version","0.1.0.0","login_time","2018/01/20 14:15","online_times","10");
        //post(url,params);

        //String url="http://120.25.63.8:9000/admin/api/basicInfo.action";
        //Map params = MapU.getMap("phone","phonephone","name","namename","sex","sexsex","wechat","wechatwechat","mail","mmmmm@qq.com","job","jjjj");
        //post(url,params);

    }

}
