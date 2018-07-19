package io.mykit.wechat.utils.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
	
	public static String getGetterName(String fieldName) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("get").append(fieldName.substring(0, 1).toUpperCase())  
                .append(fieldName.substring(1));  
        return sb.toString();
    }
	
	public static Field getModelField(Class clazz,Class annotation){
		for(Field f : clazz.getDeclaredFields()){
			if(f.getAnnotation(annotation)!=null)
				return f;
		}
		return null;
	}
	
	public static String getPrimaryKeyName(Class clazz,Class annotation){
		String name = getPrimaryKeyNameByClass(clazz, annotation);
		if (StringUtils.isEmpty(name)) {
			while (clazz.getSuperclass() != null) {
				//父级class
				clazz = clazz.getSuperclass();
				name =  getPrimaryKeyNameByClass(clazz, annotation);
				if(!StringUtils.isEmpty(name)){
					break;
				}
			}
		}
		
		return name;
	}
	
	private static String getPrimaryKeyNameByClass(Class clazz,Class annotation) {
		for(Field f : clazz.getDeclaredFields()){
			if(f.getAnnotation(annotation) != null)
				return f.getName();
		}
		Method[] methods = clazz.getDeclaredMethods();
		if(methods != null && methods.length > 0){
			for(Method m : methods){
				if(m.getAnnotation(annotation) != null){
					return getFieldName(m.getName());
				}
			}
		}
		return null;
	}
	
	
	private static String getFieldName(String gettterName){
		String fieldName = gettterName.substring("get".length());
		StringBuilder sb = new StringBuilder();
		sb.append(fieldName.substring(0, 1).toLowerCase()).append(fieldName.substring(1));
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		String str = "getId";
		System.out.println(str.substring("get".length()).toLowerCase());
	}
	public static String getSetterName(String fieldName) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("set").append(fieldName.substring(0, 1).toUpperCase())  
                .append(fieldName.substring(1));  
        return sb.toString();
    }
	
	/** 
     * java反射bean的get方法 
     *  
     * @param claz 
     * @param fieldName属性名 
     * @return 
     */  
	public static Method getGetter(Class claz, String fieldName) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("get").append(fieldName.substring(0, 1).toUpperCase())  
                .append(fieldName.substring(1));  
        try {  
            Class[] types = new Class[] {};  
            return claz.getMethod(sb.toString(), types);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
	 
	 /** 
     * java反射bean的set方法 
     *  
     * @param claz 
     * @param fieldName属性名 
     * @return 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public static Method getSetter(Class claz, String fieldName) {  
        try {  
            Class[] parameterTypes = new Class[1];  
            Field field = claz.getDeclaredField(fieldName);  
            parameterTypes[0] = field.getType();// 返回参数类型  
            StringBuilder sb = new StringBuilder();  
            sb.append("set").append(fieldName.substring(0, 1).toUpperCase())  
                    .append(fieldName.substring(1));  
            Method method = claz.getMethod(sb.toString(), parameterTypes);  
            return method;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}
