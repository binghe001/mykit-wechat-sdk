package io.mykit.wechat.mp.beans.json.analysis.resp.news.msg.upstream.dist;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/7 14:59
 * @description 获取消息发送分布数据接口
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMsgUpstreamDistAnalysisResp extends WxCode {
    private static final long serialVersionUID = 2206085642349271938L;

    private List<WxMsgUpstreamDistItemAnalysisResp> list;
}
