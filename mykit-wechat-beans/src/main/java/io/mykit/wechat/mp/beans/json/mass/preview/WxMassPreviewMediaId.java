package io.mykit.wechat.mp.beans.json.mass.preview;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 13:54
 * @Description: 微信多媒体Id
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassPreviewMediaId extends BaseJsonBean {
    private static final long serialVersionUID = 879969248654091388L;

    private String media_id;
}
