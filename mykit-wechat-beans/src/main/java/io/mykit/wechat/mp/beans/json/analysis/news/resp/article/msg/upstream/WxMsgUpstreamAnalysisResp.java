package io.mykit.wechat.mp.beans.json.analysis.news.resp.article.msg.upstream;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:56
 * @description 获取消息发送概况数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMsgUpstreamAnalysisResp extends WxCode {
    private static final long serialVersionUID = -8978779984866252636L;

    private List<WxMsgUpstreamItemAnalysisResp> list;
}
