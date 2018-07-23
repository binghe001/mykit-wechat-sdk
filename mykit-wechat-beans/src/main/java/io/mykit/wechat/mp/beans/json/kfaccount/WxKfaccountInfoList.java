package io.mykit.wechat.mp.beans.json.kfaccount;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 17:23
 * @Description: 获取客服信息时返回的微信数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxKfaccountInfoList extends BaseJsonBean {
    private static final long serialVersionUID = -5988026383074143696L;
    private List<WxKfaccountInfo> kf_list;
}
