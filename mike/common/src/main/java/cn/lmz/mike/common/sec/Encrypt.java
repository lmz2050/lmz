package cn.lmz.mike.common.sec;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.crypto.asymmetric.AsymmetricCrypto;
import com.xiaoleilu.hutool.crypto.asymmetric.KeyType;
import com.xiaoleilu.hutool.crypto.digest.DigestAlgorithm;
import com.xiaoleilu.hutool.crypto.digest.Digester;
import com.xiaoleilu.hutool.crypto.symmetric.SymmetricCrypto;
import com.xiaoleilu.hutool.lang.Base64;
import com.xiaoleilu.hutool.util.CharsetUtil;
import com.xiaoleilu.hutool.util.HexUtil;
import com.xiaoleilu.hutool.util.StrUtil;

public class Encrypt {

	//对称加密
	public static class Symmetric{
		public static enum TYPE {
			AES, ARCFOUR, Blowfish, DES, DESede, RC2, PBEWithMD5AndDES, PBEWithSHA1AndDESede, PBEWithSHA1AndRC2_40;
		}

		public static String generateKey(String algorithm,String key){
			return  HexUtil.encodeHexStr(SecureUtil.generateKey(algorithm,key==null?null:key.getBytes()).getEncoded());
		}

		public static String encrypt(String algorithm,String key,String content){
			//构建
			SymmetricCrypto sym = new SymmetricCrypto(algorithm, HexUtil.decodeHex(key));
			//加密为16进制表示
			String encryptHex = sym.encryptHex(content);
			return encryptHex;
		}
		
		public static String decrypt(String algorithm,String key,String encrypt){
			//构建
			SymmetricCrypto sym = new SymmetricCrypto(algorithm,HexUtil.decodeHex(key));
			//解密为字符串
			String decryptStr = sym.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
			return decryptStr;
		}
		
	}
	
	//非对称加密
	public static class Asymmetric{
		public static enum TYPE {
			RSA,DSA;
		}

		public static String[] generateKey(String algorithm){
			AsymmetricCrypto asy = new AsymmetricCrypto(algorithm);
			return new String[]{asy.getPrivateKeyBase64(),asy.getPublicKeyBase64()};
		}

		public static String encrypt(String algorithm,String key,String content){
			AsymmetricCrypto asy = new AsymmetricCrypto(algorithm,null,key);
			//公钥加密，私钥解密
			byte[] encrypt = asy.encrypt(content, KeyType.PublicKey);
			return Base64.encode(encrypt);
		}
		
		public static String decrypt(String algorithm,String key,String encrypt){
			AsymmetricCrypto asy = new AsymmetricCrypto(algorithm,key,null);

			byte[] aByte = Base64.decode(encrypt);
			//byte[] aByte = HexUtil.decodeHex(encrypt);
			byte[] decrypt = asy.decrypt(aByte, KeyType.PrivateKey);
			return StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
		}
		
	}	
	
	//摘要加密
	public static class Digest{
		public static enum TYPE {
			MD2,MD5,SHA1,SHA256,SHA384,SHA512;
		}
		
		public static String encrypt(String algorithm,String content){
			Digester dgt = new Digester(DigestAlgorithm.valueOf(algorithm));
			return dgt.digestHex(content);
		}
	}

	public static void main(String[] args){
		
		String aa = "123456";
		//String k=DE.MK;
		String k=null;
		
		/*
		String key = Encrypt.Symmetric.generateKey(Encrypt.Symmetric.TYPE.DES.toString(),k);
		
		System.out.println(key);
		
		String bb = Encrypt.Symmetric.encrypt(Encrypt.Symmetric.TYPE.DES.toString(),key, aa);
		
		System.out.println(bb);
		
		String cc = Encrypt.Symmetric.decrypt(Encrypt.Symmetric.TYPE.DES.toString(),key,bb);
		
		System.out.println(cc);
		*/
		
		
		String[] keys = Encrypt.Asymmetric.generateKey(Encrypt.Asymmetric.TYPE.RSA.toString());
		System.out.println(keys[0]);
		System.out.println(keys[1]);
		
		String bb = Encrypt.Asymmetric.encrypt(Encrypt.Asymmetric.TYPE.RSA.toString(), keys[1], aa);
		System.out.println(bb);
		
		String cc = Encrypt.Asymmetric.decrypt(Encrypt.Asymmetric.TYPE.RSA.toString(), keys[0], bb);
		System.out.println(cc);

		/*
		String bb = Encrypt.Digest.encrypt(Encrypt.Digest.TYPE.MD5.toString(), aa);
		System.out.println(bb);
		*/
		
		
		
	}
	
	
	
	
}
