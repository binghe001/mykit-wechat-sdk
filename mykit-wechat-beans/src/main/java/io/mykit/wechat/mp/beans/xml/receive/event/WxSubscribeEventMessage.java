package io.mykit.wechat.mp.beans.xml.receive.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import io.mykit.wechat.mp.beans.xml.receive.event.base.WxBaseReceiveEventMessage;
import lombok.Data;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 19:13
 * @Description: 微信关注/取消关注事件推送的XML
 */
@XStreamAlias("xml")
@Data
public class WxSubscribeEventMessage extends WxBaseReceiveEventMessage {
    private static final long serialVersionUID = 2108639063874991944L;

}
