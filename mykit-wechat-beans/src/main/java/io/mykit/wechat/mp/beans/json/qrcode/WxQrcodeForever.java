package io.mykit.wechat.mp.beans.json.qrcode;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 13:31
 * @Description: 永久二维码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxQrcodeForever extends BaseJsonBean {
    private static final long serialVersionUID = 4801926760262781654L;

    //二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
    private String action_name;
    //二维码详细信息
    private WxQrcodeSence action_info;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
