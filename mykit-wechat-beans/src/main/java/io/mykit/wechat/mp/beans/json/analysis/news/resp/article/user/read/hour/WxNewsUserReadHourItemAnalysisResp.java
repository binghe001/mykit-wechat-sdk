package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.user.read.hour;

import io.mykit.wechat.mp.beans.json.analysis.news.resp.WxNewsBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:10
 * @description 获取图文统计分时数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserReadHourItemAnalysisResp extends WxNewsBaseAnalysisResp {

    private static final long serialVersionUID = -2679215790665477099L;
    //数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
    private Integer ref_hour;
    //在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他;6.看一看;7.搜一搜
    private Integer user_source;
    //图文页（点击群发图文卡片进入的页面）的阅读人数
    private Integer int_page_read_user;
    //图文页的阅读次数
    private Integer int_page_read_count;
    //原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
    private Integer ori_page_read_user;
    //原文页的阅读次数
    private Integer ori_page_read_count;
    //	分享的人数
    private Integer share_user;
    //	分享的次数
    private Integer share_count;
    //收藏的人数
    private Integer add_to_fav_user;
    //收藏的次数
    private Integer add_to_fav_count;
}
