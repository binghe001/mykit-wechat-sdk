package io.mykit.wechat.mp.beans.xml.receive.event.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.receive.event.base.WxBaseReceiveEventMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 12:18
 * @Description: 弹出微信相册发图器的事件推送
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMenuPicWeixinMessage extends WxBaseReceiveEventMessage {

    private static final long serialVersionUID = -7503267041769037445L;

    @XStreamAlias("EventKey")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String eventKey;

    @XStreamAlias("SendPicsInfo")
    private WxMenuPicSysPhotoInfoMessage sendPicsInfo;

    @Override
    public String toString() {
        return super.toJsonString(this);
    }
}
