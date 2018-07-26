package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 18:06
 * @Description: 微信用户标签
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserTag extends BaseJsonBean {
    private static final long serialVersionUID = 7744011799799363940L;

    private WxUserTagName tag;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
