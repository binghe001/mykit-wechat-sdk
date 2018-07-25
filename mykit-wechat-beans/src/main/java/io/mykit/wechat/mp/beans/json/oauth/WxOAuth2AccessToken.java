package io.mykit.wechat.mp.beans.json.oauth;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 14:50
 * @Description: 通过code换取网页授权access_token返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxOAuth2AccessToken extends WxCode {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
