package io.mykit.wechat.mp.beans.json.mass.openid.news;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 09:44
 * @Description: 根据OpenId群发消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdNewsMassage extends WxMassOpenIdToUser {
    private static final long serialVersionUID = 8577817389399816101L;
    private WxMassOpenIdMediaId mpnews;
    private String msgtype = "mpnews";
    //图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
    private Integer send_ignore_reprint = 0;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
