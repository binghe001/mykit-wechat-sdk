package io.mykit.wechat.mp.beans.json.analysis.resp.news.article.user.share.hour;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:35
 * @description 获取图文分享转发分时数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsUserShareHourAnalysisResp extends WxCode {
    private static final long serialVersionUID = -2430047659167149460L;

    private List<WxNewsUserShareHourItemAnalysisResp> list;
}
