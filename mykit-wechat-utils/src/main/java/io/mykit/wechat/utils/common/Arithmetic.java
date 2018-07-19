package io.mykit.wechat.utils.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 对称加密与解密
 * @author liuyazhuang
 *
 */
public class Arithmetic {

	private Arithmetic(){}
	
	private static volatile Key key;
	
	/**
	 * 获取key
	 * @return
	 */
	private static Key getKey()throws Exception{
		if(key == null){
			synchronized (Arithmetic.class) {
				if(key == null){
					KeyGenerator _generator = KeyGenerator.getInstance("DES");
					_generator.init(new SecureRandom("smartlink".getBytes()));
					key = _generator.generateKey();
					_generator = null;
				}
			}
		}
		return key;
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public static String getEncString(String strMing) throws Exception{
		return byte2hex(getEncCode(strMing.getBytes()));
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String getDesString(String strMi) throws Exception{
		return new String(getDesCode(hex2byte(strMi.getBytes())));
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private static byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey());
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private static byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, getKey());
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) { // 一个字节的数，
		// 转成16进制字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) {

		try {
		  //String strEnc = Arithmetic.getEncString("18280395753");// 加密字符串,返回String的密文
			String strEnc = Arithmetic.getEncString("company=6F79078F79D77550739EF61CD0DC2A83&nonce_str=v1umzp4ryir5bocnvf4og8irlj29vokd&timeStamp=1495445584238&sign=5B0425A900D90E4D9F97E34F742DF141&client=ios&server=circle");// 加密字符串,返回String的密文
			System.out.println(strEnc);

			String strDes = Arithmetic.getDesString(strEnc);// 把String 类型的密文解密
			System.out.println(strDes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}