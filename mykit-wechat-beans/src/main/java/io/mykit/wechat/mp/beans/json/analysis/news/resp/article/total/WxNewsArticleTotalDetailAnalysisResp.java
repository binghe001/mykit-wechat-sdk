package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.total;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 10:15
 * @description 获取图文群发总数据详情条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsArticleTotalDetailAnalysisResp extends BaseJsonBean {
    private static final long serialVersionUID = 2355369576627817842L;
    //统计的日期，在getarticletotal接口中，ref_date指的是文章群发出日期， 而stat_date是数据统计日期
    private String stat_date;
    //送达人数，一般约等于总粉丝数（需排除黑名单或其他异常情况下无法收到消息的粉丝）
    private Integer target_user;
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
    //intpagefromsessionreaduser 公众号会话阅读人数; intpagefromsessionreadcount 公众号会话阅读次数; intpagefromhistmsgreaduser 历史消息页阅读人数; intpagefromhistmsgreadcount 历史消息页阅读次数; intpagefromfeedreaduser 朋友圈阅读人数; intpagefromfeedreadcount 朋友圈阅读次数; intpagefromfriendsreaduser 好友转发阅读人数; intpagefromfriendsreadcount 好友转发阅读次数; intpagefromotherreaduser 其他场景阅读人数; intpagefromotherreadcount 其他场景阅读次数 ;intpagefromkanyikanreaduser 看一看来源阅读人数;intpagefromkanyikanreadcount 看一看来源阅读次数;intpagefromsouyisoureaduser 搜一搜来源阅读人数;intpagefromsouyisoureadcount 搜一搜来源阅读次数;feedsharefromsessionuser 公众号会话转发朋友圈人数; feedsharefromsessioncnt 公众号会话转发朋友圈次数; feedsharefromfeeduser 朋友圈转发朋友圈人数; feedsharefromfeedcnt 朋友圈转发朋友圈次数 feedsharefromotheruser; 其他场景转发朋友圈人数 feedsharefromothercnt其他场景转发朋友圈次数
    private Integer int_page_from_session_read_user;

    private Integer int_page_from_session_read_count;

    private Integer int_page_from_hist_msg_read_user;

    private Integer int_page_from_hist_msg_read_count;

    private Integer int_page_from_feed_read_user;

    private Integer int_page_from_feed_read_count;

    private Integer int_page_from_friends_read_user;

    private Integer int_page_from_friends_read_count;

    private Integer int_page_from_other_read_user;

    private Integer int_page_from_other_read_count;

    private Integer feed_share_from_session_user;

    private Integer feed_share_from_session_cnt;

    private Integer feed_share_from_feed_user;

    private Integer feed_share_from_feed_cnt;

    private Integer feed_share_from_other_user;


}
