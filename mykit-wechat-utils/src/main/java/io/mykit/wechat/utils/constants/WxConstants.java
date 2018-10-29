package io.mykit.wechat.utils.constants;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 21:23
 * @Description: 一些常量
 */

public class WxConstants {

    /**
     * 关注事件
     */
    public static final String EVENT_SUBSCRIBE = "subscribe";
    /**
     * 取消关注事件
     */
    public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
    /**
     * access_token
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * 状态码
     */
    public static final String ERRCODE = "errcode";


    /**
     * 上传多媒体图片类型
     */
    public static final String MEDIA_TYPE_IMAGE = "image";
    /**
     * 语音
     */
    public static final String MEDIA_TYPE_VOICE = "voice";
    /**
     * 视频
     */
    public static final String MEDIA_TYPE_VIDEO = "video";
    /**
     * 缩略图
     */
    public static final String MEDIA_TYPE_THUMB = "thumb";

    /**
     * 图文消息
     */
    public static final String TYPE_MPNEWS = "mpnews";
    /**
     * 图文消息
     */
    public static final String TYPE_NEWS = "news";
    /**
     * 图片
     */
    public static final String TYPE_IMAGE = "image";
    /**
     * 文本
     */
    public static final String TYPE_TEXT = "text";
    /**
     * 语音
     */
    public static final String TYPE_VIOCE = "voice";
    /**
     * 音乐
     */
    public static final String TYPE_MUSIC = "music";
    /**
     * 视频
     */
    public static final String TYPE_VIDEO = "video";
    /**
     * 小视频
     */
    public static final String TYPE_SHORTVIDEO = "shortvideo";

    /**
     * 地理位置
     */
    public static final String TYPE_LOCATION = "location";

    /**
     * 链接消息
     */
    public static final String TYPE_LINK = "link";

    /**
     * 事件消息
     */
    public static final String TYPE_EVENT = "event";

    /**
     * 关注事件
     */
    public static final String TYPE_EVENT_SUBSCRIBE = "subscribe";
    /**
     * 取消关注
     */
    public static final String TYPE_EVENT_UNSUBSCRIBE = "unsubscribe";

    /**
     * 扫描二维码，用户已关注时的类型
     */
    public static final String TYPE_EVENT_SCAN = "SCAN";
    /**
     * 上报地理位置
     */
    public static final String TYPE_EVENT_LOCATION = "LOCATION";
    /**
     * 自定义菜单事件
     */
    public static final String TYPE_EVENT_CLICK = "CLICK";
    /**
     * 点击菜单跳转链接时的事件推送
     */
    public static final String TYPE_EVENT_VIEW = "VIEW";

    /**
     * 微信卡券
     */
    public static final String TYPE_WXCARD = "wxcard";

    /**
     * 包含<Encrypt>标签
     */
    public static final String TAG_ENCRYPT = "<Encrypt>";


    /**
     * 路由文本
     */
    public static final String ROUTER_TEXT = "router_text";
    /**
     * 路由图片
     */
    public static final String ROUTER_IMAGE = "router_image";
    /**
     * 路由语音
     */
    public static final String ROUTER_VOICE = "router_voice";
    /**
     * 路由视频
     */
    public static final String ROUTER_VIDEO = "router_video";
    /**
     * 路由小视频
     */
    public static final String ROUTER_SHORTVIDEO = "router_shortvideo";
    /**
     * 路由地理位置
     */
    public static final String ROUTER_LOCATION = "router_location";
    /**
     * 路由链接
     */
    public static final String ROUTER_LINK = "router_link";
    /**
     * 普通关注事件
     */
    public static final String ROUTER_EVENT_SUBSCRIBE = "router_event_subscribe";
    /**
     * 取消关注
     */
    public static final String ROUTER_EVENT_UNSUBSCRIBE = "router_event_unsubscribe";
    /**
     * 扫描带参数二维码事件关注
     */
    public static final String ROUTER_EVENT_QRCODE_SUBSCRIBE = "router_event_qrcode_subscribe";

    /**
     * 扫描二维码，用户已关注时的类型
     */
    public static final String ROUTER_EVENT_SCAN = "router_event_scan";
    /**
     * 上报地理位置
     */
    public static final String ROUTER_EVENT_LOCATION = "router_event_location";
    /**
     * 自定义菜单事件
     */
    public static final String ROUTER_EVENT_CLICK = "router_event_click";
    /**
     * 点击菜单跳转链接时的事件推送
     */
    public static final String ROUTER_EVENT_VIEW = "router_event_view";
}
