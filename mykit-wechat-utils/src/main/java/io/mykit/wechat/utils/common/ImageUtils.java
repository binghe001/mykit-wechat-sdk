package io.mykit.wechat.utils.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取网络上的图片
 * @author liuyazhuang
 *
 */
public class ImageUtils {
	
	public static final String NATIVE_PATH = "nativePath";
	
	public static final String VISUAL_PATH = "visualPath";
	/**
	 * 下载网络上的图片
	 * @param uri:图片在网络上的地址
	 * @param nativePath:图片保存在本地的实际路径
	 * @param visualPath:图片的访问路径
	 * @return:返回完整的本地路径和访问路径的map
	 * @throws Exception
	 */
	public static Map<String, String> downloadImage(String uri, String nativePath, String visualPath) throws Exception{
		Map<String, String>  map = new HashMap<String, String>();
		HttpURLConnection conn = null;
		InputStream in = null;
		try {
			URL url = new URL(uri);
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setReadTimeout(5000);
		    if(conn.getResponseCode() == 200){
		    	in = conn.getInputStream();
		    	//获取当前时间的时间戳
		    	long time = System.currentTimeMillis();
		    	nativePath = nativePath+time+".jpg";
		    	visualPath = visualPath+time+".jpg";
		    	File file = new File(nativePath);
		    	if(!file.exists()) file.getParentFile().mkdirs();
		    	OutputStream out = new FileOutputStream(file);
		    	FileCopyUtils.copy(in, out);
		    	map.put(VISUAL_PATH, visualPath);
		    	map.put(NATIVE_PATH, nativePath);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(in != null){
				in.close();
			}
			if(conn != null){
				conn.disconnect();
			}
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception{
		Map<String, String> map = downloadImage("https://www.baidu.com/img/bdlogo.png", "d:/home/", "/home/");
		System.out.println(map.get(VISUAL_PATH)+"==="+map.get(NATIVE_PATH));
	}
}
