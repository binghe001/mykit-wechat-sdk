package io.mykit.wechat.mp.beans.json.analysis.resp.news.msg.upstream.hour;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 13:33
 * @description 获取消息分送分时数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMsgUpstreamHourAnalysisResp extends WxCode {
    private List<WxMsgUpstreamHourItemAnalysisResp> list;
}
