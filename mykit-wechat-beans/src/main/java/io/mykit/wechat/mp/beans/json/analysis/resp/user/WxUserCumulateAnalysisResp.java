package io.mykit.wechat.mp.beans.json.analysis.resp.user;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/8/1 15:08
 * @Description: 获取累计用户数据返回的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserCumulateAnalysisResp extends WxCode {
    private static final long serialVersionUID = 2853455251014388961L;
    private List<WxUserCumulateAnalysisItemResp> list;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
