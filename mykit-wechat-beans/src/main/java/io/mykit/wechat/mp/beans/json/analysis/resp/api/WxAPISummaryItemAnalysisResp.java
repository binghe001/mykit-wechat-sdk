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
 * @description 获取接口分析数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxAPISummaryItemAnalysisResp extends WxNewsBaseAnalysisResp {
    private static final long serialVersionUID = 2468618268372157348L;
    //通过服务器配置地址获得消息后，被动回复用户消息的次数
    private Integer callback_count;
    //上述动作的失败次数
    private Integer fail_count;
    //总耗时，除以callback_count即为平均耗时
    private Integer total_time_cost;
    //最大耗时
    private Integer max_time_cost;
}
