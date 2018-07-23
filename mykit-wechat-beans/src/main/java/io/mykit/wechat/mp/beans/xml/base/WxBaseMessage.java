package io.mykit.wechat.mp.beans.xml.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.base.BaseBean;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 14:25
 * @Description: 微信基础XML
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxBaseMessage extends BaseBean {
    private static final long serialVersionUID = -1512495051521057688L;

    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String fromUserName;

}
