package io.mykit.wechat.mp.beans.json.kfaccount;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 17:21
 * @Description: 获取微信客服信息返回的具体条目数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxKfaccountInfo extends BaseJsonBean {

    private static final long serialVersionUID = -5304618220704986216L;
    private String kf_account;
    private String kf_nick;
    private String kf_id;
    private String kf_headimgurl;
}
