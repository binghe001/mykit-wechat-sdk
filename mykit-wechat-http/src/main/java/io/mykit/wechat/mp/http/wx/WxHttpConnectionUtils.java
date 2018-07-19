package io.mykit.wechat.mp.http.wx;

import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;

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
        switch (type){
            case TYPE_IMAGE:
                return "Content-Disposition: form-data;name=\"file\";filename=\""+ fileName + "\"\r\n";
            case TYPE_MEDIA:
                return "Content-Disposition: form-data;name=\"media\";filename=\"" + fileName + "\"\r\n";
            default:
                return "Content-Disposition: form-data;name=\"media\";filename=\"" + fileName + "\"\r\n";
        }
    }
}
