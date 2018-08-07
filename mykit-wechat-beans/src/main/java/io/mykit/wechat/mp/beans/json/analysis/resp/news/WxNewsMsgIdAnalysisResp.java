package io.mykit.wechat.mp.beans.json.analysis.resp.news;

import io.mykit.wechat.mp.beans.json.analysis.resp.user.WxUserBaseAnalysisResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2018/8/6 09:53
 * @description 获取微信统计结果带有MsgID的基础类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxNewsMsgIdAnalysisResp extends WxUserBaseAnalysisResp {
    private static final long serialVersionUID = -5849116075134175110L;
    //请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
    private String msgid;
    //图文消息的标题
    private String title;
}
