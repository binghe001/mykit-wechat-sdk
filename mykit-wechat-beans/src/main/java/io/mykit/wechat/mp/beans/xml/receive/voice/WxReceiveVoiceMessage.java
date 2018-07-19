package io.mykit.wechat.mp.beans.xml.receive.voice;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 17:55
 * @Description: 接收到的语音消息
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxReceiveVoiceMessage extends WxBaseReceiveMessage {

    @XStreamAlias("MediaId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mediaId;

    @XStreamAlias("Format")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String format;

    @XStreamAlias("MsgId")
    private String msgId;
}
