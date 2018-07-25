package io.mykit.wechat.mp.http.handler.template;

import io.mykit.wechat.mp.beans.json.template.WxTemplateId;
import io.mykit.wechat.mp.beans.json.template.WxTemplateShortId;
import io.mykit.wechat.mp.beans.json.template.industry.WxTemplateIndustry;
import io.mykit.wechat.mp.beans.json.template.send.WxTemplateSend;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 09:27
 * @Description: 微信模板消息处理类
 */
@Slf4j
public class WxTemplateHandler extends BaseHandler {

    /**
     * 设置所属行业
     * @param appid appid
     * @param secret appsecret
     * @param wxIndustry
     *   {
     *      "industry_id1":"1",
     *      "industry_id2":"4"
     *   }
     * @return
     * {"errcode":0,"errmsg":"ok"}
     * @throws Exception
     */
    public static String setIndustry(String appid, String secret, WxTemplateIndustry wxIndustry) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_INDUSTRY_SET), wxIndustry.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 获取所属行业
     * @param appid appid
     * @param secret appsecret
     * @return
     * {
     * "primary_industry":{"first_class":"运输与仓储","second_class":"快递"},
     * "secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"}
     * }
     * @throws Exception
     */
    public static String getIndustry(String appid, String secret) throws Exception{
        return HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_INDUSTRY_GET), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * @param appid appid
     * @param secret secret
     * @param wxTemplateId 封装的请求体数据
     *  格式如下：
     *  {
     *       "template_id_short":"TM00015"
     *   }
     * @return
     *     {
     *            "errcode":0,
     *            "errmsg":"ok",
     *            "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"
     *        }
     * @throws Exception
     */
    public static String addTemplate(String appid, String secret, WxTemplateShortId wxTemplateId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_ID_GET), wxTemplateId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 获取模板列表
     * @param appid appid
     * @param secret secret
     * @return
     * {
     *     "template_list": [
     *         {
     *             "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
     *             "title": "领取奖金提醒",
     *             "primary_industry": "IT科技",
     *             "deputy_industry": "互联网|电子商务",
     *             "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
     *             "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
     *         }
     *     ]
     * }
     *
     * 参数	            是否必填	            说明
     * access_token	        是	            接口调用凭证
     * template_id	        是	                模板ID
     * title	            是	                模板标题
     * primary_industry	    是	            模板所属行业的一级行业
     * deputy_industry	    是	            模板所属行业的二级行业
     * content	            是	                模板内容
     * example	            是	                模板示例
     * @throws Exception
     */
    public static String allTemplate(String appid, String secret) throws Exception{
        return HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_ALL_GET), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 删除模板
     * @param appid appid
     * @param secret secret
     * @param wxTemplateId 模板id
     * {
     *      "template_id" : "Dyvp3-Ff0cnail_CDSzk1fIc6-9lOkxsQE7exTJbwUE"
     *  }
     * @return
     * {
     *    "errcode" : 0,
     *    "errmsg" : "ok"
     * }
     * @throws Exception
     */
    public static String deleteTemplateById(String appid, String secret, WxTemplateId wxTemplateId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_ID_DELETE), wxTemplateId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 发送模板消息
     * @param appid appid
     * @param secret secret
     * @param wxTemplateSend 微信模板消息数据，格式如下：
     *  {
     *            "touser":"OPENID",
     *            "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
     *            "url":"http://weixin.qq.com/download",
     *            "miniprogram":{
     *              "appid":"xiaochengxuappid12345",
     *              "pagepath":"index?foo=bar"
     *            },
     *            "data":{
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
     *        }
     *
     *  参数	            是否必填	                    说明
     * touser	                是	                    接收者openid
     * template_id	            是	                        模板ID
     * url	                    否	                    模板跳转链接
     * miniprogram	            否	                跳小程序所需数据，不需跳小程序可不用传该数据
     * appid	                是	                所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     * pagepath	                否	                所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
     * data	                    是	                         模板数据
     * color	                否	                    模板内容字体颜色，不填默认为黑色
     * @return
     *  {
     *       "errcode":0,
     *       "errmsg":"ok",
     *       "msgid":200228332
     *  }
     * @throws Exception
     */
    public static String sendTemplate(String appid, String secret, WxTemplateSend wxTemplateSend) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_SEND), wxTemplateSend.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

}
