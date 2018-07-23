package io.mykit.wechat.mp.http.handler.media;

import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.constants.WxConstants;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.mp.http.handler.token.AccessTokenHandler;
import io.mykit.wechat.mp.http.wx.WxHttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 09:23
 * @Description: 微信多媒体处理类
 */
@Slf4j
public class WxMediaHandler extends BaseHandler {

    /**
     * 上传多媒体文件
     * @param appid appid
     * @param secret secret
     * @param fileType 文件类型，支持image/voice/video/thumb
     * @param filePath 文件在本地存储的绝对路径
     * @return 返回结果，成功：{"create_at":123456,"media_id":"123","type":"image"}  失败返回{"errcode":12345, "errmsg":"errmsg"}
     * @throws Exception
     */
     public static String uploadMediaFile(String appid, String secret, String fileType, String filePath) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_BASE_UPLOAD) + "?" + WxConstants.ACCESS_TOKEN + "=" + AccessTokenHandler.getAccessToken(appid, secret) + "&type=" +fileType;
        return WxHttpConnectionUtils.uploadFileToWeixinServer(url, filePath, WxHttpConnectionUtils.TYPE_MEDIA);
     }
    /**
     * 上传多媒体文件
     * @param appid appid
     * @param secret secret
     * @param fileType 文件类型，支持image/voice/video/thumb
     * @param filePath 文件在本地存储的绝对路径
     * @return 返回结果，成功：{"create_at":123456,"media_id":"123","type":"image"}  失败返回{"errcode":12345, "errmsg":"errmsg"}
     * @throws Exception
     */
     public static String uploadMediaFile2(String appid, String secret, String fileType, String filePath) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_BASE_UPLOAD_FILE) + "?" + WxConstants.ACCESS_TOKEN + "=" + AccessTokenHandler.getAccessToken(appid, secret) + "&type=" +fileType;
        return WxHttpConnectionUtils.uploadFileToWeixinServer(url, filePath, WxHttpConnectionUtils.TYPE_MEDIA);
     }

    /**
     * 下载多媒体文件
     * @param filePath 存储到本地磁盘上的绝对路径
     * @param mediaId 微信服务器上多媒体文件的id
     * @return File对象
     * @throws Exception
     */
     public static File downloadMediaFile(String appid, String secret, String filePath, String mediaId) throws Exception{
        return WxHttpConnectionUtils.downloadMedia(LoadProp.getValue(LoadProp.WEIXIN_BASE_DOWNLOAD_FILE), filePath, AccessTokenHandler.getAccessToken(appid, secret), mediaId);
     }

}
