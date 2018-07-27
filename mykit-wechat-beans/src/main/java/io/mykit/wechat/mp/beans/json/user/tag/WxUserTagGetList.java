package io.mykit.wechat.mp.beans.json.user.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 10:03
 * @Description: 获取标签下粉丝列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserTagGetList extends WxUserTagId {
    private static final long serialVersionUID = -8431074142163752363L;
    ////第一个拉取的OPENID，不填默认从头开始拉取
    private String next_openid;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
