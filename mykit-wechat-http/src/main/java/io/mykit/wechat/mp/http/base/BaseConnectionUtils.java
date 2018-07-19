package io.mykit.wechat.mp.http.base;

import io.mykit.wechat.utils.common.CharsetCode;
import io.mykit.wechat.utils.common.FileCopyUtils;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/11 19:31
 * @Description: 系统基础请求工具类，提供请求后的数据解析功能，其他的工具类可以继承此工具类，后续提供链接池功能
 */
public class BaseConnectionUtils {
	//文件流类型
	public static final String TYPE_STREAM = "type_stream";
	//字符串类型
	public static final String TYPE_STRING = "type_string";
	
	/**
	 * 从PostMethod中获取字符串数据
	 * @param postMethod
	 * @param type
	 * @return
	 * @throws Exception
	 */
	protected static String getStringFromPostMethod(PostMethod postMethod, String type) throws Exception{
		String result = "";
		switch (type) {
		case TYPE_STREAM:
			InputStream in = postMethod.getResponseBodyAsStream();
			if(in != null){
				InputStreamReader reader = new InputStreamReader(in, CharsetCode.getCharset(CharsetCode.CHARSET_UTF));
				result = FileCopyUtils.copyToString(reader);
			}
			break;
		case TYPE_STRING:
			result = postMethod.getResponseBodyAsString();
			break;
		default:
			result = postMethod.getResponseBodyAsString();
			break;
		}
		return result;
	}
	
	/**
	 * 从getMethod中获取字符串
	 * @param getMethod
	 * @param type
	 * @return
	 * @throws Exception
	 */
	protected static String getStringFromGetMethod(GetMethod getMethod, String type) throws Exception{
		String result = "";
		switch (type) {
		case TYPE_STREAM:
			InputStream in = getMethod.getResponseBodyAsStream();
			if(in != null){
				InputStreamReader reader = new InputStreamReader(in, CharsetCode.getCharset(CharsetCode.CHARSET_UTF));
				result = FileCopyUtils.copyToString(reader);
			}
			break;
		case TYPE_STRING:
			result = getMethod.getResponseBodyAsString();
			break;
		default:
			result = getMethod.getResponseBodyAsString();
			break;
		}
		return result;
	}
}
