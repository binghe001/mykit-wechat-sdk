package io.mykit.wechat.mp.beans.xml.receive.event.template;

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
 * @Date: 2018/7/25 11:39
 * @Description: 发送模板消息后微信推送的XML数据
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateEventMessage extends WxBaseReceiveEventMessage {
    private static final long serialVersionUID = 337278476453581332L;
    @XStreamAlias("MsgID")
    private Long msgId;
    //成功为：<Status>< ![CDATA[success] ]></Status>
    @XStreamAlias("Status")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String status;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
