package io.mykit.wechat.mp.http.handler.menu;

import com.alibaba.fastjson.JSONObject;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.menu.WxMenuBean;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.mp.http.handler.token.AccessTokenHandler;
import io.mykit.wechat.utils.common.StringUtils;
import io.mykit.wechat.utils.json.JsonUtils;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 21:04
 * @Description: 创建自定义菜单
 */

public class WxMenuHandler extends BaseHandler {

    /**
     * 创建自定义惨淡
     * @param appid appid
     * @param secret secret
     * @param json json
     * @return 封装的结果信息
     * @throws Exception 抛出异常
     */
    public static WxCode createWxMenu(String appid, String secret, String json) throws Exception{
        WxCode wxCode = new WxCode();
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_CREATE);
        String ret = HttpConnectionUtils.postWechatData(url, json, getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
        if(StringUtils.isEmpty(ret)) return wxCode;
        wxCode = JsonUtils.json2Bean(ret, WxCode.class);
        return wxCode;
    }

    /**
     * 查询微信自定义菜单
     * @param appid appid
     * @param appsecret appsecret
     * @return 微信自定义菜单原始数据
     */
    public static String getWxMenu(String appid, String appsecret) throws Exception{
        String access_token = AccessTokenHandler.getAccessToken(appid, appsecret);
        if (StringUtils.isEmpty(access_token)) return "";
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_GET);
        String ret = HttpConnectionUtils.getWechatData(url,getAccessTokenNameValuePairs(appid, appsecret),null, HttpConnectionUtils.TYPE_STREAM);
        return ret;
    }

    /**
     * 删除微信自定义菜单
     * @param appid appid
     * @param secret secret
     * @return Wxcode结果封装
     * @throws Exception
     */
    public static WxCode deleteWxMenu(String appid, String secret) throws Exception{
        WxCode wxCode = new WxCode();
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_DELETE);
        String ret = HttpConnectionUtils.getWechatData(url, getAccessTokenNameValuePairs(appid,secret),null, HttpConnectionUtils.TYPE_STREAM);
        if(StringUtils.isEmpty(ret)){
            return wxCode;
        }
        wxCode = JsonUtils.json2Bean(ret, WxCode.class);
        return wxCode;
    }

    /**
     * 创建个性化菜单
     * @param appid appid
     * @param secret secret
     * @param menuJson 封装的创建个性化菜单时的请求体
     * @return 返回微信原始字符串： 正确时：{"menuid":"208379533"}   错误：类似：{"errcode":40018,"errmsg":"invalid button name size"}
     * @throws Exception
     */
    public static String addconditionalMenu(String appid, String secret, String menuJson) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_ADDCONDITIONAL);
        return HttpConnectionUtils.postWechatData(url, menuJson,  getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 删除个性化菜单
     * @param appid appid
     * @param secret secret
     * @param menuId 菜单Id
     * @return 封装的结果状态(微信状态码和状态信息)
     * @throws Exception
     */
    public static WxCode delconditionalMenu(String appid, String secret, String menuId) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_DELCONDITIONAL);
        WxMenuBean wxMenuBean = new WxMenuBean();
        wxMenuBean.setMenuid(menuId);
        String ret = HttpConnectionUtils.postWechatData(url, JSONObject.toJSONString(wxMenuBean), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
        JSONObject jsonObject = JSONObject.parseObject(ret);
        return jsonObject.toJavaObject(WxCode.class);
    }

    /**
     * 测试个性化菜单匹配结果
     * @param appid appid
     * @param secret secret
     * @param userId  可以是粉丝的OpenID，也可以是粉丝的微信号。
     * @return 菜单内容原始字符串
     */
    public static String tryMatchMenu(String appid, String secret, String userId) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_TRY_MATCH);
        return HttpConnectionUtils.postWechatData(url, jsonObject.toJSONString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 获取自定义菜单配置
     * @param appid appid
     * @param secret secret
     * @return 自定义菜单配置内容
     * @throws Exception
     */
    public static String getInfoMenu(String appid, String secret) throws Exception{
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_GET_INFO);
        return HttpConnectionUtils.getWechatData(url,getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
}
