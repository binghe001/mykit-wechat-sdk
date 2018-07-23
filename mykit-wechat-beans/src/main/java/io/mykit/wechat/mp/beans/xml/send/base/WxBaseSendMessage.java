package io.mykit.wechat.mp.beans.xml.send.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import io.mykit.wechat.mp.beans.xml.base.WxBaseMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 11:38
 * @Description: 微信公众号发送XML消息基础类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxBaseSendMessage  extends WxBaseMessage {
    private static final long serialVersionUID = 8125906444213311550L;

    @XStreamAlias("CreateTime")
    private Long createTime;
}
