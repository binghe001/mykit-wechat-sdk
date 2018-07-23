package io.mykit.wechat.mp.beans.xml.receive.text;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 13:37
 * @Description: 微信文本消息
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxReceiveTextMessage extends WxBaseReceiveMessage {
    private static final long serialVersionUID = -7346355738576226082L;

    @XStreamAlias("Content")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String content;

    @XStreamAlias("MsgId")
    private Long msgId;

    @Override
    public String toString(){
        return toJsonString(this);
    }
}
