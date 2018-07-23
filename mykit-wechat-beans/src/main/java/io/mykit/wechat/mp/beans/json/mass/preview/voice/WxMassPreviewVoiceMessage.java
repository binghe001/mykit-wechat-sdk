package io.mykit.wechat.mp.beans.json.mass.preview.voice;

import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewMediaId;
import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 14:10
 * @Description: 预览语音消息（其中media_id与根据分组群发中的media_id相同）：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassPreviewVoiceMessage extends WxMassPreviewToUser {
    private static final long serialVersionUID = -2796709497695786251L;
    private WxMassPreviewMediaId voice;
    private String msgtype = "voice";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
