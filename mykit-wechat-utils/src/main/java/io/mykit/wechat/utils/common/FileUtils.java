package io.mykit.wechat.utils.common;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class FileUtils {
    private static final char EXTENSION_SEPARATOR = '.';  
    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
	/**  
     * 获取给定路径的文件名
     */  
    public static String getFilename(String path) {  
        if (path == null) {  
            return null;  
        }  
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);  
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);  
    }  
  
    /**  
     *获取给定路径文件的后缀名
     */  
    public static String getFilenameExtension(String path) {  
        if (path == null) {  
            return null;  
        }  
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);  
        if (extIndex == -1) {  
            return null;  
        }  
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);  
        if (folderIndex > extIndex) {  
            return null;  
        }  
        return path.substring(extIndex + 1);  
    }  
  
	/**
	 * 去除给定路径的文件后缀名
	 * e.g. "mypath/myfile.txt" -> "mypath/myfile".
	 * @param path the file path (may be {@code null})
	 * @return the path with stripped filename extension,
	 * or {@code null} if none
	 */ 
    public static String stripFilenameExtension(String path) {  
        if (path == null) {  
            return null;  
        }
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);  
        if (extIndex == -1) {  
            return path;  
        }
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);  
        if (folderIndex > extIndex) {  
            return path;
        }
        return path.substring(0, extIndex);  
    }
	/**
	 * Apply the given relative path to the given path,
	 * assuming standard Java folder separation (i.e. "/" separators).
	 * @param path the path to start from (usually a full file path)
	 * @param relativePath the relative path to apply
	 * (relative to the full file path above)
	 * @return the full file path that results from applying the relative path
	 */ 
	public static String applyRelativePath(String path, String relativePath) {  
	    int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);  
	    if (separatorIndex != -1) {//如果有文件分隔符  
           //获得0到最后一个分隔符之前的子字符�? 
	        String newPath = path.substring(0, separatorIndex);  
	        //如果relativePath不是以文件分隔符结尾
	        if (!relativePath.startsWith(FOLDER_SEPARATOR)) {  
	            newPath += FOLDER_SEPARATOR;
	        }
	        return newPath + relativePath;  
	    }  
	    else {//如果没有，就返回relativePath  
	        return relativePath;  
	    }  
	}
	
	/**  
	 * Normalize the path by suppressing sequences like "path/.." and  
	 * inner simple dots.  
	 * <p>The result is convenient for path comparison. For other uses,  
	 * notice that Windows separators ("\") are replaced by simple slashes.  
	 * @param path the original path  
	 * @return the normalized path  
	 */  
	public static String cleanPath(String path) {  
        //边界处理  
	    if (path == null) {  
	        return null;  
	    }  
        //�?地体pathToUse的\\  
	    String pathToUse = StringUtils.replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);  
	
	    // Strip prefix from path to analyze, to not treat it as part of the  
	    // first path element. This is necessary to correctly parse paths like  
	    // "file:core/../core/io/Resource.class", where the ".." should just  
	    // strip the first "core" directory while keeping the "file:" prefix.  
	            //找到：的位置  
	    int prefixIndex = pathToUse.indexOf(":");  
	    String prefix = "";  
	            //如果：不存在  
	    if (prefixIndex != -1) {  
	                    //前缀是pathToUse中从0到prefixIndex的字符，包括�? 
	        prefix = pathToUse.substring(0, prefixIndex + 1);  
	                    //获得冒号之后的所有字符（串）  
	        pathToUse = pathToUse.substring(prefixIndex + 1);  
	    }  
	    if (pathToUse.startsWith(FOLDER_SEPARATOR)) {//如果pathToUse是以/�?��  
	                    //把prefix +/  
	        prefix = prefix + FOLDER_SEPARATOR;  
	                    //过滤掉开头的/  
	        pathToUse = pathToUse.substring(1);  
	    }  
	
	    String[] pathArray = StringUtils.delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);  
	    List<String> pathElements = new LinkedList<String>();  
	    int tops = 0;  
	
	    for (int i = pathArray.length - 1; i >= 0; i--) {  
	        String element = pathArray[i];  
	        if (CURRENT_PATH.equals(element)) {  
	            // Points to current directory - drop it.  
	        }  
	        else if (TOP_PATH.equals(element)) {  
	            // Registering top path found.  
	            tops++;  
	        }  
	        else {  
	            if (tops > 0) {  
	                // Merging path element with element corresponding to top path.  
	                tops--;  
	            }  
	            else {  
	                // Normal path element found.  
	                pathElements.add(0, element);  
	            }  
	        }  
	    }  
	    // Remaining top paths need to be retained.  
	    for (int i = 0; i < tops; i++) {  
	        pathElements.add(0, TOP_PATH);  
	    }  
	    return prefix + StringUtils.collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);  
	}
	
	/**
	 * Delete the supplied {@link File} - for directories,
	 * recursively delete any nested directories or files as well.
	 * @param root the root {@code File} to delete
	 * @return {@code true} if the {@code File} was deleted,
	 * otherwise {@code false}
	 */
	public static boolean deleteRecursively(File root) {
		if (root != null && root.exists()) {
			if (root.isDirectory()) {
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						deleteRecursively(child);
					}
				}
			}
			return root.delete();
		}
		return false;
	}

	/**
	 * Delete the supplied {@link File} - for directories,
	 * recursively delete any nested directories or files as well.
	 * @param root the root {@code File} to delete
	 * @return {@code true} if the {@code File} was deleted,
	 * otherwise {@code false}
	 */
	public static boolean deleteRecursively(String rootPath) {
		if(StringUtils.hasLength(rootPath)){
			return deleteRecursively(new File(rootPath));
		}
		return false;
	}
	
	/**
	 * Recursively copy the contents of the {@code src} file/directory
	 * to the {@code dest} file/directory.
	 * @param src the source directory
	 * @param dest the destination directory
	 * @throws IOException in the case of I/O errors
	 */
	public static void copyRecursively(File src, File dest) throws IOException {
		Assert.isTrue(src != null && (src.isDirectory() || src.isFile()), "Source File must denote a directory or file");
		Assert.notNull(dest, "Destination File must not be null");
		doCopyRecursively(src, dest);
	}

	/**
	 * Actually copy the contents of the {@code src} file/directory
	 * to the {@code dest} file/directory.
	 * @param src the source directory
	 * @param dest the destination directory
	 * @throws IOException in the case of I/O errors
	 */
	private static void doCopyRecursively(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			dest.mkdir();
			File[] entries = src.listFiles();
			if (entries == null) {
				throw new IOException("Could not list files in directory: " + src);
			}
			for (File entry : entries) {
				doCopyRecursively(entry, new File(dest, entry.getName()));
			}
		}
		else if (src.isFile()) {
			try {
				dest.createNewFile();
			}
			catch (IOException ex) {
				IOException ioex = new IOException("Failed to create file: " + dest);
				ioex.initCause(ex);
				throw ioex;
			}
			FileCopyUtils.copy(src, dest);
		}
		else {
			// Special File handle: neither a file not a directory.
			// Simply skip it when contained in nested directory...
		}
	}
	
	public static void WriteFile(String outPath,InputStream in)throws IOException{
		File out = new File(outPath);
		if (out.isFile()) {
			try {
				out.createNewFile();
			}
			catch (IOException ex) {
				IOException ioex = new IOException("Failed to create file: " + outPath);
				ioex.initCause(ex);
				throw ioex;
			}
			FileCopyUtils.copy(in, new BufferedOutputStream(new FileOutputStream(out)));
		}
	}
	
	public static boolean DeleteFile(String path){
		File file = new File(path);
		if (file.exists() && file.isFile()) return file.delete();
		return false;
	}
	
	public static boolean DeleteFile(File file){
		if (file.exists() && file.isFile()) return file.delete();
		return false;
	}
	
	/**
	 * 读取properties文件
	 * @param fileName：文件名（相对路径）
	 * @param key：key
	 * @return
	 */
	public static String getValueFromProtertiesFileByKey(String fileName,String key)throws Exception{
		  InputStream inputStream = FileUtils.class.getResourceAsStream(fileName);   
		  Properties p = new Properties(); 
		  String value = null;
		 try {
			 p.load(inputStream);  
			 value = p.getProperty(key);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} 
		 return value;
	}
	
	public static Properties getPropertiesByFileName(String fileName){
		InputStream inputStream = FileUtils.class.getResourceAsStream(fileName);   
		  Properties p = new Properties(); 
		  try {
			p.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return p;
	}
}
