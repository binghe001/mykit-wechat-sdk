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
 * @Date: 2018/7/19 11:52
 * @Description: 弹出拍照或者相册发图的事件推送
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMenuPicPhotoOrAlbumMessage extends WxBaseReceiveEventMessage {

    private static final long serialVersionUID = -7902197926910124749L;

    @XStreamAlias("EventKey")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String eventKey;

    @XStreamAlias("SendPicsInfo")
    private WxMenuPicSysPhotoInfoMessage sendPicsInfo;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
