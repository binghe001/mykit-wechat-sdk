package io.mykit.wechat.mp.beans.json.user.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 10:18
 * @Description: 批量为用户打/取消标签
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxUserTagMember extends WxUserTagId {
    private static final long serialVersionUID = -9066272691031516551L;

    private List<String> openid_list;
}
