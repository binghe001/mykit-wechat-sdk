package io.mykit.wechat.mp.beans.json.template.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 10:39
 * @Description: 发送模板消息附带的小程序信息，格式如下：
 * {
 *     "appid":"xiaochengxuappid12345",
 *      "pagepath":"index?foo=bar"
 * },
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateMiniprogramSend implements Serializable {
    private static final long serialVersionUID = 8024951511611329554L;
    private String appid;
    private String pagepath;
}
