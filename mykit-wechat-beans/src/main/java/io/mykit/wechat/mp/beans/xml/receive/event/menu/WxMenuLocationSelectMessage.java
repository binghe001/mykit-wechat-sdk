package io.mykit.wechat.mp.beans.xml.receive.event.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.receive.event.base.WxBaseReceiveEventMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 12:21
 * @Description: 弹出地理位置选择器的事件推送
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMenuLocationSelectMessage extends WxBaseReceiveEventMessage {
    private static final long serialVersionUID = -1753225169804241448L;

    @XStreamAlias("EventKey")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String eventKey;

    @XStreamAlias("SendLocationInfo")
    private WxMenuLocationSelectItemMessage sendLocationInfo;
}
