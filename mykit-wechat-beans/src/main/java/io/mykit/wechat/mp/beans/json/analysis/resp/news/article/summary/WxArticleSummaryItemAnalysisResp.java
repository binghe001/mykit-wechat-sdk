package io.mykit.wechat.mp.beans.json.analysis.resp.news.article.summary;

import io.mykit.wechat.mp.beans.json.analysis.resp.news.WxNewsMsgIdAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 09:57
 * @description 获取图文群发每日数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxArticleSummaryItemAnalysisResp extends WxNewsMsgIdAnalysisResp {
    private static final long serialVersionUID = 5104053239267158965L;

    //图文页（点击群发图文卡片进入的页面）的阅读人数
    private Integer int_page_read_user;

    //图文页的阅读次数
    private Integer int_page_read_count;

    //原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
    private Integer ori_page_read_user;

    //原文页的阅读次数
    private Integer ori_page_read_count;

    //分享的人数
    private Integer share_user;

    //分享的次数
    private Integer share_count;

    //收藏的人数
    private Integer add_to_fav_user;

    //收藏的次数
    private Integer add_to_fav_count;

}
