package io.mykit.wechat.mp.beans.json.template.send;

import io.mykit.wechat.mp.beans.json.base.BaseJsonBean;
import io.mykit.wechat.mp.beans.json.template.WxTemplateMiniprogramSend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 10:27
 * @Description: 发送的微信模板数据
 *
 * 参数	            是否必填	            说明
 * touser	            是	            接收者openid
 * template_id	        是	            模板ID
 * url	                否	            模板跳转链接
 * miniprogram	        否	            跳小程序所需数据，不需跳小程序可不用传该数据
 * appid	            是	            所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
 * pagepath	            否	            所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
 * data	                是	            模板数据
 * color	            否	            模板内容字体颜色，不填默认为黑色
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WxTemplateSend extends BaseJsonBean {
    private static final long serialVersionUID = -2534062878840717316L;
    private String touser;
    private String template_id;
    private String url;
    private WxTemplateMiniprogramSend miniprogram;
    private WxTemplateDataSend data;

    @Override
    public String toJsonString() {
        return super.toJsonString(this);
    }
}
