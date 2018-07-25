package io.mykit.wechat.mp.beans.json.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 16:18
 * @Description: 网页授权获取用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxOAuth2GetUser extends WxOAuth2Auth {
    private static final long serialVersionUID = 9132991969213671329L;
    //返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
    private String lang = "zh_CN";

    @Override
    public Map<String, Object> toMap() {
        return super.toMap(this);
    }
}
