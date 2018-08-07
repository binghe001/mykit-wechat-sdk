package io.mykit.wechat.mp.beans.json.analysis.news.resp.msg.upstream.dist;

import io.mykit.wechat.mp.beans.json.analysis.news.resp.WxNewsBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/7 15:01
 * @description 获取消息发送分布数据接口条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMsgUpstreamDistItemAnalysisResp extends WxNewsBaseAnalysisResp {
    private static final long serialVersionUID = 3404900873286393705L;
    //当日发送消息量分布的区间，0代表 “0”，1代表“1-5”，2代表“6-10”，3代表“10次以上”
    private Integer count_interval;
    //上行发送了（向公众号发送了）消息的用户数
    private Integer msg_user;
}
