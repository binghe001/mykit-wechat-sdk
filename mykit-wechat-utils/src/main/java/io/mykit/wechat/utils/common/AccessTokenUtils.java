package io.mykit.wechat.utils.common;


/**
 * 生成AccessToken工具类
 * @author liuyazhuang
 *
 */
public class AccessTokenUtils {
	
	/**
	 * 注册后登录生成Access_Token
	 * @return
	 */
	public static String generatorAccessToken(String phone,String password){
		StringBuffer sb = new StringBuffer();
		sb.append(getMD5AccessToken(phone, password));
		return sb.toString();
	}
	
	
//	/**
//	 * 生成Access_token
//	 * @param phone
//	 * @param password
//	 * @return
//	 */
//	private static String getArithmeticAccessToken(String phone,String password){
//		String sb = "";
//		 try {
//			 sb = Arithmetic.getEncString(CryptUtil.encryptBASE64(generatorString(phone, password).getBytes("UTF-8")));
//		}  catch (Exception e) {
//			sb = getMD5AccessToken(new StringBuffer().append(phone).append("getArithmeticAccessToken").toString(), password);
//		}
//		 return sb;
//	}
	
	/**
	 * 生成Access_token
	 * @param phone
	 * @param password
	 * @return
	 */
	private static String getMD5AccessToken(String phone,String password){
		return MD5Hash.md5Java(new SHA1().getDigestOfString(generatorString(phone, password).getBytes()));
	}
	
	
	/**
	 * 拼接字符串
	 * @param phone
	 * @param password
	 * @return
	 */
	private static String generatorString(String phone,String password){
		StringBuffer sb = new StringBuffer();
		sb.append(phone);
		if(!StringUtils.isEmpty(password))
			sb.append(password);
		sb.append(System.currentTimeMillis());
		sb.append(RandomUtils.getRandomString(10));
		return sb.toString();
	}
	
	/**
	 * 快速登录生成accessToken
	 * @param phone
	 * @return
	 */
	public static String generatorAccessToken(String phone){
		StringBuffer sb = new StringBuffer();
		sb.append(getMD5AccessToken(phone, null));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generatorAccessToken("18280395753"));
	}
}
