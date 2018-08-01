package io.mykit.wechat.mp.beans.json.analysis.user.req;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/8/1 15:12
 * @Description: 用户分析接口参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserAnalysisDate extends BaseJsonBean {
    private static final long serialVersionUID = -6453049694574983101L;
    //获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
    private String begin_date;
    //获取数据的结束日期，end_date允许设置的最大值为昨日
    private String end_date;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
