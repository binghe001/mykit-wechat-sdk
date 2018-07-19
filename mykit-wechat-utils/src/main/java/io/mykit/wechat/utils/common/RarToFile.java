package io.mykit.wechat.utils.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RAR 5.0以上调用命令解压
 * @author liuyazhuang
 *
 */
public class RarToFile extends Compress{
   /*
    * cmd 压缩与解压缩命令
    */
    private static String rarCmd = "rar a "; 
    private static String unrarCmd = "rar x ";   
    private static String cdCmd = "cd ";

   /**
    * 将1个文件压缩成RAR格式
    * rarName 压缩后的压缩文件名(不包含后缀)
    * fileName 需要压缩的文件名(必须包含路径)
    * destDir 压缩后的压缩文件存放路径
    */
    public static String RARFile(String rarName, String fileName, String destDir) {
       String rarFile = destDir + rarName + ".rar";
       rarCmd += destDir + rarName + ".rar " + fileName;
       try {
           Runtime rt = Runtime.getRuntime();
           Process p = rt.exec(rarCmd);
           return rarFile;
       }catch(Exception e) {
    	   System.out.println(e.getMessage());      
       }
       return "";
    }

   /**
    * 将1个RAR文件解压
    * rarFileName 需要解压的RAR文件(必须包含路径信息以及后缀)
    * destDir 解压后的文件放置目录
    */
    public static String unRARFile(String rarFileName, String destDir) {
       unrarCmd += rarFileName + " " + destDir;
       try {
           Runtime rt = Runtime.getRuntime();
           Process p = rt.exec(unrarCmd); 
           return destDir;
       } catch (Exception e) {
    	   System.out.println(e.getMessage());   
       }
       return "";
    }
    
    public static void cdDir(String destDir){
    	try {
    		 cdCmd += destDir;
    		 System.out.println(cdCmd);
    		 Runtime rt = Runtime.getRuntime();
    		 rt.exec(cdCmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static String executeNewFlow(String srcDir, String rarFileName, String destDir) {
        Runtime run = Runtime.getRuntime();
        File wd = new File("/bin");
        Process proc = null;
        try {
            proc = run.exec("/bin/bash", null, wd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (proc != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            out.println("cd " + srcDir);
            out.println(unrarCmd + rarFileName + " " + destDir);
            out.println("exit");//这个命令必须执行，否则in流不结束。
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                proc.waitFor();
                in.close();
                out.close();
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return destDir;
    }
    
    /**
     * 解压并返回文件List集合
     * @param file
     * @param destDir
     * @param type
     * @param html
     * @return
     * @throws Exception
     */
    public static List<String> unRARFile(File file,String destDir, String type, String html) throws Exception{
    	List<String> list = new ArrayList<String>();
    	//切换目录到文件所在的目录
    	String fileDir = getFileDir(file.getAbsolutePath());
//    	System.out.println(fileDir);
//		cdDir(fileDir);
//    	//调用命令解压
//    	String filePath = unRARFile(getFileNameWithExt(file), destDir);
    	String filePath = executeNewFlow(fileDir, getFileNameWithExt(file), destDir);
    	return getFilePaths(filePath, list, type, html);
    }
    
    
    /**
     * 获取文件的名字
     * @param filePath
     * @return
     */
    public static String getFileNameWithExt(File file){
    	String fileName = file.getName();
    	return fileName;
    }
    /**
     * 获取文件的名字
     * @param filePath
     * @return
     */
    public static String getFileNameWithExt(String filePath){
    	File file = new File(filePath);
    	return getFileNameWithExt(file);
    }
    /**
     * 获取文件的名字
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath){
    	File file = new File(filePath);
    	String fileName = file.getName();
    	if(fileName.contains(".") && fileName.lastIndexOf(".") < fileName.length() - 1){
    		fileName = fileName.substring(0, fileName.lastIndexOf("."));
    	}
    	return fileName;
    }
    
    
    /**
     * 获取文件所在的目录
     * @param filePath
     * @return
     */
    private static String getFileDir(String filePath){
    	if(filePath.contains("\\")){
    		filePath = filePath.replace("\\", "/");
    	}
    	int index = filePath.lastIndexOf("/");
    	return filePath.substring(0, index);
    }
    
    public static void main(String[] args) {
		System.out.println(getFileNameWithExt("C:/Users/medcare-Android/Desktop/111/111.rar"));
//    	String str = "C:/Users/medcare-Android/Desktop/111/111.rar";
//    	int index = str.lastIndexOf("/");
//    	System.out.println(str.substring(0, index));
	}
}

