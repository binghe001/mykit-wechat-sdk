package io.mykit.wechat.mp.beans.json.analysis.resp.news.article.user.share.hour;

import io.mykit.wechat.mp.beans.json.analysis.resp.news.WxNewsBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:33
 * @description 获取图文分享转发分时数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserShareHourItemAnalysisResp extends WxNewsBaseAnalysisResp {
    private static final long serialVersionUID = -5706153376391876539L;
    //数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
    private Integer ref_hour;
    //分享的场景 1代表好友转发 2代表朋友圈 3代表腾讯微博 255代表其他
    private Integer share_scene;
    //分享的次数
    private Integer share_count;
    //分享的人数
    private Integer share_user;
}
