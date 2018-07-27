package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 10:26
 * @Description: OpenId
 * {   "openid" : "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y" }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserTagOpenId extends WxCode {
    private static final long serialVersionUID = 1328238384534452850L;
    private String openid;
    private String next_openid;
    private String begin_openid;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }

    @Override
    public Map<String, Object> toMap() {
        return super.toMap(this);
    }
}
