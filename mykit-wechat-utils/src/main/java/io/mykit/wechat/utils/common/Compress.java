package io.mykit.wechat.utils.common;

import java.io.File;
import java.util.List;

/**
 * 压缩
 * @author liuyazhuang
 *
 */
public class Compress {
	
	/**
	 * html的压缩文件
	 */
	public static final String TYPE_HTML = "type_html";
	/**
	 * index.html
	 */
	public static final String INDEX_HTML = "index.html";
	/**
	 * .html
	 */
	public static final String D_HTML = ".html";
	
	
    /**
     * 获取文件路径
     * @param destDir
     * @return
     */
    protected static List<String> getFilePaths(String destDir, List<String> list, String fileType, String html){
    	File file = new File(destDir);
    	if(file == null || !file.exists()) return list;
    	if(file.isDirectory()){
    		File[] listFiles = file.listFiles();
    		if(listFiles != null && listFiles.length > 0){
    			for(File f : listFiles){
    				getFilePaths(f.getAbsolutePath(), list, fileType, html);
    			}
    		}
    	}else{
    		String absolutePath = file.getAbsolutePath();
    		if (TYPE_HTML.equalsIgnoreCase(fileType) && (absolutePath.toLowerCase().contains(html))) {
    			absolutePath = absolutePath.replace("\\", "/");
				list.add(absolutePath);
			}
    	}
    	return list;
    }
}
