package io.mykit.wechat.mp.beans.json.qrcode;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 13:27
 * @Description: 二维码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxQrcodeSence extends BaseJsonBean {
    private static final long serialVersionUID = -8686808941201558254L;

    private WxQrcodeSenceId scene;
}
