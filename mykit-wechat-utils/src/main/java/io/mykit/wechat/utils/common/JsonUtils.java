package io.mykit.wechat.utils.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 自定义Json数据相关工具类
 * @author liuyazhuang
 *
 */
public final class JsonUtils {
	
	/**
	 * 将JsonObject转化为map
	 * @param s
	 * @return
	 */
	public static Map<String, Object> parserToMap(JSONObject json){
		Map<String, Object> map = new HashMap<String,Object>();
		Iterator<String> keys= json.keySet().iterator();
		while(keys.hasNext()){
			String key=(String) keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				map.put(key, parserToMap(value));
			}else{
				map.put(key, value);
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("aaa", "aaa");
		jsonObject.put("bbb", "bbb");
		jsonObject.put("ccc", "ccc");
		System.out.println(jsonObject);
		Map<String, Object> map = parserToMap(jsonObject);
		System.out.println(map);
	}
	 /**
	  * 将String转化为map
	 * @param s
	 * @return
	 */
	public static Map<String, Object> parserToMap(String s){
		Map<String, Object> map = new HashMap<String,Object>();
		JSONObject json = JSONObject.parseObject(s);
		Iterator<String> keys= json.keySet().iterator();
		while(keys.hasNext()){
			String key=(String) keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				map.put(key, parserToMap(value));
			}else{
				map.put(key, value);
			}
		}
		return map;
	}
	
	/**
	 * 判断空
	 * @param jsonObject
	 * @return
	 */
	public static boolean isEmpty(JSONObject jsonObject){
		if(jsonObject == null || jsonObject.isEmpty()) 
			return true;
		Set<String> keySet = jsonObject.keySet();
		for(String key : keySet){
			String value = jsonObject.getString(key);
			if(StringUtils.isEmpty(value))
				return true;
		}
		return false;
	}
	
	/**
	 * 将一个jsonObject转化为map
	 * @param jsonObject
	 * @return
	 */
	public static Map<String, String> parseJsonObjectToMap(JSONObject jsonObject){
		if(jsonObject == null || jsonObject.size() == 0) return null;
		Map<String, String> map = new HashMap<String, String>();
		Set<String> keys = jsonObject.keySet();
		for(String key : keys){
			String value = jsonObject.getString(key);
			map.put(key, value);
		}
		return map;
	} 
	

	/**
	 * 转化jsonObject中保存的字符串编码
	 * @param jsonObject
	 * @param fromCharset
	 * @param toCharset
	 * @return
	 */
	public static JSONObject changeCode(JSONObject jsonObject, String fromCharset, String toCharset){
		if(jsonObject == null || jsonObject.size() == 0) return null;
		try {
			Set<String> keys = jsonObject.keySet();
			for(String key : keys){
				Object valueObj = jsonObject.get(key);
				if(valueObj instanceof String){
					String value = (String) valueObj;
					value = new String(value.getBytes(fromCharset),toCharset);
					jsonObject.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * 判断是否为空
	 * @param jsonArray
	 * @return
	 */
	public static boolean isEmpty(JSONArray jsonArray){
		return (jsonArray == null || jsonArray.size() == 0);
	}
	
	/**
	 * 判断两个Json长度是否相等
	 * @param firstArray
	 * @param seondArray
	 * @return
	 */
	public static boolean isEqualLength(JSONArray firstArray,JSONArray seondArray){
		return firstArray.size() == seondArray.size();
	}
	
	/**
	 * 将字符串格式化为jsonArray格式
	 * @param data
	 * @return
	 */
	public static String transformStringToJsonArrayFormat(String data){
		if(!data.contains("[") && !data.contains("]")) data = "["+data+"]";
		if(!data.contains("[") && data.contains("]")) data="["+data;
		if(data.contains("[") && !data.contains("]")) data = data+"]";
		return data;
	}
	
	/**json字符串解析字段value
	 * @param dataJSON jsonData
	 * @param filedName 字段名
	 * @return 字段对应值
	 */
	public static String getJSONFieldData(String dataJSON,String filedName){
		try {
			dataJSON=URLDecoder.decode(dataJSON,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSONObject.parseObject(dataJSON).get(filedName).toString();
	}
}
