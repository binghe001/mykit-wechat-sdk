package io.mykit.wechat.mp.beans.map;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 10:13
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMediaId extends BaseJsonBean {
    private static final long serialVersionUID = -8798173681687201613L;

    private String media_id;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }

    @Override
    public Map<String, Object> toMap() {
        return super.toMap(this);
    }
}
