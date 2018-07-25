package io.mykit.wechat.mp.beans.json.template.industry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 10:01
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateIndustryItemResponse implements Serializable {
    private static final long serialVersionUID = 4392744875902297878L;
    private String first_class;
    private String second_class;
}
