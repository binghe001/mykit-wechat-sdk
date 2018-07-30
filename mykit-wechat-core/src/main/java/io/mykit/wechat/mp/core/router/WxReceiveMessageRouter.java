package io.mykit.wechat.mp.core.router;

import io.mykit.wechat.mp.beans.router.receive.WxReceiveRouterMessage;
import io.mykit.wechat.mp.beans.router.receive.WxReceiveRouterResult;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.*;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuClientEventMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPushEventMessage;
import io.mykit.wechat.mp.beans.xml.receive.image.WxReceiveImageMessage;
import io.mykit.wechat.mp.beans.xml.receive.link.WxReceiveLinkMessage;
import io.mykit.wechat.mp.beans.xml.receive.location.WxReceiveLocationMessage;
import io.mykit.wechat.mp.beans.xml.receive.text.WxReceiveTextMessage;
import io.mykit.wechat.mp.beans.xml.receive.video.WxReceiveShortVideoMessage;
import io.mykit.wechat.mp.beans.xml.receive.video.WxReceiveVideoMessage;
import io.mykit.wechat.mp.beans.xml.receive.voice.WxReceiveVoiceMessage;
import io.mykit.wechat.utils.common.StringUtils;
import io.mykit.wechat.utils.constants.WxConstants;
import io.mykit.wechat.utils.xml.handler.XStreamHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/30 09:36
 * @Description: 微信消息路由，主要的功能是当被动接收微信服务器发送过来的XML消息时，经过此方法的路由后返回完整的解析后的XML JavaBean供业务层进行业务开发
 */
@Slf4j
public class WxReceiveMessageRouter {

    /**
     * 路由XML，将XML转化为实际的JavaBean
     * @param xml 原始字符串
     * @return 转化后的JavaBean
     * @throws Exception
     */
    public static WxReceiveRouterResult router(String xml){
        //传递的xml字符串为空，直接返回空
        if (StringUtils.isEmpty(xml)) return null;
        //包含<Encrypt>标签，说明消息体已经被加密，则路由密文
        if (xml.contains(WxConstants.TAG_ENCRYPT)){
            return routerCrypt(xml);
        }
        //解析明文xml
        return routerPlaintext(xml);
    }

    /**
     * 路由加密后的消息<br/><br/>
     * 特殊说明<br/>
     * 1、解密需要传入开发者在微信公众号配置的encodingAesKey和token，同时还需要微信公众号的唯一标识appId，这里无法兼容多公众号接入<br/>
     * 2、后续想办法解决这个问题，目前暂时不兼容路由加密后的xml字符串，只支持明文模式<br/>
     * 3、后续解决了密文解密支持多公众号接入的问题后，此方法将xml密文解密为明文，直接调用routerPlaintext(String xml)方法即可<br/>
     * @param xml 原始xml字符串
     * @return 路由结果
     */
    private static WxReceiveRouterResult routerCrypt(String xml) {
        //TODO 解密需要传入开发者在微信公众号配置的encodingAesKey和token，同时还需要微信公众号的唯一标识appId，这里无法兼容多公众号接入，
        //TODO 后续想办法解决这个问题，目前暂时不兼容路由加密后的xml字符串，只支持明文模式
        //TODO 后续解决了密文解密支持多公众号接入的问题后，此方法将xml密文解密为明文，直接调用routerPlaintext(String xml)方法即可。
        return null;
    }

    /**
     * 路由明文XML字符串
     * @param xml xml原始字符串
     * @return 路由结果
     */
    private static WxReceiveRouterResult routerPlaintext(String xml){
        //将微信原始XML转化为微信路由实体
        WxReceiveRouterMessage wxRouterMessage = XStreamHandler.toBean(xml, WxReceiveRouterMessage.class);
        return routerMessage(wxRouterMessage, xml);
    }

