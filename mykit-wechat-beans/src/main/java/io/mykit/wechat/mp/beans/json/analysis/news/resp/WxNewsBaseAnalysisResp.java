package io.mykit.wechat.mp.beans.json.analysis.news.resp;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 09:48
 * @description 微信获取图文消息统计结果基础类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsBaseAnalysisResp extends BaseJsonBean {
    private static final long serialVersionUID = 8060311717449513755L;

    //数据的日期，需在begin_date和end_date之间
    private String ref_date;
}
