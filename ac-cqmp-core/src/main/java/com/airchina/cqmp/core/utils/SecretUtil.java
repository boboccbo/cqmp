package com.airchina.cqmp.core.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * SecretUtils {3DES加密解密的工具类 }
 * 
 * @author 一鸣
 * @date 2014-11-10
 */
public class SecretUtil {
	
//	private static final ResourceBundle resources = ResourceBundle.getBundle("crypto");
	/** 秘钥 */
	private static String CryptoKey = "pz6hd93gwjxk@y$n#j328bf5";
	/** 向量 */
	private static String CryptoIV = "aopxhe78";
	/** 安全码 */
	private static String SafeCode = "";
	
//	static {
//		try {
//			CryptoKey = resources.getString("CryptoKey");
//			CryptoIV = resources.getString("CryptoIV");
//			SafeCode = resources.getString("SafeCode");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 加密方法
	 * 
	 * @param src 源数据
	 * @return
	 */
	public static String encryptMode(String src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(CryptoKey.getBytes(), "DESede");
			// 向量
			IvParameterSpec iv = new IvParameterSpec(CryptoIV.getBytes());
			// 实例化负责加密/解密的Cipher工具类
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			// 初始化为加密模式
			cipher.init(Cipher.ENCRYPT_MODE, deskey, iv);
			// 加密
			byte[] bytes = cipher.doFinal(src.getBytes("utf-8"));
			return base64Encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密函数
	 * 
	 * @param src 密文
	 * @return
	 */
	public static String decryptMode(String src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(CryptoKey.getBytes(), "DESede");
			// 向量
			IvParameterSpec iv = new IvParameterSpec(CryptoIV.getBytes());
			// 实例化负责加密/解密的Cipher工具类
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			// 初始化为解密模式
			cipher.init(Cipher.DECRYPT_MODE, deskey, iv);
			// 解密
			byte[] bytes = cipher.doFinal(base64Decode(src));
			return new String(bytes, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * MD5摘要生成
	 * 
	 * @param src
	 * @return
	 */
	public static String getMD5(String src) {
		try {
			src += SafeCode;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(src.getBytes("utf-8"));
			byte[] bytes = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			String code = sb.toString().toUpperCase();
			return code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String base64Encode(byte[] src) throws Exception {
		BASE64Encoder encode = new BASE64Encoder();
		return encode.encode(src);
	}
	
	public static byte[] base64Decode(String src) throws Exception {
		BASE64Decoder decode = new BASE64Decoder();
		return decode.decodeBuffer(src);
	}
	public static void main(String[] args){
		System.out.println(getMD5("9999"));
	}
}