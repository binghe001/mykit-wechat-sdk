package io.mykit.wechat.mp.http.handler.mass;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.mass.delete.WxMassDeleteMessage;
import io.mykit.wechat.mp.beans.json.mass.get.WxMassGetMessage;
import io.mykit.wechat.mp.beans.json.mass.openid.image.WxMassOpenIdImageMessage;
import io.mykit.wechat.mp.beans.json.mass.openid.news.WxMassOpenIdNewsMassage;
import io.mykit.wechat.mp.beans.json.mass.openid.text.WxMassOpenIdTextMessage;
import io.mykit.wechat.mp.beans.json.mass.openid.video.WxMassOpenIdVideoMessage;
import io.mykit.wechat.mp.beans.json.mass.openid.voice.WxMassOpenIdVoiceMessage;
import io.mykit.wechat.mp.beans.json.mass.openid.wxcard.WxMassOpenIdCardMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.image.WxMassPreviewImageMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.news.WxMassPreviewNewsMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.text.WxMassPreviewTextMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.video.WxMassPreviewVideoMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.voice.WxMassPreviewVoiceMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.wxcard.WxMassPreviewCardMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.image.WxMassTagImageMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.news.WxMassTagNewsMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.text.WxMassTagTextMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.video.WxMassTagVideoMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.video.WxMassTagVideoUploadMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.voice.WxMassTagVoiceMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.wxcard.WxMassTagCardMessage;
import io.mykit.wechat.mp.beans.json.media.WxMediaUploadNews;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.utils.exception.WxHttpException;
import io.mykit.wechat.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 13:35
 * @Description: 群发消息处理类
 */
@Slf4j
public class WxMassHandler extends BaseHandler {

