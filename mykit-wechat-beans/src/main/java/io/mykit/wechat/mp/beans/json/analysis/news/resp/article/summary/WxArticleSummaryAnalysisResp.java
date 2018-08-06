package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.summary;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 09:56
 * @description 获取图文群发每日数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxArticleSummaryAnalysisResp extends WxCode {
    private static final long serialVersionUID = -4173165803281880668L;

    private List<WxArticleSummaryItemAnalysisResp> list;

}
