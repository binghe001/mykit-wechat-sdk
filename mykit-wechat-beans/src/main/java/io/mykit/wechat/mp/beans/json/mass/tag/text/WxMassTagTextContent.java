package io.mykit.wechat.mp.beans.json.mass.tag.text;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 10:42
 * @Description: 文本内容
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxMassTagTextContent extends BaseJsonBean {
    private static final long serialVersionUID = -1014595275249579161L;
    private String content;
}
