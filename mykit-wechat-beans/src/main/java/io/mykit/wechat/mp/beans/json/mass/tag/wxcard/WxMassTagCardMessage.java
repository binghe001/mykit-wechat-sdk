package io.mykit.wechat.mp.beans.json.mass.tag.wxcard;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 13:41
 * @Description: 群发卡券消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagCardMessage extends BaseJsonBean {
    private static final long serialVersionUID = 2571829516348374902L;
    private WxMassTagFilter filter;
    private WxMassTagCardId wxcard;
    private String msgtype = "wxcard";
}
