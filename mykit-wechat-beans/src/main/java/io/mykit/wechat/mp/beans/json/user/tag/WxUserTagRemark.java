package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class WxUserTagRemark extends WxUserTagOpenId {
    private static final long serialVersionUID = -6288228131097872355L;
    private String remark;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
