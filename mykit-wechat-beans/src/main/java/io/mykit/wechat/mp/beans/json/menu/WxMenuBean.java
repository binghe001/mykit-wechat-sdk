package io.mykit.wechat.mp.beans.json.menu;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 12:51
 * @Description: 微信的菜单信息
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WxMenuBean extends BaseJsonBean {
    private static final long serialVersionUID = -7579267534455759647L;

    private String menuid;

}
