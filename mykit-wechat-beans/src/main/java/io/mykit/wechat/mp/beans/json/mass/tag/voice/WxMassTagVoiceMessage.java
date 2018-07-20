package io.mykit.wechat.mp.beans.json.mass.tag.voice;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagMediaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 11:33
 * @Description:  群发语音消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassTagVoiceMessage extends BaseJsonBean {
    private static final long serialVersionUID = -2423638373477748527L;
    private WxMassTagFilter filter;
    private WxMassTagMediaId voice;
    private String msgtype = "voice";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
