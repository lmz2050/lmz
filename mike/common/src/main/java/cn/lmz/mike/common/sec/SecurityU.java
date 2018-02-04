package cn.lmz.mike.common.sec;

import java.net.URL;

import cn.lmz.mike.common.base.PropU;
import cn.lmz.mike.common.base.StrU;


import com.xiaoleilu.hutool.io.resource.ResourceUtil;

public class SecurityU {

	//public static String MK="2564338";//new SimpleDateFormat("yyyy").format(new Date());
	//public static String MK=new SimpleDateFormat("yyyy").format(new Date());
	public static String MK = "20170918";
	public static final String RSA_KEY_PRI="RSA_KEY_PRI"; 
	public static final String RSA_KEY_PUB="RSA_KEY_PUB";
	public static final String KEY_PATH="/conf/keys.properties";
	
	
	public static String getRSAKey(String key){
		URL url = ResourceUtil.getResource(KEY_PATH);
		return PropU.getValue(url.getPath(), key);
	}

	public static String decryptRSA(String content){
		if (content != null&&!StrU.isBlank(content)) {
			//String RSA_KEY_PRI="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL6l1RMElN1srIA3tekAumugvxmJnsjkFbCYF69RVHn/WAs/e8NpZV0o76DSGYE4s+zKOKfWRCCq+zCerptT+QgKZF1kC+lgFmR/pAfNDiop9m5u/XGh+Nl5UGelIWOPdi2EYW3HY0XrKqr8BtxKQrgEkQwpsDgAoHSbImJMjkP7AgMBAAECgYBtv4skcou719aJzQhURI4MbTH1YorcvKhby4I+84O84yhs+TOm72JhubIQk0IJaHnzQwpQXAFGCQenGoILetCtOAEnaSjdCJHnNRY4viaV1lzNJ7G4jFlkcQkHhzsvnU9iAGL7Z5Tyk59y3Fjpd1rQ/RgjW6vXmQu17qWqQRLoCQJBAPB78laa8Qj440UU+tapc9I/qrPwkGEDM416dohCvkPQxh4cdZQG1yHwE/9Km4BvaZMiXgUXd4gth1D1rXnY6oUCQQDK8r427u4AveyqQqHpE+4NfSGLfa6SWmbqA5Px4M33B6FIOs5e08GdKAHvs6gxM7T80HbeazLGSMuaiEdP6/x/AkA3uWDUNJ75p0JydYQSTb2q0FnrzCiIckd11xS0SlLwP+ZIf+u220bqdEkN9OnHfwmFsIb8ww1Nns8mMWd7PopJAkAPuNBdhxLvAceUxQav/LhDWT9BBCKC+ffhBxft9AFgsrme09dq5zKnjxLfU+aisSPDNg8PWfScAcWsGgSUP27XAkAhagQDTju9QahsHA5otrNqbgTJB2n9z3G2tWEQMmx4o6QI/ZvJIXwPu7xTUO2tlQk5i+n9Oosb0evh1IXWiitA";
			//String RSA_KEY_PUB="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+pdUTBJTdbKyAN7XpALproL8ZiZ7I5BWwmBevUVR5/1gLP3vDaWVdKO+g0hmBOLPsyjin1kQgqvswnq6bU/kICmRdZAvpYBZkf6QHzQ4qKfZubv1xofjZeVBnpSFjj3YthGFtx2NF6yqq/AbcSkK4BJEMKbA4AKB0myJiTI5D+wIDAQAB";

			String privateKey = getRSAKey(SecurityU.RSA_KEY_PRI);
			content = Encrypt.Asymmetric.decrypt(Encrypt.Asymmetric.TYPE.RSA.toString(), privateKey, content);
		}
		return content;
	}
	
	public static String encryptRSA(String content){
		//String RSA_KEY_PUB="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+pdUTBJTdbKyAN7XpALproL8ZiZ7I5BWwmBevUVR5/1gLP3vDaWVdKO+g0hmBOLPsyjin1kQgqvswnq6bU/kICmRdZAvpYBZkf6QHzQ4qKfZubv1xofjZeVBnpSFjj3YthGFtx2NF6yqq/AbcSkK4BJEMKbA4AKB0myJiTI5D+wIDAQAB";

		String publicKey = getRSAKey(SecurityU.RSA_KEY_PUB);
		content = Encrypt.Asymmetric.encrypt(Encrypt.Asymmetric.TYPE.RSA.toString(), publicKey, content);
		
		return content;
	}
	
	public static String md5(String content){		
		return Encrypt.Digest.encrypt(Encrypt.Digest.TYPE.MD5.toString(), content);
	}

	public static String de(String v){
		String key = Encrypt.Symmetric.generateKey(Encrypt.Symmetric.TYPE.DES.toString(), SecurityU.MK);
		return Encrypt.Symmetric.decrypt(Encrypt.Symmetric.TYPE.DES.toString(),key, v);
	}
	public static String en(String v){
		String key = Encrypt.Symmetric.generateKey(Encrypt.Symmetric.TYPE.DES.toString(), SecurityU.MK);
		return Encrypt.Symmetric.encrypt(Encrypt.Symmetric.TYPE.DES.toString(),key, v);
	}
	public static String getDeValue(String v){
		if(v!=null&&v.startsWith("{D}")){
			v = de(v.substring(3));
		}
		return v;
	}
	
	public static void main(String[] args){
		String pwd="admin";
		
		String en = SecurityU.encryptRSA(pwd);
		System.out.println(en);
		String de = SecurityU.decryptRSA(en);
		
		System.out.println(de);
		
	}
}
