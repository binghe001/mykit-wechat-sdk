package io.mykit.wechat.mp.beans.json.comment;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 16:36
 * @Description: 评论相关的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxCommentData extends BaseJsonBean {
    private static final long serialVersionUID = 3793492593143138779L;
    //群发返回的msg_data_id
    private Integer msg_data_id;
    //多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
    private Integer index;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
