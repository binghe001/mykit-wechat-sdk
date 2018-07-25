package io.mykit.wechat.mp.http.handler.quota;

import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.mp.beans.json.quota.WxQuota;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 13:36
 * @Description: 微信API调用次数清0
 */
@Slf4j
public class WxQuotaHandler extends BaseHandler {

    /**
     * 调用 API 次数清零
     * @param appid appid
     * @param secret secret
     * @return
     * 成功：{ "errcode" :0, "errmsg" : "ok" }
     * 失败：{ "errcode" :48006, "errmsg" : "forbid to clear quota because of reaching the limit" }
     * @throws Exception
     */
    public static String quotaClear(String appid, String secret) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEXIN_QUOTA_CLEAR), new WxQuota(appid).toString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM );
    }
}
