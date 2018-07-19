package io.mykit.wechat.mp.http.base;

import io.mykit.wechat.utils.common.CharsetCode;
import io.mykit.wechat.utils.common.CollectionUtils;
import io.mykit.wechat.utils.common.ObjectUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 网络链接;POST，GET请求
 * @author liuyazhuang
 *
 */
public class HttpConnectionUtils extends BaseConnectionUtils {
	
	/**
	 * SOAP12调用webservice
	 * @param methodName
	 * @param wsdlLocation
	 * @param soapRequestData
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static String soap12(String methodName, String wsdlLocation, String soapRequestData, String type) throws Exception {
    	String soapResponseData = "";
        PostMethod postMethod = new PostMethod(wsdlLocation);
        byte[] bytes = soapRequestData.getBytes("UTF-8");
        InputStream inputStream = new ByteArrayInputStream(bytes, 0, bytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, bytes.length, "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(requestEntity);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == HttpStatus.SC_OK){
        	 soapResponseData = getStringFromPostMethod(postMethod, type);
        }
        return soapResponseData;
    }
	
	/**
	 * POST方式调用webservice接口
	 * @param protocol: http/https
	 * @throws Exception
	 */
	public static String postWebService(String host, int port, String protocol, String url, NameValuePair[] nameValuePairs,/* String postKey, */String type) throws Exception{
		String result = "";
		if(ObjectUtils.isEmpty(nameValuePairs)){
			nameValuePairs = new NameValuePair[]{};
		}
		HttpClient httpClient = new HttpClient();
		httpClient.getHostConfiguration().setHost(host, port,  protocol);
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
		postMethod.setRequestBody(nameValuePairs);
		try {
			int status = httpClient.executeMethod(postMethod);
			if(status == HttpStatus.SC_OK){
				result = getStringFromPostMethod(postMethod, type);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(postMethod != null){
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		return result;
	}


	/**
	 * POST请求数据，直接将数据post到相关的连接地址
	 * @param url 请求的url地址
	 * @param body 请求体
	 * @param nameValuePairs 封装的消息GET参数或需要拼接到url链接后的参数地址
	 * @param headers 请求头信息
	 * @param type 获取数据的解析类型，包括字符串或者字节流
	 * @return 返回解析后的字符串
	 * @throws Exception
	 */
	public static String postData(String url, String body, NameValuePair[] nameValuePairs, Map<String, String> headers, String type) throws Exception{
		String result = "";
		PostMethod postMethod = new PostMethod(url);
		if(!CollectionUtils.isEmpty(headers)){
			for(Map.Entry<String, String> entry : headers.entrySet()){
				postMethod.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		if(ObjectUtils.isEmpty(nameValuePairs)){
			nameValuePairs = new NameValuePair[]{};
		}
		postMethod.setQueryString(nameValuePairs);
		//postMethod.setRequestBody(body);
        postMethod.setRequestEntity(new StringRequestEntity(body, null, CharsetCode.CHARSET_UTF));
		try {
			int status = new HttpClient().executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				result = getStringFromPostMethod(postMethod, type);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
            if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		return result;
	}

    /**
     * GET方式请求数据获取资源
     * @param url GET请求的链接地址
     * @param nameValuePairs GET请求的参数
     * @param headers GET请求头
     * @param type 接收返回数据类型：字符串或者字节流
     * @return GET请求的结果数据
     * @throws Exception
     */
	public static String getData(String url, NameValuePair[] nameValuePairs, Map<String, String> headers, String type) throws Exception{
		String result = "";
		GetMethod getMethod = new GetMethod(url);
		if(!CollectionUtils.isEmpty(headers)){
			for(Map.Entry<String, String> entry : headers.entrySet()){
				getMethod.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		if(ObjectUtils.isEmpty(nameValuePairs)){
			nameValuePairs = new NameValuePair[]{};
		}
		getMethod.setQueryString(nameValuePairs);
		try {
			int status = new HttpClient().executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				result = getStringFromGetMethod(getMethod, type);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
				getMethod = null;
			}
		}
		return result;
	}


    /**
     * 上传文件到指定的链接地址
     * @param url 上传文件的URL地址
     * @param imagePath 图片在本地(待上传的文件的存储路径)
     * @return 返回上传图片后服务器对的响应结果
     * @throws Exception
     */
	public static String sendPostContainerFile(String url,  String imagePath) throws Exception {
		String result = null;
		File file = new File(imagePath);
		if(!file.exists()) return null;
		PostMethod postMethod = new PostMethod(url);
		try {
			Part[] parts = { new FilePart("file", file) };
			postMethod.setRequestEntity(new MultipartRequestEntity(parts,
					postMethod.getParams()));
			int status = new HttpClient().executeMethod(postMethod);
			if (status == HttpStatus.SC_OK)
				result = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			throw e;
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

    /**
     * 上传本地文件到指定的服务器
     * @param url 上传文件的URL地址
     * @param imagePath 本地文件的路径
     * @param type  接收服务器响应数据的类型，包括字符串或者字节流
     * @return 返回上传图片或者文件后服务器响应的内容
     * @throws Exception
     */
	public static String sendPostContainerFile(String url, String imagePath, String type) throws Exception {
		String result = null;
		File file = new File(imagePath);
		if(!file.exists()) return null;
		PostMethod postMethod = new PostMethod(url);
		try {
			Part[] parts = { new FilePart("file", file) };
			postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
			int status = new HttpClient()
			.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK)
				result = getStringFromPostMethod(postMethod, type);
		} catch (Exception e) {
			throw e;
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}


    /**
     * post请求到服务器，将参数统一封装到parameter关键字提交到服务器
     * @param url API发地址
     * @param json 请求体
     * @param nameValuePairs 拼接到url的链接地址
     * @param headers 消息头信息
     * @param type 接收数据的方式，字符串或者字节流
     * @return
     * @throws Exception
     */
	public static String postDataWithParameter(String url, String json, NameValuePair[] nameValuePairs, Map<String, String> headers, String type) throws Exception{
		String result = "";
		PostMethod postMethod = new PostMethod(url);
		if(!CollectionUtils.isEmpty(headers)){
			for(Map.Entry<String, String> entry : headers.entrySet()){
				postMethod.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		if(ObjectUtils.isEmpty(nameValuePairs)){
			nameValuePairs = new NameValuePair[]{};
		}
		postMethod.setQueryString(nameValuePairs);
		postMethod.setRequestBody(new NameValuePair[] { new NameValuePair(
				"parameter", json) });
		try {
			int status = new HttpClient().executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				result = getStringFromPostMethod(postMethod, type);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		return result;
	}

    /**
     * 请求微信服务器地址
     * @param url 微信API
     * @param json 请求体
     * @param nameValuePairs 拼接到链接的参数
     * @param headers 消息头
     * @param type 接收数据的类型，字符串或者字节流
     * @return
     * @throws Exception
     */
	public static String postWechatData(String url, String json, NameValuePair[] nameValuePairs, Map<String, String> headers, String type) throws Exception{
		String result = "";
		PostMethod postMethod = new PostMethod(url);
        if(!CollectionUtils.isEmpty(headers)){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                postMethod.setRequestHeader(entry.getKey(), entry.getValue());
            }
        }
		if(ObjectUtils.isEmpty(nameValuePairs)){
			nameValuePairs = new NameValuePair[]{};
		}
		postMethod.setQueryString(nameValuePairs);
		try {
			postMethod.setRequestEntity(new StringRequestEntity(json, null, CharsetCode.CHARSET_UTF));
			int status = new HttpClient().executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				result = getStringFromPostMethod(postMethod, type);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		return result;
	}

    /**
     * @param url 微信API
     * @param nameValuePairs 封装的参数信息
     * @param headers  请求体信息
     * @param type 接收数据的类型：字符串或者字节流
     * @return 微信服务器响应的消息
     * @throws Exception
     */
	public static String getWechatData(String url, NameValuePair[] nameValuePairs, Map<String, String> headers, String type) throws Exception{
		String result = "";
		GetMethod getMethod = new GetMethod(url);

        if(!CollectionUtils.isEmpty(headers)){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                getMethod.setRequestHeader(entry.getKey(), entry.getValue());
            }
        }
		if(ObjectUtils.isEmpty(nameValuePairs)){
			nameValuePairs = new NameValuePair[]{};
		}
		getMethod.setQueryString(nameValuePairs);
		try {
			int status = new HttpClient().executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				result = getStringFromGetMethod(getMethod, type);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
				getMethod = null;
			}
		}
		return result;
	}

}
