package io.mykit.wechat.mp.beans.json.mass.tag.image;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagMediaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 11:40
 * @Description: 群发图片消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagImageMessage extends BaseJsonBean {
    private static final long serialVersionUID = 2127855513988172102L;
    private WxMassTagFilter filter;
    private WxMassTagMediaId image;
    private String msgtype = "image";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
