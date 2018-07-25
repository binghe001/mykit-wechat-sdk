package io.mykit.wechat.mp.beans.json.template.send;

import io.mykit.wechat.mp.beans.json.template.WxTemplateDataItemSend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 10:45
 * @Description:  发送模板消息附带的数据，格式如下：
 *
 * {
 *                    "first": {
 *                        "value":"恭喜你购买成功！",
 *                        "color":"#173177"
 *                    },
 *                    "keyword1":{
 *                        "value":"巧克力",
 *                        "color":"#173177"
 *                    },
 *                    "keyword2": {
 *                        "value":"39.8元",
 *                        "color":"#173177"
 *                    },
 *                    "keyword3": {
 *                        "value":"2014年9月22日",
 *                        "color":"#173177"
 *                    },
 *                    "remark":{
 *                        "value":"欢迎再次购买！",
 *                        "color":"#173177"
 *                    }
 *            }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateDataSend implements Serializable {
    private static final long serialVersionUID = 1550374466855843514L;

    private WxTemplateDataItemSend first;
    private WxTemplateDataItemSend keyword1;
    private WxTemplateDataItemSend keyword2;
    private WxTemplateDataItemSend keyword3;
    private WxTemplateDataItemSend keyword4;
    private WxTemplateDataItemSend keyword5;
    private WxTemplateDataItemSend keyword6;
    private WxTemplateDataItemSend keyword7;
    private WxTemplateDataItemSend keyword8;
    private WxTemplateDataItemSend keyword9;
    private WxTemplateDataItemSend keyword10;
    private WxTemplateDataItemSend remark;


}
