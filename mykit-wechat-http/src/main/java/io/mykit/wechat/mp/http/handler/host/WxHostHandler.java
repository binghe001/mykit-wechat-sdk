package io.mykit.wechat.mp.http.handler.host;

import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 20:21
 * @Description: 获取微信服务器地址
 */
@Slf4j
public class WxHostHandler extends BaseHandler {

    /**
     * 获取微信服务器地址列表
     * @param appid appid
     * @param secret secret
     * @return 获取微信服务器地址列表，原始数据
     */
    public static String getHostList(String appid, String secret) throws  Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_HOST_GET);
        return HttpConnectionUtils.getWechatData(url, getAccessTokenNameValuePairs(appid, secret) , null,HttpConnectionUtils.TYPE_STREAM);
    }
}
