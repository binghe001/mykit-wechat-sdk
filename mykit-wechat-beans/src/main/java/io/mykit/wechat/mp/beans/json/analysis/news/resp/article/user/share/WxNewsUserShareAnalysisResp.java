package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.user.share;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:25
 * @description 获取图文分享转发数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserShareAnalysisResp extends WxCode {
    private static final long serialVersionUID = 5841606037354301929L;
    private List<WxNewsUserShareItemAnalysisResp> list;
}
