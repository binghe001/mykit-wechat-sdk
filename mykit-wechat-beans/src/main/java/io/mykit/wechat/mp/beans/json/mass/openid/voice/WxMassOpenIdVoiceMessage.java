package io.mykit.wechat.mp.beans.json.mass.openid.voice;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import io.mykit.wechat.utils.common.UUIDUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 12:35
 * @Description: 群发微信语音消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassOpenIdVoiceMessage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = -1761531442293831460L;
    private WxMassOpenIdMediaId voice;
    private String msgtype = "voice";
    //开发者侧群发msgid，长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid
    private String clientmsgid = UUIDUtils.getUUID();

    @Override
    public String toString() {
        return super.toString(this);
    }
}
