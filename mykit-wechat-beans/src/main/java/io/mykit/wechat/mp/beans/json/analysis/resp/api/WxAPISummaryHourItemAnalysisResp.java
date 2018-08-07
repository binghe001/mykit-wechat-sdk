package io.mykit.wechat.mp.beans.json.analysis.resp.api;

import io.mykit.wechat.mp.beans.json.analysis.resp.news.WxNewsBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/7 15:34
 * @description 获取接口分析分时数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxAPISummaryHourItemAnalysisResp extends WxAPISummaryItemAnalysisResp {

    private static final long serialVersionUID = -7917759819150591430L;
    //数据的小时
    private Integer ref_hour;
}
