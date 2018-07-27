package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.code.WxCode;
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
public class WxUserTagGetListResp extends WxCode {

    private static final long serialVersionUID = 9116270409629674550L;

    private Integer count;

    private String next_openid;

    private  WxUserTagGetListItemResp data;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
