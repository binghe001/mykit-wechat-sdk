package io.mykit.wechat.mp.beans.json.mass.openid.video;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 12:44
 * @Description: 根据openId群发视频消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdVideoMessage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = 3004096179305491849L;

    private WxMassOpenIdMedia mpvideo;
    private String msgtype = "mpvideo";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
