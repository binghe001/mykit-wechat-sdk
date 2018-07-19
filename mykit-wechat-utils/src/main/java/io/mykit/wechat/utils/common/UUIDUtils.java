package io.mykit.wechat.utils.common;

import java.util.UUID;

/**
 * UUID工具类
 * @author liuyazhuang
 *
 */
public class UUIDUtils {
	
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}
}
