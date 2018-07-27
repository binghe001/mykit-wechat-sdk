package io.mykit.wechat.mp.http.handler.user;

import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.user.tag.*;
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
     * {"errcode":0,"errmsg":"","tag":{"id":100,"name":"测试标签"}}
     *
     * 参数	            说明
     * id	        标签id，由微信分配
     * name	        标签名，UTF8编码
     * @throws Exception
     */
    public static WxUserTagCreateResp createTag(String appid, String secret, WxUserTag wxUserTag) throws Exception{
        String json = HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEXIN_TAG_CREATE), wxUserTag.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
        return JsonUtils.json2Bean(json, WxUserTagCreateResp.class);
    }

    /**
     * 获取已创建的标签
     * @param appid appid
     * @param secret secret
     * @return
     * {   "tags":[{ "id":1,name":"每天一罐可乐星人",  "count":0 //此标签下粉丝数 },{   "id":2,"name":"星标组","count":0 },{ "id":127, "name":"广东","count":5 }] }
     * 实际返回：{"errcode":0,"errmsg":"","tags":[{"count":0,"id":2,"name":"星标组"},{"count":0,"id":100,"name":"测试标签"}]}
     *
     * @throws Exception
     */
    public static WxUserTagGetResp getTag(String appid, String secret) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.getData(LoadProp.getValue(LoadProp.WEXIN_TAG_GET), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxUserTagGetResp.class);
    }

    /**
     * 编辑标签
     * @param appid appid
     * @param secret secret
     * @param wxUserTagCreateResp 创建标签返回的数据格式
     *  { "tag" : { "id" : 134, "name" : "广东人"   } }
     * @return
     * {   "errcode":0,   "errmsg":"ok" }
     *
     * 错误码	               说明
     * -1	                系统繁忙
     * 45157	            标签名非法，请注意不能和其他标签重名
     * 45158	            标签名长度超过30个字节
     * 45058	            不能修改0/1/2这三个系统默认保留的标签
     * @throws Exception
     */
    public static WxCode updateTag(String appid, String secret, WxUserTagCreateResp wxUserTagCreateResp) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEXIN_TAG_UPDATE), wxUserTagCreateResp.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }

    /**
     * 删除标签
     * @param appid appid
     * @param secret secret
     * @param wxUserTagCreateResp 创建标签返回的数据格式
     *  {   "tag":{        "id" : 134   } }
     * @return
     * {   "errcode":0,   "errmsg":"ok" }
     *
     * 错误码	        说明
     * -1	            系统繁忙
     * 45058	不能修改0/1/2这三个系统默认保留的标签
     * 45057	该标签下粉丝数超过10w，不允许直接删除
     * @throws Exception
     */
    public static WxCode deleteTag(String appid, String secret, WxUserTagCreateResp wxUserTagCreateResp) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TAG_DELETE), wxUserTagCreateResp.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }

    /**
     * 获取标签下的粉丝列表
     * @param appid appid
     * @param secret secret
     * @param wxUserTagGetList
     * {   "tagid" : 134,   "next_openid":""//第一个拉取的OPENID，不填默认从头开始拉取 }
     * @return
     * {
     *     "count": 2,
     *     "data": {
     *         "openid": [
     *             "ocYxcuAEy30bX0NXmGn4ypqx3tI0",
     *             "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"
     *         ]
     *     },
     *     "next_openid": "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"
     * }
     *
     * 错误码说明
     * 错误码	            说明
     * -1	               系统繁忙
     * 40003	        传入非法的openid
     * 45159	            非法的tag_id
     * @throws Exception
     */
    public static WxUserTagGetListResp getTagUserList(String appid, String secret, WxUserTagGetList wxUserTagGetList) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TAG_USER_GET), wxUserTagGetList.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxUserTagGetListResp.class);
    }

    /**
     * 批量为用户打标签
     * @param appid appid
     * @param secret secret
     * @param wxUserTagMember
     * {   "openid_list" : [//粉丝列表
     * "ocYxcuAEy30bX0NXmGn4ypqx3tI0",
     * "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"   ],
     * "tagid" : 134 }
     * @return
     * {
     * "errcode":0,
     * "errmsg":"ok"
     * }
     *
     * 错误码	        说明
     * -1	            系统繁忙
     * 40032	每次传入的openid列表个数不能超过50个
     * 45159	非法的标签
     * 45059	有粉丝身上的标签数已经超过限制，即超过20个
     * 40003	传入非法的openid
     * 49003	传入的openid不属于此AppID
     * @throws Exception
     */
    public static WxCode batchtaggingUserTag(String appid, String secret, WxUserTagMember wxUserTagMember) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TAG_BATCHTAGGING), wxUserTagMember.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }
    /**
     * 批量为用户取消标签
     * @param appid appid
     * @param secret secret
     * @param wxUserTagMember
     * {   "openid_list" : [//粉丝列表
     * "ocYxcuAEy30bX0NXmGn4ypqx3tI0",
     * "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"   ],
     * "tagid" : 134 }
     * @return
     * {
     * "errcode":0,
     * "errmsg":"ok"
     * }
     *
     * 错误码	        说明
     * -1	            系统繁忙
     * 40032	每次传入的openid列表个数不能超过50个
     * 45159	非法的标签
     * 45059	有粉丝身上的标签数已经超过限制，即超过20个
     * 40003	传入非法的openid
     * 49003	传入的openid不属于此AppID
     * @throws Exception
     */
    public static WxCode batchuntaggingUserTag(String appid, String secret, WxUserTagMember wxUserTagMember) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TAG_BATCHUNTAGGING), wxUserTagMember.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }

    /**
     * 获取用户身上的标签列表
     * @param appid appid
     * @param secret secret
     * @param wxUserTagOpenId
     * {   "openid" : "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y" }
     * @return
     * {   "tagid_list":[//被置上的标签列表 134, 2   ] }
     *
     * 错误码	        说明
     * -1	        系统繁忙
     * 40003	    传入非法的openid
     * 49003	    传入的openid不属于此AppID
     * @throws Exception
     */
    public static WxUserTagId getUserTagList(String appid, String secret, WxUserTagOpenId wxUserTagOpenId) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_TAG_GET_LIST), wxUserTagOpenId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxUserTagId.class);
    }

    /**
     * 设置用户备注名
     * @param appid appid
     * @param secret secret
     * @param wxUserTagRemark
     * {
     *     "openid":"oDF3iY9ffA-hqb2vVvbr7qxf6A0Q",
     *     "remark":"pangzi"
     * }
     * @return
     * {
     * "errcode":0,
     * "errmsg":"ok"
     * }
     *
     * {"errcode":40013,"errmsg":"invalid appid"}
     * @throws Exception
     */
    public static WxCode setUserRemark(String appid, String secret, WxUserTagRemark wxUserTagRemark) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_INFO_UPDATEREMARK), wxUserTagRemark.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     * @param appid appid
     * @param secret secret
     * @param wxUserTagLang
     * ?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     * @return
     * {
     *     "subscribe": 1,
     *     "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
     *     "nickname": "Band",
     *     "sex": 1,
     *     "language": "zh_CN",
     *     "city": "广州",
     *     "province": "广东",
     *     "country": "中国",
     *     "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
     *     "subscribe_time": 1382694957,
     *     "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     *     "remark": "",
     *     "groupid": 0,
     *     "tagid_list":[128,2],
     *     "subscribe_scene": "ADD_SCENE_QR_CODE",
     *     "qr_scene": 98765,
     *     "qr_scene_str": ""
     * }
     *
     * 参数	                    说明
     * subscribe	    用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * openid	        用户的标识，对当前公众号唯一
     * nickname	        用户的昵称
     * sex	            用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * city	            用户所在城市
     * country	        用户所在国家
     * province	        用户所在省份
     * language	        用户的语言，简体中文为zh_CN
     * headimgurl	    用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * subscribe_time	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * unionid	        只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * remark	        公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * groupid	        用户所在的分组ID（兼容旧的用户分组接口）
     * tagid_list	    用户被打上的标签ID列表
     * subscribe_scene	返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     * qr_scene	        二维码扫码场景（开发者自定义）
     * qr_scene_str	    二维码扫码场景描述（开发者自定义）
     *
     *
     * {"errcode":40013,"errmsg":"invalid appid"}
     * @throws Exception
     */
    public static WxUseInfo getWxUseInfo(String appid, String secret, WxUserTagLang wxUserTagLang) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_INFO), getAccessTokenNameValuePairs(appid, secret, wxUserTagLang.toMap()), null, HttpConnectionUtils.TYPE_STREAM), WxUseInfo.class);
    }

    /**
     * @param appid appid
     * @param secret secret
     * @param wxUserTagUserList
     * {
     *     "user_list": [
     *         {
     *             "openid": "otvxTs4dckWG7imySrJd6jSi0CWE",
     *             "lang": "zh_CN"
     *         },
     *         {
     *             "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg",
     *             "lang": "zh_CN"
     *         }
     *     ]
     * }
     *
     * 参数	            是否必须	            说明
     * openid	            是	            用户的标识，对当前公众号唯一
     * lang	                否	            国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN
     * @return
     *
     * {
     *    "user_info_list": [
     *        {
     *            "subscribe": 1,
     *            "openid": "otvxTs4dckWG7imySrJd6jSi0CWE",
     *            "nickname": "iWithery",
     *            "sex": 1,
     *            "language": "zh_CN",
     *            "city": "揭阳",
     *            "province": "广东",
     *            "country": "中国",
     *
     *            "headimgurl": "http://thirdwx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0",
     *
     *           "subscribe_time": 1434093047,
     *            "unionid": "oR5GjjgEhCMJFyzaVZdrxZ2zRRF4",
     *            "remark": "",
     *
     *            "groupid": 0,
     *            "tagid_list":[128,2],
     *            "subscribe_scene": "ADD_SCENE_QR_CODE",
     *            "qr_scene": 98765,
     *            "qr_scene_str": ""
     *
     *       },
     *        {
     *            "subscribe": 0,
     *            "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg"
     *        }
     *    ]
     * }
     * 参数	                    说明
     * subscribe	    用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * openid	        用户的标识，对当前公众号唯一
     * nickname	        用户的昵称
     * sex	            用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * city	            用户所在城市
     * country	        用户所在国家
     * province	        用户所在省份
     * language	        用户的语言，简体中文为zh_CN
     * headimgurl	    用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * subscribe_time	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * unionid	        只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * remark	        公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * groupid	        用户所在的分组ID（兼容旧的用户分组接口）
     * tagid_list	    用户被打上的标签ID列表
     * subscribe_scene	返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     * qr_scene	        二维码扫码场景（开发者自定义）
     * qr_scene_str	    二维码扫码场景描述（开发者自定义）
     *
     * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
     *
     * {"errcode":40013,"errmsg":"invalid appid"}
     * @throws Exception
     */
    public static WxUseInfoList getWxUserInfoList(String appid, String secret, WxUserTagUserList wxUserTagUserList) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_BATCHGET), wxUserTagUserList.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM), WxUseInfoList.class);
    }

    /**
     * 获取用户列表
     * @param appid appid
     * @param secret secret
     * @param wxUserTagOpenId
     * t?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
     * @return
     * {"total":2,
     * "count":2,
     * "data":{
     * "openid":["OPENID1","OPENID2"]},
     * "next_openid":"NEXT_OPENID"
     * }
     * 参数	                说明
     * total	    关注该公众账号的总用户数
     * count	    拉取的OPENID个数，最大值为10000
     * data	        列表数据，OPENID的列表
     * next_openid	拉取列表的最后一个用户的OPENID
     *
     *
     * 错误时返回JSON数据包（示例为无效AppID错误）：
     *
     * {"errcode":40013,"errmsg":"invalid appid"}
     * @throws Exception
     */
    public static WxUserGetList getWxUserGetList(String appid, String secret, WxUserTagOpenId wxUserTagOpenId) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_GET), getAccessTokenNameValuePairs(appid, secret, wxUserTagOpenId.toMap()), null, HttpConnectionUtils.TYPE_STREAM), WxUserGetList.class);
    }

    /**
     * 获取公众号的黑名单列表
     * @param appid appid
     * @param secret secret
     * @param wxUserTagOpenId
     * {"begin_openid":"OPENID1" }
     * @return
     * {
     *  "total":23000,
     *  "count":10000,
     *  "data":{"
     *     openid":[
     *        "OPENID1",
     *        "OPENID2",
     *        ...,
     *        "OPENID10000"
     *     ]
     *   },
     *   "next_openid":"OPENID10000"
     * }
     * 错误时返回 JSON数据包（示例为无效AppID错误）
     * {"errcode":40013,"errmsg":"invalid appid"}
     *
     * 返回码	        说明
     * -1	            系统繁忙
     * 40003	    传入非法的openid
     * 49003	    传入的openid不属于此AppID
     * @throws Exception
     */
    public static WxUserGetList getUserBlackList(String appid, String secret, WxUserTagOpenId wxUserTagOpenId) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_BLACK_LIST), getAccessTokenNameValuePairs(appid, secret, wxUserTagOpenId.toMap()), null, HttpConnectionUtils.TYPE_STREAM), WxUserGetList.class);
    }


    /**
     * 拉黑用户
     * @param appid appid
     * @param secret secret
     * @param wxUserOpenIds
     * {
     *  "openid_list":["OPENID1”,” OPENID2”]
     * }
     * @return
     * {
     * "errcode": 0,
     * "errmsg": "ok"
     * }
     *
     * 返回码	     说明
     * -1	        系统繁忙
     * 40003	    传入非法的openid
     * 49003	    传入的openid不属于此AppID
     * 40032	    一次只能拉黑20个用户
     * @throws Exception
     */
    public static WxCode batchBlackList(String appid, String secret, WxUserOpenIds wxUserOpenIds) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_BLACK_BATCHBLACKLIST), getAccessTokenNameValuePairs(appid, secret, wxUserOpenIds.toMap()), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }

    /**
     * 取消拉黑用户
     * @param appid appid
     * @param secret secret
     * @param wxUserOpenIds
     * {
     *  "openid_list":["OPENID1”,” OPENID2”]
     * }
     * @return
     * {
     * "errcode": 0,
     * "errmsg": "ok"
     * }
     *
     * 返回码	     说明
     * -1	        系统繁忙
     * 40003	    传入非法的openid
     * 49003	    传入的openid不属于此AppID
     * 40032	    一次只能拉黑20个用户
     * @throws Exception
     */
    public static WxCode batchUnblackList(String appid, String secret, WxUserOpenIds wxUserOpenIds) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEIXIN_USER_BLACK_BATCHUNBLACKLIST), getAccessTokenNameValuePairs(appid, secret, wxUserOpenIds.toMap()), null, HttpConnectionUtils.TYPE_STREAM), WxCode.class);
    }



}
