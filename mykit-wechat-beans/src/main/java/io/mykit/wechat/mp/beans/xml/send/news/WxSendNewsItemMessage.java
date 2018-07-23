package io.mykit.wechat.mp.beans.xml.send.news;

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
 * @Date: 2018/7/18 20:07
 * @Description:
 */
@XStreamAlias("item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxSendNewsItemMessage implements Serializable {
    private static final long serialVersionUID = 8711814812260655055L;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

    @XStreamAlias("PicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picUrl;

    @XStreamAlias("Url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String url;
}
