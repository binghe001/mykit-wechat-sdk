package io.mykit.wechat.mp.beans.json.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 17:40
 * @Description: 回复评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxCommentContent extends WxCommentId{
    private static final long serialVersionUID = -6749161813420346470L;

    private String content;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
