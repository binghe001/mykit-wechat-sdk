package io.mykit.wechat.utils.map;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用反射对Java对象和Map相互转化
 * @author liuyazhuang
 *
 */
public class ReflectMap {
	
	/**
	 * 将Map转化为Object返回泛型类型
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToT(Map<String, Object> map, Class<T> beanClass){
		Object obj = mapToObject(map, beanClass);
		return obj == null ? null : ((T)obj);
	}
	
	 /**
	  * 将Map转化为Object
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass){    
	    if (map == null)  
	        return null;    
	    Object obj = null;
		try {
			obj = beanClass.newInstance();  
			Field[] fields = obj.getClass().getDeclaredFields();   
			for (Field field : fields) {    
			    int mod = field.getModifiers();    
			    if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
			        continue;    
			    }    
			    field.setAccessible(true);    
			    field.set(obj, map.get(field.getName()));   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}   
	    return obj;    
	}    
	
	/**
	 * 将Object转化为Map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object obj) {    
	    if(obj == null){    
	        return null;    
	    }   
	    Map<String, Object> map = new HashMap<String, Object>();    
	    try {
	    	Field[] declaredFields = obj.getClass().getDeclaredFields();    
		    for (Field field : declaredFields) {    
		        field.setAccessible(true);  
		        map.put(field.getName(), field.get(obj));  
		    }    
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return map;  
	}   
	
	/**
	 * 将Object转化为Map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> beanToMap(Object obj) {    
		if(obj == null){    
			return null;    
		}   
		Map<String, Object> map = new HashMap<String, Object>();    
		try {
			Field[] declaredFields = obj.getClass().getDeclaredFields();    
			for (Field field : declaredFields) {    
				field.setAccessible(true);  
				map.put(field.getName(), field.get(obj));  
			}
			if(map.containsKey("serialVersionUID")){
				map.remove("serialVersionUID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;  
	}   
}

