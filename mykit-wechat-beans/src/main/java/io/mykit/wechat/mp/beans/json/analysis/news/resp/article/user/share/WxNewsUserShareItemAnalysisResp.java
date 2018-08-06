package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.user.share;

import io.mykit.wechat.mp.beans.json.analysis.news.resp.WxNewsBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:23
 * @description 获取图文分享转发数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserShareItemAnalysisResp extends WxNewsBaseAnalysisResp {
    private static final long serialVersionUID = 5841606037354301929L;
    //分享的场景 1代表好友转发 2代表朋友圈 3代表腾讯微博 255代表其他
    private Integer share_scene;
    //分享的次数
    private Integer share_count;
    //分享的人数
    private Integer share_user;
}
