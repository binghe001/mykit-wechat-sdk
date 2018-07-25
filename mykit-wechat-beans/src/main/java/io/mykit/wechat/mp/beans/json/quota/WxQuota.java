package io.mykit.wechat.mp.beans.json.quota;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 13:34
 * @Description: 微信API调用次数清0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxQuota extends BaseJsonBean {
    private static final long serialVersionUID = 1014205090723458831L;
    private String appid;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
