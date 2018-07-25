package io.mykit.wechat.mp.http.handler.template.subscribe;

import io.mykit.wechat.mp.beans.json.template.subscribe.WxTemplateSubscribe;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 12:39
 * @Description: 一次性订阅消息,通过API推送订阅模板消息给到授权微信用户
 */
@Slf4j
public class WxTemplateSubscribeHandler extends BaseHandler {

    /**
     * 一次性订阅消息,通过API推送订阅模板消息给到授权微信用户
     * @param appid appid
     * @param secret secret
     * @param wxTemplateSubscribe 格式如下：
     *  {
     *     "data": {
     *         "content": {
     *             "color": "#777777",
     *             "value": "你好"
     *         }
     *     },
     *     "miniprogram": {
     *         "appid": "test",
     *         "pagepath": "test"
     *     },
     *     "scene": "sence",
     *     "template_id": "template_id",
     *     "title": "title",
     *     "touser": "toUser",
     *     "url": "http://www.baidu.com"
     * }
     * @return
     * {
     * “errcode”:0,
     * “errmsg”:”ok”
     * }
     * @throws Exception
     */
    public static String subscribeTemplate(String appid, String secret, WxTemplateSubscribe wxTemplateSubscribe) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TEMPLATE_SUBSCRIBE), wxTemplateSubscribe.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
}
