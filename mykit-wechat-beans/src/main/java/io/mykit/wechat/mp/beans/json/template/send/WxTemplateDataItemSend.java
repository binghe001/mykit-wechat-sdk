package io.mykit.wechat.mp.beans.json.template.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 10:41
 * @Description: 发送模板消息条目的数据
 * 格式：
 *  {
 *       "value":"恭喜你购买成功！",
 *        "color":"#173177"
 *  }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateDataItemSend implements Serializable {
    private static final long serialVersionUID = -4315360753672221280L;
    private String value;
    private String color;
}
