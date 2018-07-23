package io.mykit.wechat.mp.beans.json.mass.preview.image;

import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewMediaId;
import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 14:14
 * @Description: 预览图片（其中media_id与根据分组群发中的media_id相同）：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassPreviewImageMessage extends WxMassPreviewToUser {
    private static final long serialVersionUID = -8534132804631966655L;
    private WxMassPreviewMediaId image;
    private String msgtype = "image";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
