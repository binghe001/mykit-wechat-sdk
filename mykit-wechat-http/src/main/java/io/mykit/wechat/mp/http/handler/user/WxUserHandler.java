package io.mykit.wechat.mp.http.handler.user;

import io.mykit.wechat.mp.beans.json.user.tag.WxUserTag;
import io.mykit.wechat.mp.beans.json.user.tag.WxUserTagResp;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.utils.json.JsonUtils;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 18:09
 * @Description: 用户管理相关的处理类
 */

public class WxUserHandler extends BaseHandler {


    /**
     * 创建标签
     * @param appid appid
     * @param secret secret
     * @param wxUserTag 格式如下：
     *  {   "tag" : {     "name" : "广东"//标签名   } }
     * @return
     * {   "tag":{ "id":134,//标签id "name":"广东"   } }
     *
     * 参数	            说明
     * id	        标签id，由微信分配
     * name	        标签名，UTF8编码
     * @throws Exception
     */
    public static WxUserTagResp createTag(String appid, String secret, WxUserTag wxUserTag) throws Exception{
        String json = HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEXIN_TAG_CREATE), wxUserTag.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
        return JsonUtils.json2Bean(json, WxUserTagResp.class);
    }
}
