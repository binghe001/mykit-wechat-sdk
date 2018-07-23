package io.mykit.wechat.mp.beans.json.mass.openid.image;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 12:40
 * @Description: 群发微信图片
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdImageMessage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = 7342743968370851024L;

    private WxMassOpenIdMediaId image;
    private String msgtype = "image";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
