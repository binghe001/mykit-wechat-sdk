package io.mykit.wechat.utils.common;

import com.alibaba.fastjson.JSONArray;

/**
 * 数组工具类
 * @author liuyazhuang
 *
 */
public class ArrayUtils extends ObjectUtils {
	
	/**
	 * 将JsonArray类型的数据转化为Long[]类型
	 * @param jsonArray
	 * @return
	 */
	public static Long[] getLongArr(JSONArray jsonArray){
		Long[] ids = new Long[jsonArray.size()];
		for(int i = 0; i < jsonArray.size(); i++){
			ids[i] = Long.parseLong(jsonArray.getString(i));
		}
		return ids;
	}
	/**
	 * 将String类型数组转化为Long类型
	 * @param arr
	 * @return
	 */
	public static Long[] getLongArr(String[] arr){
		Long[] ids = new Long[arr.length];
		for(int i = 0; i < arr.length; i++){
			ids[i] = Long.parseLong(arr[i]);
		}
		return ids;
	}
	
	/**
	 * 将String类型数组转化为Integer类型
	 * @param arr
	 * @return
	 */
	public static Integer[] getIntegerArr(String[] arr){
		Integer[] ids = new Integer[arr.length];
		for(int i = 0; i < arr.length; i++){
			ids[i] = Integer.parseInt(arr[i]);
		}
		return ids;
	}
}
