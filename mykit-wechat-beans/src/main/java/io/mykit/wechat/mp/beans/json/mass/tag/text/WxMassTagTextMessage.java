package io.mykit.wechat.mp.beans.json.mass.tag.text;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 10:41
 * @Description: 文本消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagTextMessage extends BaseJsonBean {
    private static final long serialVersionUID = 3725621162310756980L;
    private WxMassTagFilter filter;
    private WxMassTagTextContent text;
    private String msgtype = "text";
    @Override
    public String toString() {
        return super.toString(this);
    }
}