    /**
     * 根据wxRouterMessage路由实际消息
     * @param wxRouterMessage 微信路由的依据类
     * @param xml 原始XML
     * @return 转化后的JavaBean
     */
    private static WxReceiveRouterResult routerMessage(WxReceiveRouterMessage wxRouterMessage, String xml){
        //转化后的实体为空，或者实体的msgType为空，直接返回null
        if (wxRouterMessage == null || StringUtils.isEmpty(wxRouterMessage.getMsgType()))
            return null;
        //log.info("解析后的WxReceiveRouterMessage对象为：" + wxRouterMessage.toJsonString() + ",wxRouterMessage.getEvent() = " + wxRouterMessage.getEvent());
        switch (wxRouterMessage.getMsgType()){
            //文本消息
            case WxConstants.TYPE_TEXT:
                return buildRouterResult(WxConstants.ROUTER_TEXT, XStreamHandler.toBean(xml, WxReceiveTextMessage.class));
             //图片消息
            case WxConstants.TYPE_IMAGE:
                return buildRouterResult(WxConstants.ROUTER_IMAGE, XStreamHandler.toBean(xml, WxReceiveImageMessage.class));
            //路由语音
            case WxConstants.TYPE_VIOCE:
                return buildRouterResult(WxConstants.ROUTER_VOICE, XStreamHandler.toBean(xml, WxReceiveVoiceMessage.class));
            //路由视频
            case WxConstants.TYPE_VIDEO:
                return buildRouterResult(WxConstants.ROUTER_VIDEO, XStreamHandler.toBean(xml, WxReceiveVideoMessage.class));
            //路由小视频
            case WxConstants.TYPE_SHORTVIDEO:
                return buildRouterResult(WxConstants.ROUTER_SHORTVIDEO, XStreamHandler.toBean(xml, WxReceiveShortVideoMessage.class));
            //路由地理位置
            case WxConstants.TYPE_LOCATION:
                return buildRouterResult(WxConstants.ROUTER_LOCATION, XStreamHandler.toBean(xml, WxReceiveLocationMessage.class));
            //路由链接
            case WxConstants.TYPE_LINK:
                return buildRouterResult(WxConstants.ROUTER_LINK, XStreamHandler.toBean(xml, WxReceiveLinkMessage.class));
             //路由事件消息
            case WxConstants.TYPE_EVENT:
                return routerEventMessage(wxRouterMessage, xml);
            //默认路由文本
            default:
                return buildRouterResult(WxConstants.ROUTER_TEXT, XStreamHandler.toBean(xml, WxReceiveTextMessage.class));
        }
    }

    /**
     * 路由事件消息
     * @param wxRouterMessage 微信路由的依据类
     * @param xml 原始XML
     * @return 转化后的JavaBean
     */
    private static WxReceiveRouterResult routerEventMessage(WxReceiveRouterMessage wxRouterMessage, String xml) {
        //路由Event字段
        switch (wxRouterMessage.getEvent()){
            //关注事件
            case WxConstants.TYPE_EVENT_SUBSCRIBE:
                //普通关注事件
                if (wxRouterMessage.getTicket() == null){
                    return buildRouterResult(WxConstants.ROUTER_EVENT_SUBSCRIBE, XStreamHandler.toBean(xml, WxSubscribeEventMessage.class));
                }else{          //扫描微信二维码关注
                    return buildRouterResult(WxConstants.ROUTER_EVENT_QRCODE_SUBSCRIBE, XStreamHandler.toBean(xml, WxQrcodeSubscribeEventMessage.class));
                }
            //取消关注
            case WxConstants.TYPE_EVENT_UNSUBSCRIBE:
                return buildRouterResult(WxConstants.ROUTER_EVENT_UNSUBSCRIBE, XStreamHandler.toBean(xml, WxUnSubscribeEventMessage.class));
            //扫描二维码，用户已关注时的事件推送
            case WxConstants.TYPE_EVENT_SCAN:
                return buildRouterResult(WxConstants.ROUTER_EVENT_SCAN, XStreamHandler.toBean(xml, WxQrcodeScanEventMessage.class));
             //上报地理位置
            case WxConstants.TYPE_EVENT_LOCATION:
                return buildRouterResult(WxConstants.ROUTER_EVENT_LOCATION, XStreamHandler.toBean(xml, WxLocationEventMessage.class));
             //自定义菜单事件
            case WxConstants.TYPE_EVENT_CLICK:
                return buildRouterResult(WxConstants.ROUTER_EVENT_CLICK, XStreamHandler.toBean(xml, WxMenuClientEventMessage.class));
            //点击菜单跳转链接时的事件推送
            case WxConstants.TYPE_EVENT_VIEW:
                return buildRouterResult(WxConstants.ROUTER_EVENT_VIEW, XStreamHandler.toBean(xml, WxMenuPushEventMessage.class));
            //默认普通关注事件
            default:
                return buildRouterResult(WxConstants.ROUTER_EVENT_SUBSCRIBE, XStreamHandler.toBean(xml, WxSubscribeEventMessage.class));
        }
    }

    /**
     * 构建微信路由结果，业务层要根据routerType的信息将wxBaseReceiveMessage强转为具体的JavaBean
     * @param routerType 微信路由类型，SDK具体路由规则自定义
     * @param wxBaseReceiveMessage 微信路由实际结果类，
     * @return 微信路由结果
     */
    private static WxReceiveRouterResult buildRouterResult(String routerType, WxBaseReceiveMessage wxBaseReceiveMessage){
        return new WxReceiveRouterResult(routerType, wxBaseReceiveMessage);
    }
}
