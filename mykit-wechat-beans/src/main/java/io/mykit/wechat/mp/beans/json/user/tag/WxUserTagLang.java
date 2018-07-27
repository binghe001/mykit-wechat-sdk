package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
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
public class WxUserTagLang extends BaseJsonBean {
    private static final long serialVersionUID = -8550511163861932436L;
    //返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
    private String lang;
    private String openid;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }

    @Override
    public Map<String, Object> toMap() {
        return super.toMap(this);
    }
}
