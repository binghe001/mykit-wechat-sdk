package io.mykit.wechat.utils.common;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BigInteger相关的工具类
 * @author liuyazhuang
 *
 */
public class BigIntegerUtils {
	
	/**
	 * 从字符串中提取出数字字符串
	 * @param str
	 * @return
	 */
	public static String getNumberFromString(String str){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	/**
	 * 从字符串中提取出BigInteger数据
	 * @param str
	 * @return
	 */
	public static BigInteger getBigIntegerFromString(String str){
		String ret = getNumberFromString(str);
		if(StringUtils.isEmpty(ret)) 
			return new BigInteger("0");
		return new BigInteger(ret);
	}
	
	public static void main(String[] args) {
		String num = getNumberFromString("db157885a4e2468f89746e7b7d253d5a");
		BigInteger bigInteger = getBigIntegerFromString("db157885a4e2468f89746e7b7d253d5a");
		System.out.println(num);
		System.out.println(bigInteger);
		System.out.println("1578854246889746772535");
	}
}
