package io.mykit.wechat.mp.beans.json.user.tag;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 10:26
 * @Description: 获取用户基本信息（包括UnionID机制）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUseInfo extends WxUserTagOpenId {
    private static final long serialVersionUID = 1029388381541408802L;
    //户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    private Integer subscribe;
    //用户的昵称
    private String nickname;
    //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private Integer sex;
    //用户的语言，简体中文为zh_CN
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Long subscribe_time;
    private String unionid;
    private String remark;
    private Integer groupid;
    private List<Integer> tagid_list;
    //返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
    private String subscribe_scene;
    //二维码扫码场景（开发者自定义）
    private Integer qr_scene;
    //	二维码扫码场景描述（开发者自定义）
    private String qr_scene_str;
    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
