package io.mykit.wechat.utils.common;

/**
 * 密码
 * @author liuyazhuang
 *
 */
public class PasswordUtils {
	/**
	 * 密码加密
	 * @param password
	 * @return
	 */
	public static String encryptionPws(String password){
		for(int i = 0; i < 5; i++){
			password = MD5Hash.md5Java(password);
		}
		return password;
	}
	
	/** 
	 * 
	 * @author  liuyazhuang
	 * @Method: encryptionPws
	 * @Description:密码加密
	 * @param password
	 * @return String
	 * @throws TODO<异常抛出列表>
	 * @see TODO<类、类#方法、类#成员>
	 */
	public static String encryption(String password){
		try {
			password  = Arithmetic.getEncString(password);
			password = DigestUtils.md5DigestAsHex(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		password = MD5Hash.md5Java(password);
		return password;
	}
	
	public static void main(String[] args) {
//		String aa = encryptionPws("E10ADC3949BA59ABBE56E057F20F883E");
//		System.out.println(aa);
		System.out.println(PasswordUtils.encryptionPws("123456"));
	}
}
