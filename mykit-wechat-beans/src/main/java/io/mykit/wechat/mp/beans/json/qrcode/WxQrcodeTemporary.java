package io.mykit.wechat.mp.beans.json.qrcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 13:34
 * @Description: 临时二维码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxQrcodeTemporary extends WxQrcodeForever{
    private static final long serialVersionUID = -2251413494458356434L;
    //该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
    private Integer expire_seconds;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
