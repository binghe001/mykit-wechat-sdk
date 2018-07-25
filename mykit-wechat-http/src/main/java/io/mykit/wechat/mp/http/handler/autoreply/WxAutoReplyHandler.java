package io.mykit.wechat.mp.http.handler.autoreply;

import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 13:51
 * @Description: 获取公众号的自动回复规则
 */
@Slf4j
public class WxAutoReplyHandler extends BaseHandler {

    /**获取公众号的自动回复规则
     * @param appid appid
     * @param secret secret
     * @return
     * {
     *    "is_add_friend_reply_open": 1,
     *    "is_autoreply_open": 1,
     *    "add_friend_autoreply_info": {
     *        "type": "text",
     *        "content": "Thanks for your attention!"
     *    },
     *    "message_default_autoreply_info": {
     *        "type": "text",
     *        "content": "Hello, this is autoreply!"
     *    },
     *    "keyword_autoreply_info": {
     *        "list": [
     *            {
     *                "rule_name": "autoreply-news",
     *                "create_time": 1423028166,
     *                "reply_mode": "reply_all",
     *                "keyword_list_info": [
     *                    {
     *                        "type": "text",
     *                        "match_mode": "contain",
     *                        "content": "news测试"//此处content即为关键词内容
     *                    }
     *                ],
     *                "reply_list_info": [
     *                    {
     *                        "type": "news",
     *                        "news_info": {
     *                            "list": [
     *                                {
     *                                    "title": "it's news",
     *                                    "author": "jim",
     *                                    "digest": "it's digest",
     *                                    "show_cover": 1,  "cover_url": "http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfKbE8sWdt2DDcL0dMfQWJWTVn1N8DxI0gcRmrtqBOuwQH
     *
     * euPKmFLK0ZQ/0",
     *                                    "content_url": "http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=203929886&idx=1&sn=628f964cf0c6d84c026881b6959aea8b#rd",
     *                                    "source_url": "http://www.url.com"
     *                                }
     *                            ]
     *                        }
     *                    },
     *                    {
     *                        "type": "news",
     *                        "content":"KQb_w_Tiz-nSdVLoTV35Psmty8hGBulGhEdbb9SKs-o",
     *                        "news_info": {
     *                            "list": [
     *                                {
     *                                    "title": "MULTI_NEWS",
     *                                    "author": "JIMZHENG",
     *                                    "digest": "text",
     *                                    "show_cover": 0,
     *                                    "cover_url": "http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfK0HKuBIa1A1cypS0uY1wickv70iaY1gf3I1DTszuJoS3lAVLv
     *
     * hTcm9sDA/0",
     *                                    "content_url": "http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=204013432&idx=1&sn=80ce6d9abcb832237bf86c87e50fda15#rd",
     *                                    "source_url": ""
     *                                },
     *                                {
     *                                    "title": "MULTI_NEWS4",
     *                                    "author": "JIMZHENG",
     *                                    "digest": "MULTI_NEWSMULTI_NEWSMULTI_NEWSMULTI_NEWSMULTI_NEWSMULT",
     *                                    "show_cover": 1,
     * "cover_url": "http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfKbE8sWdt2DDcL0dMfQWJWTVn1N8DxI0gcRmrtqBOuwQ
     *
     * HeuPKmFLK0ZQ/0",
     *                                    "content_url": "http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=204013432&idx=5&sn=b4ef73a915e7c2265e437096582774af#rd",
     *                                    "source_url": ""
     *                                }
     *                            ]
     *                        }
     *                    }
     *                ]
     *            },
     *            {
     *                "rule_name": "autoreply-voice",
     *                "create_time": 1423027971,
     *                "reply_mode": "random_one",
     *                "keyword_list_info": [
     *                    {
     *                        "type": "text",
     *                        "match_mode": "contain",
     *                        "content": "voice测试"
     *                    }
     *                ],
     *                "reply_list_info": [
     *                    {
     *                        "type": "voice",
     *                        "content": "NESsxgHEvAcg3egJTtYj4uG1PTL6iPhratdWKDLAXYErhN6oEEfMdVyblWtBY5vp"
     *                    }
     *                ]
     *            },
     *            {
     *                "rule_name": "autoreply-text",
     *                "create_time": 1423027926,
     *                "reply_mode": "random_one",
     *                "keyword_list_info": [
     *                    {
     *                        "type": "text",
     *                        "match_mode": "contain",
     *                        "content": "text测试"
     *                    }
     *                ],
     *                "reply_list_info": [
     *                    {
     *                        "type": "text",
     *                        "content": "hello!text!"
     *                    }
     *                ]
     *            },
     *            {
     *                "rule_name": "autoreply-video",
     *                "create_time": 1423027801,
     *                "reply_mode": "random_one",
     *                "keyword_list_info": [
     *                    {
     *                        "type": "text",
     *                        "match_mode": "equal",
     *                        "content": "video测试"
     *                    }
     *                ],
     *                "reply_list_info": [
     *                    {
     *                  "type": "video",
     * "content": "http://61.182.133.153/vweixinp.tc.qq.com/1007_114bcede9a2244eeb5ab7f76d951df5f.f10.mp4?vkey=7183E5C952B16C3AB1991BA8138673DE1037CB82A29801A504B64A77F691BF9DF7AD054A9B7FE683&sha=0&save=1"
     *                    }
     *                ]
     *            }
     *        ]
     *    }
     * }
     *
     *
     * 参数	                                        说明
     * is_add_friend_reply_open	        关注后自动回复是否开启，0代表未开启，1代表开启
     * is_autoreply_open	            消息自动回复是否开启，0代表未开启，1代表开启
     * add_friend_autoreply_info	    关注后自动回复的信息
     * type	                            自动回复的类型。关注后自动回复和消息自动回复的类型仅支持文本（text）、图片（img）、语音（voice）、视频（video），关键词自动回复则还多了图文消息（news）
     * content	                        对于文本类型，content是文本内容，对于图文、图片、语音、视频类型，content是mediaID
     * message_default_autoreply_info	消息自动回复的信息
     * keyword_autoreply_info	        关键词自动回复的信息
     * rule_name	                    规则名称
     * create_time	                    创建时间
     * reply_mode	                    回复模式，reply_all代表全部回复，random_one代表随机回复其中一条
     * keyword_list_info	            匹配的关键词列表
     * match_mode	                    匹配模式，contain代表消息中含有该关键词即可，equal表示消息内容必须和关键词严格相同
     * news_info	                    图文消息的信息
     * title	                        图文消息的标题
     * digest	                        摘要
     * author	                        作者
     * show_cover	                    是否显示封面，0为不显示，1为显示
     * cover_url	                    封面图片的URL
     * content_url	                    正文的URL
     * source_url	                    原文的URL，若置空则无查看原文入口
     * @throws Exception
     */
    public static String getAutoReply(String appid, String secret) throws Exception{
        return HttpConnectionUtils.getWechatData(LoadProp.getValue(LoadProp.WEXIN_AUTOREPLY_INFO), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }
}
