package io.mykit.wechat.mp.beans.json.template;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 09:51
 * @Description: 微信模板Id
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateShortId extends BaseJsonBean {
    private static final long serialVersionUID = 6580132554742967614L;
    //模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
    private String template_id_short;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
