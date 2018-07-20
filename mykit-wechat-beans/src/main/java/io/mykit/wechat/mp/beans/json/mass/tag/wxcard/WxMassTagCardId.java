package io.mykit.wechat.mp.beans.json.mass.tag.wxcard;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 13:39
 * @Description: 微信卡券信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagCardId extends BaseJsonBean {
    private static final long serialVersionUID = 2536366285597788463L;
    private String card_id;
}
