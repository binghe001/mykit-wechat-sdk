package io.mykit.wechat.utils.common;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class CryptUtil {
	private static final String KEY_MD5 = "MD5";
	private static final String KEY_SHA = "SHA";
	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * 
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HMACSHA256";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (byte[]) new Base64().decode(key);
	}

	/**
	 * BASE64 加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return new String(new Base64().encode(key));
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();

	}

	private static String macKey = "cloud";

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		SecretKey secretKey = new SecretKeySpec(macKey.getBytes("UTF-8"), KEY_MAC);
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}

	/**
	 * DES 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 * 
	 * <pre>
	 * DES                  key size must be equal to 56 
	 * DESede(TripleDES)    key size must be equal to 112 or 168 
	 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
	 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
	 * RC2                  key size must be between 40 and 1024 bits 
	 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
	 * </pre>
	 */
	public static final String ALGORITHM = "DES";

	/**
	 * DES 算法转换密钥<br>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = null;
		if (ALGORITHM.equals("DES") || ALGORITHM.equals("DESede")) {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			secretKey = keyFactory.generateSecret(dks);
		} else {
			// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
			secretKey = new SecretKeySpec(key, ALGORITHM);
		}
		return secretKey;
	}

	/**
	 * DES 算法解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * DES 算法加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * DES 算法生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initKey() throws Exception {
		return initKey(null);
	}

	/**
	 * DES 算法生成密钥
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String initKey(String seed) throws Exception {
		SecureRandom secureRandom = null;
		if (seed != null) {
			secureRandom = new SecureRandom(decryptBASE64(seed));
		} else {
			secureRandom = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}


	public static String generateToken(String username, String password) throws Exception {

		String key = CryptUtil.initMacKey();
		return username + ":" + new BigInteger(CryptUtil.encryptHMAC(username.getBytes(), key)).toString(16);
	}

	public static void main(String[] args) {
		try {
			String s = "阿伯才的覆盖";
			String b = CryptUtil.encryptBASE64(s.getBytes("UTF-8"));
			System.out.println("BASE64加密后:" + b);
			byte[] c = CryptUtil.decryptBASE64(b);
			System.out.println("BASE64解密后:" + new String(c, "UTF-8"));

			c = encryptMD5(s.getBytes());
			System.out.println("MD5   加密后:" + new BigInteger(c).toString(16));

			c = encryptSHA(s.getBytes());
			System.out.println("SHA   加密后:" + new BigInteger(c).toString(16));

			String key = initMacKey();
			System.out.println("HMAC密匙:" + key);
			c = encryptHMAC(s.getBytes(), key);
			System.out.println("HMAC  加密后:" + new BigInteger(c).toString(16));

			key = initKey();
			System.out.println(ALGORITHM + "密钥:\t" + key);
			c = encrypt(s.getBytes("UTF-8"), key);
			System.out.println(ALGORITHM + "   加密后:" + new BigInteger(c).toString(16));
			c = decrypt(c, key);
			System.out.println(ALGORITHM + "   解密后:" + new String(c, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
