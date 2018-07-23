package io.mykit.wechat.mp.beans.json.mass.openid;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 09:41
 * @Description: OpenId列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdToUser extends BaseJsonBean {
    private static final long serialVersionUID = 2692077710159630930L;
    private List<String> touser;
}
