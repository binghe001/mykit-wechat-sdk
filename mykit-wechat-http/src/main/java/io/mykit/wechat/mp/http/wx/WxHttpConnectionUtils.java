package io.mykit.wechat.mp.http.wx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.constants.WxConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 16:31
 * @Description: 微信HTTP请求的工具类
 */
@Slf4j
public class WxHttpConnectionUtils extends HttpConnectionUtils {

    /**
     * 头像
     */
    public static final String TYPE_IMAGE = "image";
    /**
     * 多媒体
     */
    public static final String TYPE_MEDIA = "media";

    /**
     * 文件类型
     */
    public static final String TYPE_FILE = "file";

    /**
     * 上传头像或者多媒体
     * @param url 请求地址 form表单url地址,需要调用方提前拼接好参数
     * @param filePath 文件在服务器保存路径
     * @return String url的响应信息返回值
     * @throws IOException
     */
    public static String uploadFileToWeixinServer(String url, String filePath, String type) throws IOException {

        String result = null;
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            log.error("文件不存在");
            throw new IOException("文件不存在");
        }
        /**
         * 第一部分
         */
        URL urlObj = new URL(url);
        // 连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        /**
         * 设置关键字
         */
        con.setRequestMethod("POST");// 以POST方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);// POST方式不能使用缓存
        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
        // 请求正文信息
        // 第一部分
        StringBuilder sb = new StringBuilder();
        sb.append("--");// 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append(getString(file.getName(), type));
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);
        // 文件正文部分
        // 把文件以流文件的方式推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输出流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            log.error("发送POST请求异常！" + e);
            e.printStackTrace();
            throw new IOException("数据读取异常");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    private static String getString(String fileName, String type){
        return "Content-Disposition: form-data;name=\"" + type + "\";filename=\"" + fileName + "\"\r\n";
    }


    /**
     * 多媒体下载接口
     * @comment 不支持视频文件的下载
     * @param fileName  素材存储文件路径,完整绝对路径
     * @param token     认证token
     * @param mediaId   素材ID（对应上传后获取到的ID）
     * @return 素材文件
     */
    public static File downloadMedia(String url, String fileName, String token, String mediaId) {
        url = url + "?" + WxConstants.ACCESS_TOKEN + "=" + token + "&media_id=" + mediaId;
        return httpRequestToFile(fileName, url, "GET", null);
    }
    /**
     * 其他类型的素材消息，则响应的直接为素材的内容，开发者可以自行保存为文件
     * @comment 不支持视频文件的下载
     * @param fileName  素材存储文件路径,完整绝对路径
     * @param token     认证token
     * @param '{"media_id":"61224425"}'
     * @return 素材文件
     */
    public static File downloadMaterial(String url, String fileName, String token, String json) {
        url = url + "?" + WxConstants.ACCESS_TOKEN + "=" + token;
        return httpRequestToFile(fileName, url, "POST", json);
    }
    /**
     * 以http方式发送请求,并将请求响应内容输出到文件
     * @param path    请求路径
     * @param method  请求方法
     * @param body    请求数据
     * @return 返回响应的存储到文件
     */
    public static File httpRequestToFile(String fileName, String path, String method, String body) {
        if(fileName==null||path==null||method==null){
            return null;
        }

        File file = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            if(inputStream!=null){
                file = new File(fileName);
            }else{
                return file;
            }

            //写入到文件
            fileOut = new FileOutputStream(file);
            if(fileOut!=null){
                int c = inputStream.read();
                while(c!=-1){
                    fileOut.write(c);
                    c = inputStream.read();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally{
            if(conn!=null){
                conn.disconnect();
            }

            /*
             * 必须关闭文件流
             * 否则JDK运行时，文件被占用其他进程无法访问
             */
            try {
                inputStream.close();
                fileOut.close();
            } catch (IOException execption) {
                log.error(execption.getMessage());
            }
        }
        return file;
    }


    /**
     * 上传视频永久素材
     * @param url https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE
     * @param filePath 视频在本地磁盘的存储路径
     * @param title 	视频素材的标题
     * @param introduction 视频素材的描述
     * @return
     * 成功：
     * {
     *   "media_id":MEDIA_ID,
     *   "url":URL
     * }
     *
     * 失败：
     * {"errcode":40007,"errmsg":"invalid media_id"}
     */
    public static String uploadVideoFileToWeixinServer(String url, String filePath, String title, String introduction) {
        File file = new File(filePath);
        if(!file.exists())
            return null;
        String result = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------"+System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
            output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
            byte[] data = new byte[1024];
            int len =0;
            FileInputStream input = new FileInputStream(file);
            while((len=input.read(data))>-1){
                output.write(data, 0, len);
            }
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
            output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}",title,introduction).getBytes());
            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            output.close();
            input.close();
            InputStream resp = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            while((len= resp.read(data))>-1)
                sb.append(new String(data,0,len,"utf-8"));
            resp.close();
            result = sb.toString();
            System.out.println(result);
        } catch (ClientProtocolException e) {
            log.error("postFile，不支持http协议",e);
        } catch (IOException e) {
            log.error("postFile数据传输失败",e);
        }
        log.info("{}: result={}",url,result);
        return result;
    }
}
