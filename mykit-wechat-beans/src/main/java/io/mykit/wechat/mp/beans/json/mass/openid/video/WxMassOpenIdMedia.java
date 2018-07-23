package io.mykit.wechat.mp.beans.json.mass.openid.video;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 12:45
 * @Description: 视频的微信多媒体封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdMedia extends WxMassOpenIdMediaId {
    private static final long serialVersionUID = 2429427167064400749L;
    private String title;
    private String description;
}
