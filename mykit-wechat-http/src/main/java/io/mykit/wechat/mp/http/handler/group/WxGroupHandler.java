package io.mykit.wechat.mp.http.handler.group;

import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.mp.http.handler.token.AccessTokenHandler;
import io.mykit.wechat.mp.http.wx.WxHttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 17:59
 * @Description: 微信群发消息相关的请求
 */

@Slf4j
public class WxGroupHandler extends BaseHandler {

    /**
     * 上传图文消息内的图片获取URL
     * @param appid appid
     * @param secret secret
     * @param filePath 图片的本地路径 图片仅支持jpg/png格式，大小必须在1MB以下。
     * @return 微信返回的原始字符串
     * @throws Exception
     */
    public static String uploadImg(String appid, String secret, String filePath) throws Exception{
        String access_token = AccessTokenHandler.getAccessToken(appid, secret);
        String url = LoadProp.getValue(LoadProp.WEIXIN_GROUP_UPLOADIMG) + "?access_token=" + access_token;
        return WxHttpConnectionUtils.uploadFileToWeixinServer(url, filePath, WxHttpConnectionUtils.TYPE_MEDIA);
    }
}
