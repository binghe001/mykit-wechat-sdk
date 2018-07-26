package io.mykit.wechat.mp.beans.json.media.news;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 11:00
 * @Description: 新增永久素材
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMediaNewsAdd  extends BaseJsonBean {
    private static final long serialVersionUID = -3843998711713184044L;

    private List<WxMediaNewsAddItem> articles;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
