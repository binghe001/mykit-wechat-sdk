package io.mykit.wechat.mp.http.handler.media;

import io.mykit.wechat.mp.beans.json.media.news.WxMediaNewsAdd;
import io.mykit.wechat.mp.beans.json.media.news.WxNewsBatchGet;
import io.mykit.wechat.mp.beans.json.media.upload.WxMediaUpdateNews;
import io.mykit.wechat.mp.beans.map.WxMediaId;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
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
     * 上传图文消息内的图片获取URL
     * @param appid appid
     * @param secret secret
     * @param filePath 图片的本地路径 图片仅支持jpg/png格式，大小必须在1MB以下。
     * @return 微信返回的原始字符串
     * @throws Exception
     */
    public static String uploadImg(String appid, String secret, String filePath) throws Exception{
        String access_token = AccessTokenHandler.getAccessToken(appid, secret);
        String url = LoadProp.getValue(LoadProp.WEIXIN_GROUP_UPLOADIMG) + "?access_token=" + access_token;
        return WxHttpConnectionUtils.uploadFileToWeixinServer(url, filePath, WxHttpConnectionUtils.TYPE_MEDIA);
    }

    /**
     * 上传多媒体文件
     * @param appid appid
     * @param secret secret
     * @param fileType 文件类型，支持image/voice/video/thumb
     * @param filePath 文件在本地存储的绝对路径
     * @return 返回结果，成功：{"create_at":123456,"media_id":"123","type":"image"}  失败返回{"errcode":12345, "errmsg":"errmsg"}
     * 成功：{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
     * 参数	                            描述
     * type	                    媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
     * media_id	                媒体文件上传后，获取标识
     * created_at	            媒体文件上传时间戳
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

    /**
     * 获取临时素材
     * @param appid appid
     * @param secret secret
     * @param filePath 下载的文件本地路径
     * @param mediaId mediaId
     * @return
     * 正确情况下的返回HTTP头如下：
     * HTTP/1.1 200 OK
     * Connection: close
     * Content-Type: image/jpeg
     * Content-disposition: attachment; filename="MEDIA_ID.jpg"
     * Date: Sun, 06 Jan 2013 10:20:18 GMT
     * Cache-Control: no-cache, must-revalidate
     * Content-Length: 339721
     * curl -G "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
     * 如果返回的是视频消息素材，则内容如下：
     * {
     *  "video_url":DOWN_URL
     * }
     * 错误情况下的返回JSON数据包示例如下（示例为无效媒体ID错误）：
     * {"errcode":40007,"errmsg":"invalid media_id"}
     * @throws Exception
     */
     public static File getMediaFile(String appid, String secret, String filePath, String mediaId) throws Exception{
         return WxHttpConnectionUtils.downloadMedia(LoadProp.getValue(LoadProp.WEXIN_MEDIA_GET), filePath, AccessTokenHandler.getAccessToken(appid, secret), mediaId);
     }


    /**
     * 高清语音素材获取接口
     * @param appid appid
     * @param secret secret
     * @param filePath 下载后的文件在本地磁盘的存储路径(完整路径)
     * @param mediaId mediaId
     * @return
     * 正确情况下的返回HTTP头如下：
     * HTTP/1.1 200 OK
     * Connection: close
     * Content-Type: voice/speex
     * Content-disposition: attachment; filename="MEDIA_ID.speex"
     * Date: Sun, 06 Jan 2016 10:20:18 GMT
     * Cache-Control: no-cache, must-revalidate
     * Content-Length: 339721
     * curl -G "https://api.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"
     * 错误情况下的返回JSON数据包示例如下（示例为无效媒体ID错误）：
     * {"errcode":40007,"errmsg":"invalid media_id"}
     *
     * @throws Exception
     */
     public static File getMediaJSSDKFile(String appid, String secret, String filePath, String mediaId) throws Exception{
         return WxHttpConnectionUtils.downloadMedia(LoadProp.getValue(LoadProp.WEXIN_MEDIA_JSSDK_GET), filePath, AccessTokenHandler.getAccessToken(appid, secret), mediaId);
     }

    /**
     * 新增永久图文素材
     * @param appid appid
     * @param secret secret
     * @param wxMediaNewsAdd 封装的参数
     * {
     * "articles": [{
     * "title": TITLE,
     * "thumb_media_id": THUMB_MEDIA_ID,
     * "author": AUTHOR,
     * "digest": DIGEST,
     * "show_cover_pic": SHOW_COVER_PIC(0 / 1),
     * "content": CONTENT,
     * "content_source_url": CONTENT_SOURCE_URL
     * },
     * //若新增的是多图文素材，则此处应还有几段articles结构
     * ]
     * }
     * @return
     * {
     *    "media_id":MEDIA_ID
     * }
     *
     * { “errcode” : 88000， “errmsg” : “without comment privilege”   //没有留言权限 }
     * @throws Exception
     */
    public static String addNews(String appid, String secret, WxMediaNewsAdd wxMediaNewsAdd) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_NEWS_ADD), wxMediaNewsAdd.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 新增其他类型永久素材
     * @param appid appid
     * @param secret secret
     * @param fileType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param filePath 文件在本地磁盘的存路径
     * @return
     * 成功：
     * {
     *   "media_id":MEDIA_ID,
     *   "url":URL
     * }
     * 参数                   	描述
     * media_id	            新增的永久素材的media_id
     * url	                新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
     *
     * 错误情况下的返回JSON数据包示例如下（示例为无效媒体类型错误）：
     * {"errcode":40007,"errmsg":"invalid media_id"}
     * @throws Exception
     */
    public static String uploadNewsMaterial(String appid, String secret, String fileType, String filePath) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERIAL_ADD) + "?" + WxConstants.ACCESS_TOKEN + "=" + AccessTokenHandler.getAccessToken(appid, secret) + "&type=" +fileType;
        return WxHttpConnectionUtils.uploadFileToWeixinServer(url, filePath, WxHttpConnectionUtils.TYPE_MEDIA);
    }


    /**
     * 上传视频永久素材
     * @param appid appid
     * @param secret secret
     * @param filePath 文件在本地磁盘的存路径
     * @param title 视频标题
     * @param introduction 视频描述
     * @return
     * 成功：
     * {
     *   "media_id":MEDIA_ID,
     *   "url":URL
     * }
     * 参数                   	描述
     * media_id	            新增的永久素材的media_id
     * url	                新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
     *
     * 错误情况下的返回JSON数据包示例如下（示例为无效媒体类型错误）：
     * {"errcode":40007,"errmsg":"invalid media_id"}
     * @throws Exception
     */
    public static String uploadNewsVideoMaterial(String appid, String secret, String filePath, String title, String introduction) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERIAL_ADD) + "?" + WxConstants.ACCESS_TOKEN + "=" + AccessTokenHandler.getAccessToken(appid, secret) + "&type=" + WxConstants.TYPE_VIDEO;
        return WxHttpConnectionUtils.uploadVideoFileToWeixinServer(url, filePath, title, introduction);
    }

    /**
     * 获取图文和视频永久素材
     * @param appid appid
     * @param secret secret
     * @param wxMediaId mediaId
     * {
     *      "media_id":MEDIA_ID
     * }
     *
     * @return
     * 图文素材:
     * {
     *  "news_item":
     *  [
     *      {
     *      "title":TITLE,
     *      "thumb_media_id"::THUMB_MEDIA_ID,
     *      "show_cover_pic":SHOW_COVER_PIC(0/1),
     *      "author":AUTHOR,
     *      "digest":DIGEST,
     *      "content":CONTENT,
     *      "url":URL,
     *      "content_source_url":CONTENT_SOURCE_URL,
     *      "need_open_comment ":need_open_comment
     *      "only_fans_can_comment":only_fans_can_comment
     *      },
     *      //多图文消息有多篇文章
     *   ]
     * }
     *
     * 视频素材：
     * {
     *   "title":TITLE,
     *   "description":DESCRIPTION,
     *   "down_url":DOWN_URL,
     * }
     * @throws Exception
     */
    public static String getNewsOrVideoMaterail(String appid, String secret, WxMediaId wxMediaId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERIAL_GET), wxMediaId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
    /**
     * 获取其他永久素材
     * @param appid appid
     * @param secret secret
     * @param filePath 文件本地路径
     * @param wxMediaId mediaId
     * {
     *      "media_id":MEDIA_ID
     * }
     *
     * @return File文件对象
     * @throws Exception
     */
    public static File getOtherMaterail(String appid, String secret, String filePath, WxMediaId wxMediaId) throws Exception{
        return WxHttpConnectionUtils.downloadMaterial(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERIAL_GET), filePath, AccessTokenHandler.getAccessToken(appid, secret), wxMediaId.toJsonString());
    }

    /**
     * 删除永久素材
     * @param appid appid
     * @param secret secret
     * @param wxMediaId mediaId
     * {
     *      "media_id":MEDIA_ID
     * }
     * @return
     * {
     *     "errcode":ERRCODE,
     *     "errmsg":ERRMSG
     * }
     * @throws Exception
     */
    public static String deleteMaterail(String appid, String secret, WxMediaId wxMediaId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERIAL_DELETE), wxMediaId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM );
    }


    /**
     * 修改永久素材
     * @param appid appid
     * @param secret secret
     * @param wxMediaUpdateNews
     * {
     *   "media_id":MEDIA_ID,
     *   "index":INDEX,
     *   "articles": {
     *        "title": TITLE,
     *        "thumb_media_id": THUMB_MEDIA_ID,
     *        "author": AUTHOR,
     *        "digest": DIGEST,
     *        "show_cover_pic": SHOW_COVER_PIC(0 / 1),
     *        "content": CONTENT,
     *        "content_source_url": CONTENT_SOURCE_URL
     *     }
     * }
     * @return
     * {
     *   "errcode": ERRCODE,
     *   "errmsg": ERRMSG
     * }
     */
    public static String updateMaterail(String appid, String secret, WxMediaUpdateNews wxMediaUpdateNews) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERAIL_UPDATE), wxMediaUpdateNews.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 获取素材总数
     * @param appid appid
     * @param secret secret
     * @return
     * 正确：
     * {
     *   "voice_count":COUNT,
     *   "video_count":COUNT,
     *   "image_count":COUNT,
     *   "news_count":COUNT
     * }
     * 参数	                    描述
     * voice_count	          语音总数量
     * video_count	          视频总数量
     * image_count	          图片总数量
     * news_count	          图文总数量
     * 错误：
     * {"errcode":-1,"errmsg":"system error"}
     * @throws Exception
     */
    public static String countMaterail(String appid, String secret) throws Exception{
        return HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERAIL_COUNT), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 获取素材列表
     * @param appid appid
     * @param secret secret
     * @param wxNewsBatchGet
     * 格式为：
     * {
     *     "type":TYPE,
     *     "offset":OFFSET,
     *     "count":COUNT
     * }
     *
     * 参数	            是否必须	            说明
     * type	                是	        素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * offset	            是	        从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * count	            是	            返回素材的数量，取值在1到20之间
     *
     * @return
     *
     * 永久图文消息素材列表的响应如下：
     *  {
     *    "total_count": TOTAL_COUNT,
     *    "item_count": ITEM_COUNT,
     *    "item": [{
     *        "media_id": MEDIA_ID,
     *        "content": {
     *            "news_item": [{
     *                "title": TITLE,
     *                "thumb_media_id": THUMB_MEDIA_ID,
     *                "show_cover_pic": SHOW_COVER_PIC(0 / 1),
     *                "author": AUTHOR,
     *                "digest": DIGEST,
     *                "content": CONTENT,
     *                "url": URL,
     *                "content_source_url": CONTETN_SOURCE_URL,
     *                "need_open_comment ":need_open_comment
     *                "only_fans_can_comment":only_fans_can_comment
     *            },
     *            //多图文消息会在此处有多篇文章
     *            ]
     *         },
     *         "update_time": UPDATE_TIME
     *     },
     *     //可能有多个图文消息item结构
     *   ]
     * }
     *
     * 其他类型（图片、语音、视频）的返回如下：
     * {
     *    "total_count": TOTAL_COUNT,
     *    "item_count": ITEM_COUNT,
     *    "item": [{
     *        "media_id": MEDIA_ID,
     *        "name": NAME,
     *        "update_time": UPDATE_TIME,
     *        "url":URL
     *    },
     *    //可能会有多个素材
     *    ]
     * }
     *
     * 参数	                        描述
     * total_count	            该类型的素材的总数
     * item_count	            本次调用获取的素材的数量
     * title	                图文消息的标题
     * thumb_media_id	        图文消息的封面图片素材id（必须是永久mediaID）
     * show_cover_pic	        是否显示封面，0为false，即不显示，1为true，即显示
     * author	                    作者
     * digest	                图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     * content	                图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     * url	                    图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
     * content_source_url	    图文消息的原文地址，即点击“阅读原文”后的URL
     * update_time	            这篇图文消息素材的最后更新时间
     * name	                    文件名称
     *
     *
     * 错误情况下的返回JSON数据包示例如下（示例为无效媒体类型错误）
     * {"errcode":40007,"errmsg":"invalid media_id"}
     * @throws Exception
     */
    public static String batchGetMaterail(String appid, String secret, WxNewsBatchGet wxNewsBatchGet) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_MATERAIL_BATCH_GET), wxNewsBatchGet.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

}
