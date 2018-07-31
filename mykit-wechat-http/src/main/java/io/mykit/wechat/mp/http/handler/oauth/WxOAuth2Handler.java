package io.mykit.wechat.mp.http.handler.oauth;

import io.mykit.wechat.cache.redis.RedisUtils;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.oauth.*;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.utils.common.StringUtils;
import io.mykit.wechat.utils.constants.WxConstants;
import io.mykit.wechat.utils.json.JsonUtils;
import io.mykit.wechat.utils.map.ReflectMap;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 14:41
 * @Description: 通过code换取网页授权access_token
 */
@Slf4j
public class WxOAuth2Handler extends BaseHandler {

    private static final String WX_OAUTH2_ACCESS_TOKEN = "oauth2.access.token";
    private static final String WX_OAUTH2_REFRESH_TOKEN = "oauth2.refresh.token";


    /**
     * 通过code换取网页授权access_token，此方法不缓存数据，直接调用微信API
     * @param appid appid
     * @param secret secret
     * @param wxOAuth2Code  get数据
     * @return
     * { "
     *      "access_token":"ACCESS_TOKEN",
     *      "expires_in":7200,
     *      "refresh_token":"REFRESH_TOKEN",
     *      "openid":"OPENID",
     *      "scope":"SCOPE"
     * }
     *
     * 参数	                                描述
     * access_token	            网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * expires_in	            access_token接口调用凭证超时时间，单位（秒）
     * refresh_token	        用户刷新access_token
     * openid	                用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * scope	                用户授权的作用域，使用逗号（,）分隔
     * @throws Exception
     */
    public static String getOAuth2AccessToken(String appid, String secret, WxOAuth2Code wxOAuth2Code) throws Exception{
        String accessTokenKey = WX_OAUTH2_ACCESS_TOKEN.concat(appid).concat(secret);
        String refreshTokenKey = WX_OAUTH2_REFRESH_TOKEN.concat(appid).concat(secret);
        Map<String, Object> map = ReflectMap.beanToMap(wxOAuth2Code);
        map.put("appid", appid);
        map.put("secret", secret);
        String ret = HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEXIN_OAUTH2_TOKEN), getAccessTokenNameValuePairs(map), null, HttpConnectionUtils.TYPE_STREAM);
        if(StringUtils.isEmpty(ret))
            throw new RuntimeException("the data of the weixin api response is null, the API is ===> " + LoadProp.getValue(LoadProp.WEXIN_OAUTH2_TOKEN));
        WxOAuth2AccessToken wxOAuth2AccessToken = JsonUtils.json2Bean(ret, WxOAuth2AccessToken.class);
        if(wxOAuth2AccessToken == null)
            throw new RuntimeException("getOAuth2AccessToken==>> json string can not  transform to WxOAuth2AccessToken Object...");
        //说明并未覆盖errcode值，成功
        if (wxOAuth2AccessToken.getErrcode() == WxCode.ERRCODE_NORMAL){
             //成功时，缓存微信的所有数据7200s
             RedisUtils.saveValueToRedis(accessTokenKey, ret, wxOAuth2AccessToken.getExpires_in());
             //缓存refresh_token 30天
            RedisUtils.saveValueToRedis(refreshTokenKey, wxOAuth2AccessToken.getRefresh_token(), 30 * 24 * 60 * 60);
        }
        return ret;
    }

    /**
     * 刷新access_token，参数从缓存中获取
     * @param appid appid
     * @param secret secret
     * @return
     * { "
     *      "access_token":"ACCESS_TOKEN",
     *      "expires_in":7200,
     *      "refresh_token":"REFRESH_TOKEN",
     *      "openid":"OPENID",
     *      "scope":"SCOPE"
     * }
     *
     * 参数	                                描述
     * access_token	            网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * expires_in	            access_token接口调用凭证超时时间，单位（秒）
     * refresh_token	        用户刷新access_token
     * openid	                用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * scope	                用户授权的作用域，使用逗号（,）分隔
     * @throws Exception
     */
    public static String refreshOAuth2AccessToken(String appid, String secret) throws Exception{
        String refreshTokenKey = WX_OAUTH2_REFRESH_TOKEN.concat(appid).concat(secret);
        String refreshToken = RedisUtils.getValueFromRedis(refreshTokenKey);
        if(StringUtils.isEmpty(refreshToken))
            throw new RuntimeException("refreshToken is null, please call the method of \"" + WxOAuth2Handler.class.getName() + ".getOAuth2AccessToken(String appid, String secret, WxOAuth2Code wxOAuth2Code)\" to get refresh_token which should be saved in cache...");
        String accessTokenKey = WX_OAUTH2_ACCESS_TOKEN.concat(appid).concat(secret);
        WxOAuth2Refresh wxOAuth2Refresh = new WxOAuth2Refresh();
        wxOAuth2Refresh.setAppid(appid);
        wxOAuth2Refresh.setRefresh_token(refreshToken);
        wxOAuth2Refresh.setGrant_type("refresh_token");
        String ret = HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEXIN_OAUTH2_REFRESH), getAccessTokenNameValuePairs(wxOAuth2Refresh.toMap()), null, HttpConnectionUtils.TYPE_STREAM );

        if(StringUtils.isEmpty(ret))
            throw new RuntimeException("the data of the weixin api response is null, the API is ===> " + LoadProp.getValue(LoadProp.WEXIN_OAUTH2_REFRESH));
        WxOAuth2AccessToken wxOAuth2AccessToken = JsonUtils.json2Bean(ret, WxOAuth2AccessToken.class);
        if(wxOAuth2AccessToken == null)
            throw new RuntimeException("refreshOAuth2AccessToken==>> json  string can not  transform to WxOAuth2AccessToken Object...");
        //说明并未覆盖errcode值，成功
        if (wxOAuth2AccessToken.getErrcode() == WxCode.ERRCODE_NORMAL){
            //成功时，缓存微信的所有数据7200s
            RedisUtils.saveValueToRedis(accessTokenKey, ret, wxOAuth2AccessToken.getExpires_in());
            //缓存refresh_token 30天
            RedisUtils.saveValueToRedis(refreshTokenKey, wxOAuth2AccessToken.getRefresh_token(), 30 * 24 * 60 * 60);
        }
        return ret;
    }

    /**
     * 附：检验授权凭证（access_token）是否有效
     * @param access_token access_token
     * @param openid openid
     * @return
     * 成功返回：{ "errcode":0,"errmsg":"ok"}
     * 失败返回：{ "errcode":40003,"errmsg":"invalid openid"}
     * @throws Exception
     */
    public static WxCode authWxOauth2AccessToken(String access_token, String openid) throws Exception{
        WxOAuth2Auth wxOAuth2Auth =  new WxOAuth2Auth();
        wxOAuth2Auth.setAccess_token(access_token);
        wxOAuth2Auth.setOpenid(openid);
        String ret = HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEXIN_OAUTH2_TOKEN_AUTH), getAccessTokenNameValuePairs(wxOAuth2Auth.toMap()), null, HttpConnectionUtils.TYPE_STREAM);
        return JsonUtils.json2Bean(ret, WxCode.class);
    }

    /**
     * 获取用户信息
     * @param wxOAuth2GetUser
     * @return
     * 正确：
     * {    "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * 参数	                            描述
     * openid	                    用户的唯一标识
     * nickname	                    用户昵称
     * sex	                        用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * province	                    用户个人资料填写的省份
     * city	                        普通用户个人资料填写的城市
     * country	                    国家，如中国为CN
     * headimgurl	                用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * privilege	                用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * unionid	                    只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * 错误：
     * {"errcode":40003,"errmsg":" invalid openid "}
     * @throws Exception
     */
    public static WxOAuth2UserInfo getUserInfo(WxOAuth2GetUser wxOAuth2GetUser) throws Exception{
        String ret = HttpConnectionUtils.getWechatData(LoadProp.WEXIN_OAUTH2_USER_GET, getAccessTokenNameValuePairs(wxOAuth2GetUser.toMap()), null, HttpConnectionUtils.TYPE_STREAM);
        return JsonUtils.json2Bean(ret, WxOAuth2UserInfo.class);
    }

    /**
     * 获取用户信息
     * @param appid appid
     * @param secret secret
     * @return
     * 正确：
     * {    "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * 参数	                            描述
     * openid	                    用户的唯一标识
     * nickname	                    用户昵称
     * sex	                        用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * province	                    用户个人资料填写的省份
     * city	                        普通用户个人资料填写的城市
     * country	                    国家，如中国为CN
     * headimgurl	                用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * privilege	                用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * unionid	                    只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * 错误：
     * {"errcode":40003,"errmsg":" invalid openid "}
     * @throws Exception
     */
    public static WxOAuth2UserInfo getUserInfo(String appid, String secret) throws Exception{
        String accessTokenKey = WX_OAUTH2_ACCESS_TOKEN.concat(appid).concat(secret);
        String ret = RedisUtils.getValueFromRedis(accessTokenKey);
        //access_token过期, 刷新access_token
        if(StringUtils.isEmpty(ret)){
            ret = refreshOAuth2AccessToken(appid, secret);
        }
        return getWxOAuth2UserInfo(appid, secret, ret);
    }

    /**
     * 获取微信用户信息
     * @param appid appid
     * @param secret secret
     * @param ret
     * @return
     * 正确：
     * {    "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * 参数	                            描述
     * openid	                    用户的唯一标识
     * nickname	                    用户昵称
     * sex	                        用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * province	                    用户个人资料填写的省份
     * city	                        普通用户个人资料填写的城市
     * country	                    国家，如中国为CN
     * headimgurl	                用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * privilege	                用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * unionid	                    只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * 错误：
     * {"errcode":40003,"errmsg":" invalid openid "}
     * @throws Exception
     */
    private static WxOAuth2UserInfo getWxOAuth2UserInfo(String appid, String secret, String ret) throws Exception {
        //验证access_token是否有效
        WxOAuth2AccessToken wxOAuth2AccessToken = JsonUtils.json2Bean(ret, WxOAuth2AccessToken.class);
        //验证access_token是否有效
        WxCode wxCode = authWxOauth2AccessToken(wxOAuth2AccessToken.getAccess_token(), wxOAuth2AccessToken.getOpenid());
        if (wxCode == null)
            throw new RuntimeException("weixin server response null, the api is ===>>" + LoadProp.getValue(LoadProp.WEXIN_OAUTH2_TOKEN_AUTH));
        //不正常,有可能缓存中存在ret数据，但是access_token失效了
        if(wxCode.getErrcode() != WxCode.ERRCODE_NORMAL){
            //再次刷新access_token
            ret = refreshOAuth2AccessToken(appid, secret);
            wxOAuth2AccessToken = JsonUtils.json2Bean(ret, WxOAuth2AccessToken.class);
            wxCode = authWxOauth2AccessToken(wxOAuth2AccessToken.getAccess_token(), wxOAuth2AccessToken.getOpenid());
            if (wxCode == null)
                throw new RuntimeException("weixin server response null, the api is ===>>" + LoadProp.getValue(LoadProp.WEXIN_OAUTH2_TOKEN_AUTH));
        }
        //不正常,考虑了1、access_token过期；2、access_token未过期但是失效的情况，再不正常就真的不正常了。
        if(wxCode.getErrcode() != WxCode.ERRCODE_NORMAL){
            throw new RuntimeException("access_token is not normal, please call the method of \"" + WxOAuth2Handler.class.getName() + ".getOAuth2AccessToken(String appid, String secret, WxOAuth2Code wxOAuth2Code) \" to get refresh_token and access_token which should be saved in cache...");
        }
        //不正常的情况都抛出了异常，下面就是正常的情况了，获取用户的基本信息
        WxOAuth2GetUser wxOAuth2GetUser = new WxOAuth2GetUser();
        wxOAuth2GetUser.setLang("zh_CN");
        wxOAuth2GetUser.setAccess_token(wxOAuth2AccessToken.getAccess_token());
        wxOAuth2GetUser.setOpenid(wxOAuth2AccessToken.getOpenid());
        return getUserInfo(wxOAuth2GetUser);
    }


    /**
     * 获取用户信息
     * @param appid appid
     * @param secret secret
     * @param wxOAuth2Code 封装的网页传递过来的code
     * @return
     * 正确：
     * { "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * 参数	                            描述
     * openid	                    用户的唯一标识
     * nickname	                    用户昵称
     * sex	                        用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * province	                    用户个人资料填写的省份
     * city	                        普通用户个人资料填写的城市
     * country	                    国家，如中国为CN
     * headimgurl	                用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * privilege	                用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * unionid	                    只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * 错误：
     * {"errcode":40003,"errmsg":" invalid openid "}
     * @throws Exception
     */
    public static WxOAuth2UserInfo getUserInfo(String appid, String secret, WxOAuth2Code wxOAuth2Code) throws Exception{
        String accessTokenKey = WX_OAUTH2_ACCESS_TOKEN.concat(appid).concat(secret);
        String ret = RedisUtils.getValueFromRedis(accessTokenKey);
        //缓存中的access_token信息为空
        if(StringUtils.isEmpty(ret)){
            //拼接refreshTokenKey并从Redis缓存中获取refreshToken
            String refreshTokenKey = WX_OAUTH2_REFRESH_TOKEN.concat(appid).concat(secret);
            String refreshToken = RedisUtils.getValueFromRedis(refreshTokenKey);
            //refreshToken为空，则调用获取access_token接口
            if(StringUtils.isEmpty(refreshToken)){
                ret = getOAuth2AccessToken(appid, secret, wxOAuth2Code);
            }else{          //refreshToken不为空，调用刷新access_token的接口
                ret = refreshOAuth2AccessToken(appid, secret);
            }
        }
        //为空，抛出异常
        if(StringUtils.isEmpty(ret)){
            throw new RuntimeException("access_token is null, sdk call weixin getAccessToken and refreshAccessToken API, But access_token still is null...");
        }
        //存在errcode，则打印日志，并抛出异常
        if (ret.contains(WxConstants.ERRCODE)){
            log.info(ret);
            throw new RuntimeException(ret);
        }
        return getWxOAuth2UserInfo(appid, secret, ret);
    }

}
