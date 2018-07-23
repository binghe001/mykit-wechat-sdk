package io.mykit.wechat.mp.beans.json.mass.openid.wxcard;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 12:56
 * @Description: 群发微信卡券
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdCardMessage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = 5379448342748629517L;
    private WxMassOpenIdCardId wxcard;
    private String msgtype = "wxcard";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
