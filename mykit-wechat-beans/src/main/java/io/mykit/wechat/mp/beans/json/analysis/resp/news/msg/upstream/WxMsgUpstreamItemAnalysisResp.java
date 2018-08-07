package io.mykit.wechat.mp.beans.json.analysis.resp.news.msg.upstream;

import io.mykit.wechat.mp.beans.json.analysis.resp.news.WxNewsBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 11:51
 * @description 获取消息发送概况数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMsgUpstreamItemAnalysisResp extends WxNewsBaseAnalysisResp {
    private static final long serialVersionUID = 7693156791618971476L;
    //消息类型，代表含义如下： 1代表文字 2代表图片 3代表语音 4代表视频 6代表第三方应用消息（链接消息）
    private Integer msg_type;
    //上行发送了（向公众号发送了）消息的用户数
    private Integer msg_user;
    //上行发送了消息的消息总数
    private Integer msg_count;
}
