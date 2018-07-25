package io.mykit.wechat.mp.beans.json.template.subscribe;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.template.WxTemplateDataItemSend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 12:28
 * @Description: 内容
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateContentSubscribe extends BaseJsonBean {
    private static final long serialVersionUID = 2613266155354042128L;
    private WxTemplateDataItemSend content;
}
