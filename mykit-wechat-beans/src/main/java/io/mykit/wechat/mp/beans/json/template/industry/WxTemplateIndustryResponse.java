package io.mykit.wechat.mp.beans.json.template.industry;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.template.industry.WxTemplateIndustryItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 09:59
 * @Description: 获取设置的行业信息返回数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateIndustryResponse extends WxCode {
    private static final long serialVersionUID = 2050906079508392483L;

    private WxTemplateIndustryItemResponse primary_industry;

    private WxTemplateIndustryItemResponse secondary_industry;
}
