package io.mykit.wechat.mp.beans.json.mass.preview.video;

import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewMediaId;
import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 14:18
 * @Description: 预览视频消息（其中media_id与根据分组群发中的media_id相同）：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassPreviewVideoMessage extends WxMassPreviewToUser {
    private static final long serialVersionUID = 1172510364754095775L;
    private WxMassPreviewMediaId mpvideo;
    private String msgtype = "mpvideo";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
