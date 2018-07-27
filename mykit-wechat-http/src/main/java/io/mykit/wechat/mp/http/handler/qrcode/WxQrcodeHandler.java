package io.mykit.wechat.mp.http.handler.qrcode;

import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeForever;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeResp;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeTemporary;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.mp.http.wx.WxHttpConnectionUtils;
import io.mykit.wechat.utils.json.JsonUtils;

import java.io.File;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 13:39
 * @Description: 二维码处理器
 * 目前有2种类型的二维码：
 *
 * 1、临时二维码，是有过期时间的，最长可以设置为在二维码生成后的30天（即2592000秒）后过期，但能够生成较多数量。临时二维码主要用于帐号绑定等不要求二维码永久保存的业务场景
 * 2、永久二维码，是无过期时间的，但数量较少（目前为最多10万个）。永久二维码主要用于适用于帐号绑定、用户来源统计等场景。
 *
 * 用户扫描带场景值二维码时，可能推送以下两种事件：
 *
 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
 *
 * 如果用户已经关注公众号，在用户扫描后会自动进入会话，微信也会将带场景值扫描事件推送给开发者。
 *
 * 获取带参数的二维码的过程包括两步，首先创建二维码ticket，然后凭借ticket到指定URL换取二维码。
 */

public class WxQrcodeHandler extends BaseHandler {

    /**
     * 请求永久二维码
     * @param appid appid
     * @param secret secret
     * @param wxQrcodeForever
     * {"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * 或者：
     * {"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
     * 参数	                                    说明
     * expire_seconds	        该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * action_name	            二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     * action_info	            二维码详细信息
     * scene_id	                场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * scene_str	            场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return
     * {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http://weixin.qq.com/q/kZgfwMTm72WWPkovabbI"}
     * 参数	                说明
     * ticket	        获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
     * url	            二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     * @throws Exception
     */
    public static WxQrcodeResp createWxQrcodeForever(String appid, String secret, WxQrcodeForever wxQrcodeForever) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_QRCODE_CREATE), wxQrcodeForever.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxQrcodeResp.class);
    }
    /**
     * 请求临时二维码
     * @param appid appid
     * @param secret secret
     * @param wxQrcodeTemporary
     * {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * 或者：
     * {"expire_seconds": 604800, "action_name": "QR_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
     * 参数	                                    说明
     * expire_seconds	        该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * action_name	            二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     * action_info	            二维码详细信息
     * scene_id	                场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * scene_str	            场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return
     * {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http://weixin.qq.com/q/kZgfwMTm72WWPkovabbI"}
     * 参数	                说明
     * ticket	        获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
     * url	            二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     * @throws Exception
     */
    public static WxQrcodeResp createWxQrcodeTemporary(String appid, String secret, WxQrcodeTemporary wxQrcodeTemporary) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_QRCODE_CREATE), wxQrcodeTemporary.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxQrcodeResp.class);
    }

    /**
     * 下载微信二维码
     * @param filePath 下载后的二维码图片在本地的存放路径
     * @param ticket 票据
     * @return 二维码文件
     * @throws Exception
     */
    public static File downloadQrcode(String filePath, String ticket) throws Exception{
        return WxHttpConnectionUtils.downloadQrcode(LoadProp.getValue(LoadProp.WEIXIN_QRCODE_SHOW), filePath, ticket);
    }

}
