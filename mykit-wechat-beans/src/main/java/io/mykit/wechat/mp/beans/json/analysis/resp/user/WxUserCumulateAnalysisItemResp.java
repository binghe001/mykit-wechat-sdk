package io.mykit.wechat.mp.beans.json.analysis.resp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/8/1 15:06
 * @Description: 获取累计用户数据返回的条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserCumulateAnalysisItemResp extends  WxUserBaseAnalysisResp{
    private static final long serialVersionUID = 315576649390801144L;
    //总用户量
    private Integer cumulate_user;
}
