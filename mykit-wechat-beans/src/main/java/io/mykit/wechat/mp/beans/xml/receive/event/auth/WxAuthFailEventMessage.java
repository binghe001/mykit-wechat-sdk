package io.mykit.wechat.mp.beans.xml.receive.event.auth;

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
 * @Date: 2018/7/27 15:47
 * @Description: 资质认证失败 / 名称认证失败（这时虽然客户端不打勾，但仍有接口权限）
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxAuthFailEventMessage extends WxBaseReceiveEventMessage {

    private static final long serialVersionUID = 5838711228563157399L;
    //失败发生时间 (整形)，时间戳
    @XStreamAlias("FailTime")
    private Long failTime;

    @XStreamAlias("FailReason")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String failReason;
}
