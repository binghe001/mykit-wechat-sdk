package io.mykit.wechat.mp.beans.router.receive;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @Author: liuyazhuang
 * @Date: 2018/7/30 10:29
 * @Description: 微信路由转化的JavaBean结果,实际业务根据routerType中的类型将baseReceiveMessage强制转化为具体的JavaBean
 *
 * 具体转化规则如下:
 * routerType类型                                      对应的常量                                               WxBaseReceiveMessage强制转化的具体类型
 * router_text                                    WxConstants.ROUTER_TEXT                                              WxReceiveTextMessage
 * router_image                                   WxConstants.ROUTER_IMAGE                                             WxReceiveImageMessage
 * router_voice                                   WxConstants.ROUTER_VOICE                                             WxReceiveVoiceMessage
 * router_video                                   WxConstants.ROUTER_VIDEO                                             WxReceiveVideoMessage
 * router_shortvideo                              WxConstants.ROUTER_SHORTVIDEO                                        WxReceiveShortVideoMessage
 * router_location                                WxConstants.ROUTER_LOCATION                                          WxReceiveLocationMessage
 * router_link                                    WxConstants.ROUTER_LINK                                              WxReceiveLinkMessage
 * router_event_subscribe                         WxConstants.ROUTER_EVENT_SUBSCRIBE                                   WxSubscribeEventMessage
 * router_event_qrcode_subscribe                  WxConstants.ROUTER_EVENT_QRCODE_SUBSCRIBE                            WxQrcodeSubscribeEventMessage
 * router_event_unsubscribe                       WxConstants.ROUTER_EVENT_UNSUBSCRIBE                                 WxUnSubscribeEventMessage
 * router_event_scan                              WxConstants.ROUTER_EVENT_SCAN                                        WxQrcodeScanEventMessage
 * router_event_location                          WxConstants.ROUTER_EVENT_LOCATION                                    WxLocationEventMessage
 * router_event_click                             WxConstants.ROUTER_EVENT_CLICK                                       WxMenuClientEventMessage
 * router_event_view                              WxConstants.ROUTER_EVENT_VIEW                                        WxMenuPushEventMessage
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxReceiveRouterResult extends BaseJsonBean {
    private static final long serialVersionUID = 4429856716411569022L;

    //微信消息路由的依据类
    private String  routerType;
    //实际的路由结果Javabean
    private WxBaseReceiveMessage baseReceiveMessage;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
