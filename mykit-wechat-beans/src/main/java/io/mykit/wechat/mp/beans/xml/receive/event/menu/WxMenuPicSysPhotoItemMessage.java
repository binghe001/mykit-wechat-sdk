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
 * @Date: 2018/7/18 22:26
 * @Description:
 */
@XStreamAlias("item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMenuPicSysPhotoItemMessage implements Serializable {

    @XStreamAlias("PicMd5Sum")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picMd5Sum;
}
