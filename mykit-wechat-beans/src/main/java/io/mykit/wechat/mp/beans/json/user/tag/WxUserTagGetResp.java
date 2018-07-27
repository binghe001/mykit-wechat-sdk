package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 18:06
 * @Description: 获取标签的响应数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserTagGetResp extends WxCode {
    private static final long serialVersionUID = 7744011799799363940L;

    private List<WxUserTagName> tags;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
