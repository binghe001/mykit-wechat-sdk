package io.mykit.wechat.mp.beans.xml.receive.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import io.mykit.wechat.mp.beans.xml.receive.event.base.WxBaseReceiveEventMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 19:30
 * @Description: 上报地理位置信息
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxLocationEventMessage extends WxBaseReceiveEventMessage {
    private static final long serialVersionUID = -5956367691437971176L;

    @XStreamAlias("Latitude")
    private Double latitude;

    @XStreamAlias("Longitude")
    private Double longitude;

    @XStreamAlias("Precision")
    private Double precision;
}
