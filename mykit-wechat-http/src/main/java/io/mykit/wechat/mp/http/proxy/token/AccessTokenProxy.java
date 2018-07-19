package io.mykit.wechat.mp.http.proxy.token;

import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.NameValuePair;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 16:49
 * @Description:
 */
@Slf4j
public class AccessTokenProxy {

    /**
     * 直接通过网络获取AccessToken
     * @param appid appid
     * @param secret secret
     * @return 返回微信原始字符串
     */
    public static String getAccessToken(String appid, String secret) throws Exception{
        //String url, NameValuePair[] nameValuePairs, Map<String, String> headers, String type
        String url = LoadProp.getValue(LoadProp.WEIXIN_TOKEN_GET);
        NameValuePair[] nameValuePairs = new NameValuePair[]{
                new NameValuePair("grant_type", "client_credential"),
                new NameValuePair("appid", appid),
                new NameValuePair("secret", secret)
        };
        return HttpConnectionUtils.getWechatData(url, nameValuePairs, null, HttpConnectionUtils.TYPE_STREAM);
    }
}
