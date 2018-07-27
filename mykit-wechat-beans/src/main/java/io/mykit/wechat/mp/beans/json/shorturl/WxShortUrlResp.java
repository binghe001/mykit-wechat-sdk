package io.mykit.wechat.mp.beans.json.shorturl;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 15:30
 * @Description: 生成微信短连接响应的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxShortUrlResp extends WxCode {
    private static final long serialVersionUID = 6181777295682541665L;
    //转化后的短连接
    private String short_url;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
