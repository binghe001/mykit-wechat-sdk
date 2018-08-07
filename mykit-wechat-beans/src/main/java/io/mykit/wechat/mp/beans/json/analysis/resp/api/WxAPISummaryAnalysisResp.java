package io.mykit.wechat.mp.beans.json.analysis.resp.api;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/7 15:38
 * @description 获取接口分析数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxAPISummaryAnalysisResp extends WxCode {
    private static final long serialVersionUID = 4444699391453793747L;

    private List<WxAPISummaryItemAnalysisResp> list;
}
