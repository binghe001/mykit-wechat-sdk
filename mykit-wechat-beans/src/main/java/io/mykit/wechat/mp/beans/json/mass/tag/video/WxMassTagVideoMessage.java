package io.mykit.wechat.mp.beans.json.mass.tag.video;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagMediaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 13:32
 * @Description: 微信群发视频
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagVideoMessage extends BaseJsonBean {
    private static final long serialVersionUID = 6145180102002669322L;
    private WxMassTagFilter filter;
    private WxMassTagMediaId mpvideo;
    private String msgtype = "mpvideo";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
