package io.mykit.wechat.utils.common;

import java.math.BigDecimal;

/**
 * 基本数据类型的工具类
 * @author liuyazhuang
 *
 */
public class BasicNumberUtils extends NumberUtils{
	
	
	public static boolean isEmpty(Long data){
		return data == null || data <= 0;
	}
	
	public static boolean isEmpty(Double data){
		return data == null || data <=0;
	}
	
	public static boolean isEmpty(BigDecimal data){
		return data == null || data.doubleValue()<=0;
	}
	
	public static boolean isEmpty(Integer data){
		return data == null || data <= 0;
	}
}
