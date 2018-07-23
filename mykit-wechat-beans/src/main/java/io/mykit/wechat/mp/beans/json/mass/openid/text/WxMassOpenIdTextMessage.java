package io.mykit.wechat.mp.beans.json.mass.openid.text;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 10:33
 * @Description: 根据openId 发送文本消息的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdTextMessage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = -6961278727318084027L;
    private String msgtype = "text";
    private WxMassOpenIdContentMessage text;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
