package io.mykit.wechat.mp.beans.xml.receive.event.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 22:25
 * @Description:
 */
@XStreamAlias("SendPicsInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMenuPicSysPhotoInfoMessage implements Serializable {
    private static final long serialVersionUID = -7753856734346170701L;

    @XStreamAlias("Count")
    private Integer count;

    @XStreamAlias("PicList")
    private List<WxMenuPicSysPhotoItemMessage> picList;
}
