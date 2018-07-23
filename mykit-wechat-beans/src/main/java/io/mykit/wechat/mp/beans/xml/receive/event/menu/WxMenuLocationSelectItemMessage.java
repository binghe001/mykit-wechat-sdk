package io.mykit.wechat.mp.beans.xml.receive.event.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 12:23
 * @Description:
 */
@XStreamAlias("SendLocationInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMenuLocationSelectItemMessage implements Serializable {
    private static final long serialVersionUID = 6866480442955635045L;

    @XStreamAlias("Location_X")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String location_X;

    @XStreamAlias("Location_Y")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String location_Y;

    @XStreamAlias("Scale")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String scale;

    @XStreamAlias("Label")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String label;

    @XStreamAlias("Poiname")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String poiname;
}
