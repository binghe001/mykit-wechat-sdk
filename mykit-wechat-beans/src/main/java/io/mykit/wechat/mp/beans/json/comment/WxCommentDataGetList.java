package io.mykit.wechat.mp.beans.json.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 17:14
 * @Description: 查看指定文章的评论数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxCommentDataGetList extends WxCommentData {
    private static final long serialVersionUID = -3279175633391774837L;
    //起始位置
    private Integer begin;
    //获取数目（>=50会被拒绝）
    private Integer count;
    //type=0 普通评论&精选评论 type=1 普通评论 type=2 精选评论
    private Integer type;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
