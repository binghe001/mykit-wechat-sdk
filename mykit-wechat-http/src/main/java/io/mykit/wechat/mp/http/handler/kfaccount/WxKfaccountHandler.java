package io.mykit.wechat.mp.http.handler.kfaccount;

import com.alibaba.fastjson.JSONObject;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.kfaccount.WxKfaccountBean;
import io.mykit.wechat.mp.beans.json.kfaccount.message.WxKfaccountTextMessage;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.mp.http.handler.token.AccessTokenHandler;
import io.mykit.wechat.mp.http.wx.WxHttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 15:50
 * @Description: 微信客服账号相关方法
 * 重点：操蛋的微信，坑大了！
 *
 * 微信多客服开发，返回65400错误状态码
 * 在做微信多客服开发中，按照文档一步步进行，可是却报65400错误，找了一下返回状态码提示，竟没有这个状态码，而后经过多方查找资料，才知道原因：
 * 原来是多客服管理的规则变了，这个文档已不能再使用，新规则链接如下：
 * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&key=1464266075&version=12&lang=zh_CN
 */
@Slf4j
public class WxKfaccountHandler extends BaseHandler {

    /**
     * 添加客服账号
     * @param appid appid
     * @param secret secret
     * @param wxKfaccountBean 封装的客户账号实体类
     * @return 微信结果信息
     * @throws Exception
     */
    public static WxCode addKfaccount(String appid, String secret, WxKfaccountBean wxKfaccountBean) throws Exception{
        String ret = HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MENU_KFACCOUNT_ADD), wxKfaccountBean.toString(), getAccessTokenNameValuePairs(appid,secret), null, HttpConnectionUtils.TYPE_STREAM);
        return JSONObject.toJavaObject(JSONObject.parseObject(ret), WxCode.class);
    }

    /**
     * 修改客服账号
     * @param appid appid
     * @param secret secret
     * @param wxKfaccountBean 封装的客服账号实体
     * @return 微信结果信息
     * @throws Exception
     */
    public static WxCode updateKfaccount(String appid, String secret, WxKfaccountBean wxKfaccountBean) throws Exception{
        String ret = HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MENU_KFACCOUNT_UPDATE), wxKfaccountBean.toString(), getAccessTokenNameValuePairs(appid,secret), null, HttpConnectionUtils.TYPE_STREAM);
        return JSONObject.toJavaObject(JSONObject.parseObject(ret), WxCode.class);
    }

    /**
     * 删除客服账号
     * @param appid appid
     * @param secret secret
     * @param wxKfaccountBean 封装的微信客服账号实体
     * @return 微信结果信息
     * @throws Exception
     */
    public static WxCode delKfaccount(String appid, String secret, WxKfaccountBean wxKfaccountBean) throws Exception{
        String ret = HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_MENU_KFACCOUNT_DEL), wxKfaccountBean.toString(), getAccessTokenNameValuePairs(appid,secret), null, HttpConnectionUtils.TYPE_STREAM);
        return JSONObject.toJavaObject(JSONObject.parseObject(ret), WxCode.class);
    }

    /**
     * 设置微信客服头像
     * @param appid appId
     * @param secret secret
     * @param kf_account 客户账号
     * @param filePath 本地图片完整路径
     * @return 微信状态信息
     * @throws Exception
     */
    public static WxCode uploadheadimgKfaccount(String appid, String secret, String kf_account, String filePath) throws Exception{
        String access_token = AccessTokenHandler.getAccessToken(appid, secret);
        String url = LoadProp.getValue(LoadProp.WEIXIN_MENU_KFACCOUNT_UPLOADHEADIMG) + "?access_token = "+access_token+" &kf_account= " + kf_account;
        String ret = WxHttpConnectionUtils.uploadFileToWeixinServer(url, filePath, WxHttpConnectionUtils.TYPE_IMAGE);
        return JSONObject.toJavaObject(JSONObject.parseObject(ret), WxCode.class);
    }


    /**
     * 获取所有的微信客服信息
     * @param appid appid
     * @param secret secret
     * @return 微信返回客服信息原始数据
     * @throws Exception
     */
    public static String getKflist(String appid, String secret) throws Exception{
        return HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_MENU_KFACCOUNT_GETKFLIST), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 发送微信客服文本消息
     * @param appid appid
     * @param secret secret
     * @param wxKfaccountTextMessage 微信客服文本消息数据结构
     * @return 返回发送结果
     * @throws Exception
     */
    public static String sendWxKfaccountTextMessage(String appid, String secret, WxKfaccountTextMessage wxKfaccountTextMessage) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_KFACCOUNT_MESSAGE), wxKfaccountTextMessage.toJsonString(), getAccessTokenNameValuePairs(appid,secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
}
