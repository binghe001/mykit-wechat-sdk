package io.mykit.wechat.mp.beans.xml.receive.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.base.WxBaseMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 11:14
 * @Description: 公众号接收消息的基础类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxBaseReceiveMessage extends WxBaseMessage {
    private static final long serialVersionUID = 7096703420709872400L;
    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String msgType;

}
