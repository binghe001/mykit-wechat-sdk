package io.mykit.wechat.mp.beans.xml.receive.event.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 22:13
 * @Description: scancode_push：扫码推事件的事件推送条目
 */
@XStreamAlias("ScanCodeInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMenuScanCodeItemEventMessage implements Serializable {

    private static final long serialVersionUID = -2908270646115392867L;

    @XStreamAlias("ScanType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String scanType;

    @XStreamAlias("ScanResult")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String scanResult;
}
