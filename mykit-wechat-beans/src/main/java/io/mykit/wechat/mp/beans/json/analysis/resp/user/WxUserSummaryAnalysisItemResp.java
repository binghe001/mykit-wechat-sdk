package io.mykit.wechat.mp.beans.json.analysis.resp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/8/1 15:02
 * @Description: 获取用户增减数据条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserSummaryAnalysisItemResp extends WxUserBaseAnalysisResp {
    private static final long serialVersionUID = -944402317368660269L;
    //用户的渠道，数值代表的含义如下： 0代表其他合计 1代表公众号搜索 17代表名片分享 30代表扫描二维码 43代表图文页右上角菜单 51代表支付后关注（在支付完成页） 57代表图文页内公众号名称 75代表公众号文章广告 78代表朋友圈广告
    private Integer user_source;
    //新增的用户数量
    private Integer new_user;
    //取消关注的用户数量，new_user减去cancel_user即为净增用户数量
    private Integer cancel_user;
}
