package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.msg.upstream.week;

import io.mykit.wechat.mp.beans.json.analysis.news.resp.article.msg.upstream.WxMsgUpstreamAnalysisResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 13:44
 * @description 获取消息发送周数据
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxMsgUpstreamWeekAnalysisResp extends WxMsgUpstreamAnalysisResp {
    private static final long serialVersionUID = -5911425853664458444L;
}
