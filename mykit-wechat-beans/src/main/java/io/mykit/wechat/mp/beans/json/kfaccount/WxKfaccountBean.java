package io.mykit.wechat.mp.beans.json.kfaccount;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 15:51
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxKfaccountBean extends BaseJsonBean {
    private static final long serialVersionUID = 2897911121683729895L;

    private String kf_account;
    private String nickname;
    private String password;

    @Override
    public String toString() {
        return super.toString(this);
    }
}
