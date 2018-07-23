package io.mykit.wechat.mp.beans.json.mass.openid.voice;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 12:35
 * @Description: 群发微信语音消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdVoiceMessage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = -1761531442293831460L;
    private WxMassOpenIdMediaId voice;
    private String msgtype = "voice";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
