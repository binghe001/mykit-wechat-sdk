package io.mykit.wechat.mp.beans.xml.receive.event.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 11:22
 * @Description: 微信接收事件基础类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxBaseReceiveEventMessage extends WxBaseReceiveMessage {
    private static final long serialVersionUID = -4727976028597439732L;

    @XStreamAlias("Event")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String event;

}
