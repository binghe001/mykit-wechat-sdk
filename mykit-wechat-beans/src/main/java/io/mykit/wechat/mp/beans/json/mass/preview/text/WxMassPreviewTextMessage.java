package io.mykit.wechat.mp.beans.json.mass.preview.text;

import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 14:07
 * @Description: 预览文本消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassPreviewTextMessage extends WxMassPreviewToUser {
    private static final long serialVersionUID = 1719042384066893332L;
    private WxMassPreviewContentMessage text;
    private String msgtype = "text";

    @Override
    public String toString() {
        return super.toString(this);
    }
}
