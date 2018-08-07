package io.mykit.wechat.mp.beans.json.analysis.resp.news.article.total;

import io.mykit.wechat.mp.beans.json.analysis.resp.news.WxNewsMsgIdAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 10:30
 * @description 获取图文群发总数据详情
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsArticleTotalDetailsAnalysisResp extends WxNewsMsgIdAnalysisResp {
    private static final long serialVersionUID = -3874672488825117412L;

    private List<WxNewsArticleTotalDetailAnalysisResp> details;
}
