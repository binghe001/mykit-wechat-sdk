package io.mykit.wechat.mp.beans.json.token;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 12:45
 * @Description: 微信的AccessToken
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxAccessToken extends BaseJsonBean {
    private static final long serialVersionUID = -2954185406720903761L;
    //解析的Access_token
    private String access_token;

    //解析的过期时间
    private int expires_in;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
