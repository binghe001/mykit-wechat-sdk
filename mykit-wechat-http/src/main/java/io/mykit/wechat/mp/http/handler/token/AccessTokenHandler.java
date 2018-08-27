package io.mykit.wechat.mp.http.handler.token;

import io.mykit.wechat.cache.redis.RedisUtils;
import io.mykit.wechat.mp.beans.json.token.WxAccessToken;
import io.mykit.wechat.mp.http.proxy.token.AccessTokenProxy;
import io.mykit.wechat.utils.common.StringUtils;
import io.mykit.wechat.utils.constants.WxConstants;
import io.mykit.wechat.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 17:09
 * @Description: 获取Access_token相关的包装类
 */
@Slf4j
public class AccessTokenHandler {

    private static final String WX_BASE = "base";

    /**
     * 直接通过网络获取access_token
     * @param appid appid
     * @param secret secret
     * @return 返回从微信获取的access_token
     */
    public static String getAccessToken(String appid, String secret) throws Exception{
        WxAccessToken wxAccessToken = getWxAccessToken(appid, secret);
        if(wxAccessToken == null) return "";
        return wxAccessToken.getAccess_token();
    }

    /**
     * 查看本地是否存在AccessToken,本地存在则直接取缓存中的数据，本地不存在，则直接通过网络获取
     * @param appid appid
     * @param secret secret
     * @return 返回从微信获取的access_token
     */
    public static String getAccessTokenWithCache(String appid, String secret) throws Exception{
        String key = WX_BASE.concat(appid).concat(secret);
        //从缓存获取access_token
        String access_token = RedisUtils.getValueFromRedis(key);
        //缓存中没有，则从网络直接获取后放入缓存7200s
        if(StringUtils.isEmpty(access_token)){
            log.debug("通过网络获取access_token");
            WxAccessToken wxAccessToken = getWxAccessToken(appid, secret);
            if(wxAccessToken == null){
                return "";
            }
            RedisUtils.saveValueToRedis(key,wxAccessToken.getAccess_token(), wxAccessToken.getExpires_in());
            access_token = wxAccessToken.getAccess_token();
        }
        return access_token;
    }
    /**
     * 直接通过网络获取access_token
     * @param appid appid
     * @param secret secret
     * @return 返回从微信获取的access_token数据封装
     */
    private static WxAccessToken getWxAccessToken(String appid, String secret) throws Exception{
        String json = AccessTokenProxy.getAccessToken(appid, secret);
        if(StringUtils.isEmpty(json) || json.contains(WxConstants.ERRCODE)){
            json = AccessTokenProxy.getAccessToken(appid, secret);
        }
        if(StringUtils.isEmpty(json) || json.contains(WxConstants.ERRCODE)){
            return null;
        }
        WxAccessToken wxAccessToken = JsonUtils.json2Bean(json, WxAccessToken.class);
        log.debug("wxAccessToken====>>>" + wxAccessToken.toString());
        return wxAccessToken;
    }

}
