package io.mykit.wechat.mp.beans.xml.send.news;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import io.mykit.wechat.mp.beans.xml.send.base.WxBaseSendMessage;
import io.mykit.wechat.utils.xml.XStreamCDataConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 19:58
 * @Description: 回复图文消息
 */
@XStreamAlias("xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxSendNewsMessage extends WxBaseSendMessage {
    private static final long serialVersionUID = -958376690542758418L;

    @XStreamAlias("MsgType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String msgType = "news";

    @XStreamAlias("ArticleCount")
    private Integer articleCount;

    @XStreamAlias("Articles")
    private List<WxSendNewsItemMessage> articles;
}
