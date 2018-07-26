package io.mykit.wechat.mp.beans.json.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 17:21
 * @Description: 将评论标记精选
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxCommentId extends WxCommentData {
    private static final long serialVersionUID = -8122236339497216152L;
    private Integer user_comment_id;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
