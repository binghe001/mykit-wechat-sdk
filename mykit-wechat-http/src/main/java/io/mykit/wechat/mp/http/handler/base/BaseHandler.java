package io.mykit.wechat.mp.http.handler.base;

import io.mykit.wechat.mp.http.constants.WxConstants;
import io.mykit.wechat.mp.http.handler.token.AccessTokenHandler;
import org.apache.commons.httpclient.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 21:35
 * @Description: 所有包装类的子类
 */

public class BaseHandler {

    /**
     * 获取封装的access_token的NameValuePair数组
     * @param appid appid
     * @param secret secret
     * @param map 其他需要放到连接上的参数的可变map集合
     * @return NameValuePair数组
     */
    protected static NameValuePair[] getAccessTokenNameValuePairs(String appid, String secret, Map<String, Object> map) throws Exception{
        String access_token = AccessTokenHandler.getAccessToken(appid, secret);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new NameValuePair(WxConstants.ACCESS_TOKEN, access_token));
        if(map != null && map.size() > 0){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                nameValuePairs.add(new NameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        return nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]);
    }
    /**
     * 获取封装的access_token的NameValuePair数组
     * @param appid appid
     * @param secret secret
     * @return NameValuePair数组
     */
    protected static NameValuePair[] getAccessTokenNameValuePairs(String appid, String secret) throws Exception{
        return getAccessTokenNameValuePairs(appid, secret, null);
    }

    /**
     * 获取封装的access_token的NameValuePair数组
     * @param map map对象
     * @return NameValuePair数组
     * @throws Exception
     */
    protected static NameValuePair[] getAccessTokenNameValuePairs(Map<String, Object> map) throws Exception{
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if(map != null && map.size() > 0){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                nameValuePairs.add(new NameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        return nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]);
    }
}
