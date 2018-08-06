package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.total;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 10:42
 * @description 获取图文群发总数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsArticleTotalAnalysisResp extends WxCode {
    private static final long serialVersionUID = -14608034032845966L;

    private List<WxNewsArticleTotalDetailsAnalysisResp> list;
}
