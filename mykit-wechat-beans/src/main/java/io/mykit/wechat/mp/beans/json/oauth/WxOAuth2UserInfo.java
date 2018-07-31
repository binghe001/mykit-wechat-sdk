package io.mykit.wechat.mp.beans.json.oauth;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 16:14
 * @Description: 通过微信网页授权获取的用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxOAuth2UserInfo extends WxCode {
    private static final long serialVersionUID = 3647683783356746919L;
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private List<String> privilege;
    private String unionid;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