    /**
     * 上传图文消息素材
     * @param appid appid
     * @param secret secret
     * @param wxMediaUploadNews 封装的接口请求体
     *                          post数据格式如下：
     *                          {
     *     "articles": [
     *         {
     *             "thumb_media_id": "qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
     *             "author": "xxx",
     *             "title": "Happy Day",
     *             "content_source_url": "www.qq.com",
     *             "content": "content",
     *             "digest": "digest",
     *             "show_cover_pic": 1
     *         },
     *         {
     *             "thumb_media_id": "qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
     *             "author": "xxx",
     *             "title": "Happy Day",
     *             "content_source_url": "www.qq.com",
     *             "content": "content",
     *             "digest": "digest",
     *             "show_cover_pic": 0
     *         }
     *     ]
     * }
     * @return 成功：：{"create_at":123456,"media_id":"123","type":"image"}  失败返回:{"errcode":12345, "errmsg":"errmsg"}
     * @throws Exception
     */
    public static String uploadMediaNewsFile(String appid, String secret, WxMediaUploadNews wxMediaUploadNews) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_NEWS_UPLOAD), wxMediaUploadNews.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 根据标签发送微信图文消息
     * @param appid appid
     * @param secret secret
     * @param wxMassNewsMessage 封装的请求体，格式如下：
     *  {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "mpnews":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"mpnews",
     *     "send_ignore_reprint":0
     * }
     * @return 结果数据：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * @throws Exception
     */
    public static String sendMpnewsMessageByTag(String appid, String secret, WxMassTagNewsMessage wxMassNewsMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SENDALL), wxMassNewsMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 根据标签发送微信文本消息
     * @param appid appid
     * @param secret secret
     * @param wxMassTagTextMessage 封装的请求体，格式如下：
     *  {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "text":{
     *       "content":"CONTENT"
     *    },
     *     "msgtype":"text"
     * }
     * @return 结果数据：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * @throws Exception
     */
    public static String sendTextMessageByTag(String appid, String secret, WxMassTagTextMessage wxMassTagTextMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SENDALL), wxMassTagTextMessage.toString(),getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
    /**
     * 语音/音频（注意此处media_id需通过基础支持中的上传下载多媒体文件来得到）
     * @param appid appid
     * @param secret secret
     * @param wxMassTagVoiceMessage 封装的请求体，格式如下：
     *  {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "voice":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"voice"
     * }
     * @return 结果数据：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * @throws Exception
     */
    public static String sendVoiceMessageByTag(String appid, String secret, WxMassTagVoiceMessage wxMassTagVoiceMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SENDALL), wxMassTagVoiceMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 图片（注意此处media_id需通过基础支持中的上传下载多媒体文件来得到）
     * @param appid appid
     * @param secret secret
     * @param wxMassTagImageMessage 封装的请求体，格式如下：
     *  {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "image":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"image"
     * }
     * @return 结果数据：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * @throws Exception
     */
    public static String sendImageMessageByTag(String appid, String secret, WxMassTagImageMessage wxMassTagImageMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SENDALL), wxMassTagImageMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 群发消息上传视频素材
     * @param appid appid
     * @param appsecret secret
     * @param wxMassVideoUploadMessage 封装的请求体，格式如下：
     * {
     *   "media_id": "rF4UdIMfYK3efUfyoddYRMU50zMiRmmt_l0kszupYh_SzrcW5Gaheq05p_lHuOTQ",
     *   "title": "TITLE",
     *   "description": "Description"
     * }
     * @return 格式：
     * {
     *   "type":"video",
     *   "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",
     *   "created_at":1398848981
     * }
     * @throws Exception
     */
    public static String uploadMediaVideo(String appid, String appsecret, WxMassTagVideoUploadMessage wxMassVideoUploadMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MEDIA_VIDEO_UPLOAD), wxMassVideoUploadMessage.toString(), getAccessTokenNameValuePairs(appid, appsecret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 视频（注意此处media_id需通过uploadMediaVideo方法得到）
     * @param appid appid
     * @param secret secret
     * @param wxMassTagVideoMessage 封装的请求体，格式如下：
     *  {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "mpvideo":{
     *       "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc"
     *    },
     *     "msgtype":"mpvideo"
     * }
     * @return 结果数据：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * @throws Exception
     */
    public static String sendVideoMessageByTag(String appid, String secret, WxMassTagVideoMessage wxMassTagVideoMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SENDALL), wxMassTagVideoMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 卡券消息（注意图文消息的media_id需要通过上述方法来得到）
     * @param appid appid
     * @param secret secret
     * @param wxMassTagCardMessage 封装的请求体，格式如下：
     *  {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":"2"
     *    },
     *   "wxcard":{
     *            "card_id":"123dsdajkasd231jhksad"
     *             },
     *    "msgtype":"wxcard"
     * }
     * @return 结果数据：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * @throws Exception
     */
    public static String sendWxcardMessageByTag(String appid, String secret, WxMassTagCardMessage wxMassTagCardMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SENDALL), wxMassTagCardMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 根据openId群发图文消息
     * @param appid appid
     * @param secret secret
     * @param wxMassOpenIdNewsMassage 封装微信图文消息
     * 格式如下：
     *
     *{
     *    "touser":[
     *     "OPENID1",
     *     "OPENID2"
     *    ],
     *    "mpnews":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"mpnews"，
     *     "send_ignore_reprint":0
     * }
     *
     * @return 结果
     * 正确时返回：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     *
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     *
     * 错误时返回具体状态信息
     *{"errcode": 状态码, "errmsg":"状态信息"}
     * @throws Exception
     */
    public static String sendWxNewsMessageByOpenId(String appid, String secret, WxMassOpenIdNewsMassage wxMassOpenIdNewsMassage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SEND), wxMassOpenIdNewsMassage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 根据openId群发文本消息
     * @param appid appid
     * @param secret secret
     * @param wxMassOpenIdTextMessage 封装微信文本消息
     * 格式如下：
     *
     *{
     *    "touser":[
     *     "OPENID1",
     *     "OPENID2"
     *    ],
     *     "msgtype": "text",
     *     "text": { "content": "hello from boxer."}
     * }
     *
     * @return 结果
     * 正确时返回：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     *
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     *
     * 错误时返回具体状态信息
     *{"errcode": 状态码, "errmsg":"状态信息"}
     * @throws Exception
     */
    public static String sendWxTextMessageByOpenId(String appid, String secret, WxMassOpenIdTextMessage wxMassOpenIdTextMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SEND), wxMassOpenIdTextMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 根据openId群发语音消息
     * @param appid appid
     * @param secret secret
     * @param wxMassOpenIdVoiceMessage 封装微信语音消息
     * 格式如下：
     *
     *{
     *    "touser":[
     *     "OPENID1",
     *     "OPENID2"
     *    ],
     *    "voice":{
     *       "media_id":"mLxl6paC7z2Tl-NJT64yzJve8T9c8u9K2x-Ai6Ujd4lIH9IBuF6-2r66mamn_gIT"
     *    },
     *     "msgtype":"voice"
     * }
     *
     * @return 结果
     * 正确时返回：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     *
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     *
     * 错误时返回具体状态信息
     *{"errcode": 状态码, "errmsg":"状态信息"}
     * @throws Exception
     */
    public static String sendWxVoiceMessageByOpenId(String appid, String secret, WxMassOpenIdVoiceMessage wxMassOpenIdVoiceMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SEND), wxMassOpenIdVoiceMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
    /**
     * 根据openId群发图片消息
     * @param appid appid
     * @param secret secret
     * @param wxMassOpenIdImageMessage 封装微信图片消息
     * 格式如下：
     *
     *{
     *    "touser":[
     *     "OPENID1",
     *     "OPENID2"
     *    ],
     *    "image":{
     *       "media_id":"BTgN0opcW3Y5zV_ZebbsD3NFKRWf6cb7OPswPi9Q83fOJHK2P67dzxn11Cp7THat"
     *    },
     *     "msgtype":"image"
     * }
     *
     * @return 结果
     * 正确时返回：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     *
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     *
     * 错误时返回具体状态信息
     *{"errcode": 状态码, "errmsg":"状态信息"}
     * @throws Exception
     */
    public static String sendWxImageMessageByOpenId(String appid, String secret, WxMassOpenIdImageMessage wxMassOpenIdImageMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SEND), wxMassOpenIdImageMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 根据openId群发视频消息
     * @param appid appid
     * @param secret secret
     * @param wxMassOpenIdVideoMessage 封装微信视频消息
     * 格式如下：
     *
     *{
     *    "touser":[
     *     "OPENID1",
     *     "OPENID2"
     *    ],
     *    "mpvideo":{
     *       "media_id":"123dsdajkasd231jhksad",
     *       "title":"TITLE",
     *       "description":"DESCRIPTION"
     *    },
     *     "msgtype":"mpvideo"
     * }
     *
     * @return 结果
     * 正确时返回：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     *
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     *
     * 错误时返回具体状态信息
     *{"errcode": 状态码, "errmsg":"状态信息"}
     * @throws Exception
     */
    public static String sendWxVideoMessageByOpenId(String appid, String secret, WxMassOpenIdVideoMessage wxMassOpenIdVideoMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SEND), wxMassOpenIdVideoMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 根据openId群发卡券消息
     * @param appid appid
     * @param secret secret
     * @param wxMassOpenIdCardMessage 封装微信卡券消息
     * 格式如下：
     *{
     *    "touser":[
     *     "OPENID1",
     *     "OPENID2"
     *    ],
     *         "wxcard": {"card_id":"123dsdajkasd231jhksad"}
     *         "msgtype":"wxcard"
     * }
     *
     * @return 结果
     * 正确时返回：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182,
     *    "msg_data_id": 206227730
     * }
     *
     * type	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息发送任务的ID
     * msg_data_id	消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     *
     * 错误时返回具体状态信息
     *{"errcode": 状态码, "errmsg":"状态信息"}
     * @throws Exception
     */
    public static String sendWxCardMessageByOpenId(String appid, String secret, WxMassOpenIdCardMessage wxMassOpenIdCardMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_SEND), wxMassOpenIdCardMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 删除群发消息
     * @param appid appid
     * @param secret secret
     * @param wxMassDeleteMessage 封装的删除群发消息请求体
     * 格式如下：
     * {
     *    "msg_id":30124,
     *    "article_idx":2
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"ok"
     * }
     * @throws Exception
     */
    public static WxCode deleteMassMessage(String appid, String secret, WxMassDeleteMessage wxMassDeleteMessage) throws Exception{
        String ret = HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_DELETE), wxMassDeleteMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
        return JsonUtils.json2Bean(ret, WxCode.class);
    }

    /**
     * 预览图文消息
     * @param appid appid
     * @param secret secret
     * @param wxMassPreviewNewsMessage 封装的请求体
     * 格式如下：
     * {
     *    "touser":"OPENID",
     *    "mpnews":{
     *             "media_id":"123dsdajkasd231jhksad"
     *              },
     *    "msgtype":"mpnews"
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 参数	     说明
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息ID
     * @throws Exception
     */
    public static String previewMassNewsMessage(String appid, String secret, WxMassPreviewNewsMessage wxMassPreviewNewsMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_PREVIEW), wxMassPreviewNewsMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 预览文本消息
     * @param appid appid
     * @param secret secret
     * @param wxMassPreviewTextMessage 封装的请求体
     * 格式如下：
     * {
     *     "touser":"OPENID",
     *     "text":{
     *            "content":"CONTENT"
     *            },
     *     "msgtype":"text"
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 参数	     说明
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息ID
     * @throws Exception
     */
    public static String previewMassTextMessage(String appid, String secret, WxMassPreviewTextMessage wxMassPreviewTextMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_PREVIEW), wxMassPreviewTextMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 预览语音消息
     * @param appid appid
     * @param secret secret
     * @param wxMassPreviewVoiceMessage 封装的请求体
     * 格式如下：
     * {
     *     "touser":"OPENID",
     *     "voice":{
     *             "media_id":"123dsdajkasd231jhksad"
     *             },
     *     "msgtype":"voice"
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 参数	     说明
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息ID
     * @throws Exception
     */
    public static String previewMassVoiceMessage(String appid, String secret, WxMassPreviewVoiceMessage wxMassPreviewVoiceMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_PREVIEW), wxMassPreviewVoiceMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
    /**
     * 预览图片消息
     * @param appid appid
     * @param secret secret
     * @param wxMassPreviewImageMessage 封装的请求体
     * 格式如下：
     * {
     *     "touser":"OPENID",
     *     "image":{
     *             "media_id":"123dsdajkasd231jhksad"
     *             },
     *     "msgtype":"image"
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 参数	     说明
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息ID
     * @throws Exception
     */
    public static String previewMassImageMessage(String appid, String secret, WxMassPreviewImageMessage wxMassPreviewImageMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_PREVIEW), wxMassPreviewImageMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 预览视频消息
     * @param appid appid
     * @param secret secret
     * @param wxMassPreviewVideoMessage 封装的请求体
     * 格式如下：
     * {
     *     "touser":"OPENID",
     *     "mpvideo":{  "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",
     *                },
     *     "msgtype":"mpvideo"
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 参数	     说明
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息ID
     * @throws Exception
     */
    public static String previewMassVideoMessage(String appid, String secret, WxMassPreviewVideoMessage wxMassPreviewVideoMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_PREVIEW), wxMassPreviewVideoMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 预览卡券消息
     * @param appid appid
     * @param secret secret
     * @param wxMassPreviewCardMessage 封装的请求体
     * 格式如下：
     * { "touser":"OPENID",
     *   "wxcard":{
     *            "card_id":"123dsdajkasd231jhksad",
     *             "card_ext": "{"code":"","openid":"","timestamp":"1402057159","signature":"017bb17407c8e0058a66d72dcc61632b70f511ad"}"
     *             },
     *   "msgtype":"wxcard"
     * }
     * @return
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 参数	     说明
     * errcode	错误码
     * errmsg	错误信息
     * msg_id	消息ID
     * @throws Exception
     */
    public static String previewMassCardMessage(String appid, String secret, WxMassPreviewCardMessage wxMassPreviewCardMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_PREVIEW), wxMassPreviewCardMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 查询群发消息发送状态
     * @param appid appid
     * @param secret secret
     * @param wxMassGetMessage 消息体
     * 格式如下：
     * {
     *    "msg_id": "201053012"
     * }
     * @return
     * 正确时：
     * {
     *      "msg_id":201053012,
     *      "msg_status":"SEND_SUCCESS"
     * }
     * msg_id	群发消息后返回的消息id
     * msg_status	消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     * @throws Exception
     */
    public static String getMassMessage(String appid, String secret, WxMassGetMessage wxMassGetMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MASS_GET), wxMassGetMessage.toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }




}
