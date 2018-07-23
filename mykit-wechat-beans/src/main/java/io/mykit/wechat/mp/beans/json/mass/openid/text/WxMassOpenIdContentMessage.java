package io.mykit.wechat.mp.beans.json.mass.openid.text;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 11:53
 * @Description: 内容
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMassOpenIdContentMessage implements Serializable {
    private static final long serialVersionUID = 6974681925222992691L;
    private String content;
}
