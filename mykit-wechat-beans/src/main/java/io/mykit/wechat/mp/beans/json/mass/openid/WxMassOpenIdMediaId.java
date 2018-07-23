package io.mykit.wechat.mp.beans.json.mass.openid;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 10:20
 * @Description: 存储MediaId
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdMediaId extends BaseJsonBean {
    private static final long serialVersionUID = 6433616473353063645L;
    //用于群发的消息的media_id
    private String media_id;
}
