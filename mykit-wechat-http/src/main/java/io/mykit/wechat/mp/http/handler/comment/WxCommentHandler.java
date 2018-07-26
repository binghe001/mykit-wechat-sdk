package io.mykit.wechat.mp.http.handler.comment;

import io.mykit.wechat.mp.beans.json.comment.WxCommentContent;
import io.mykit.wechat.mp.beans.json.comment.WxCommentData;
import io.mykit.wechat.mp.beans.json.comment.WxCommentDataGetList;
import io.mykit.wechat.mp.beans.json.comment.WxCommentId;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/26 16:33
 * @Description: 评论处理
 */

public class WxCommentHandler extends BaseHandler {

    /**
     * 打开已群发文章评论
     * @param appid appid
     * @param secret secret
     * @param wxCommentData
     * { “msg_data_id” : MSG_DATA_ID, “index” : INDEX }
     * @return
     * { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     * 返回码定义
     * { “errcode” : 45009,“errmsg” : “reach max api daily quota limit”      //没有剩余的调用次数 }
     *  { “errcode” : 88000， “errmsg” : “without comment privilege”           //没有留言权限 }
     *  { “errcode” : 88001, “errmsg” : “msg_data is not exists”                  //该图文不存在 }
     *  { “errcode”: 88002, “errmsg” : “the article is limit for safety”         //文章存在敏感信息
     * }
     * @throws Exception
     */
    public static String openComment(String appid, String secret, WxCommentData wxCommentData) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_OPEN), wxCommentData.toJsonString(), getAccessTokenNameValuePairs(appid,secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 关闭文章评论
     * @param appid appid
     * @param secret secret
     * @param wxCommentData
     *  { “msg_data_id” : MSG_DATA_ID, “index” : INDEX }
     * @return
     * { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     * @throws Exception
     */
    public static String closeComment(String appid, String secret, WxCommentData wxCommentData) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_CLOSE), wxCommentData.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 查看指定文章的评论数据
     * @param appid appid
     * @param secret secret
     * @param wxCommentDataGetList 格式如下：
     *  { “msg_data_id” : MSG_DATA_ID, “index” : INDEX, “begin”: BEGIN, “count”: COUNT, “type” : TYPE }
     * @return
     *
     * { “errcode”: 0, “errmsg” : “ok”, “total”: TOTAL           //总数，非comment的size around“comment”: [{ user_comment_id : USER_COMMENT_ID        //用户评论id aroundopenid : OPENID                                             //openid aroundcreate_time : CREATE_TIME                            //评论时间 aroundcontent : CONTENT                                        //评论内容aroundcomment_type : IS_ELECTED                          //是否精选评论，0为即非精选，1为true，即精选around reply :around { content : CONTENT                                //作者回复内容around create_time : CREATE_TIME                   //作者回复时间 } }] around}
     *
     * 返回码定义:
     * { “errcode” : 45009, “errmsg” : “reach max api daily quota limit”                  //没有剩余的调用次数 }
     *  { “errcode” : 88000， “errmsg” : “open comment without comment privilege” //没有留言权限 }
     *  { “errcode” : 88001, “errmsg” : “msg_data is not exists”                                   //该图文不存在 }
     *  { “errcode” : 88010, “errmsg” : “count range error. cout <= 0 or count > 50” //获取评论数目不合法 }
     * @throws Exception
     */
    public static String listComment(String appid, String secret, WxCommentDataGetList wxCommentDataGetList) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_LIST), wxCommentDataGetList.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     *  将评论标记精选
     * @param appid appid
     * @param secret secret
     * @param wxCommentId
     * { “msg_data_id” : MSG_DATA_ID, “index”: INDEX, “user_comment_id”: COMMENT_ID, }
     *
     *  参数	            是否必须	            类型	                说明
     * msg_data_id	            是	                Uint32	            群发返回的msg_data_id
     * index	                否	                Uint32	            多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * user_comment_id	        是	                Uint32	                评论id
     * @return
     *  { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     *  返回码定义
     *  { “errcode” : 45009, “errmsg” : “reach max api daily quota limit”                  //没有剩余的调用次数 }
     *  { “errcode” : 88000， “errmsg” : “open comment without comment privilege” //没有留言权限 }
     *  { “errcode” : 88001, “errmsg” : “msg_data is not exists”                                  //该图文不存在 }
     *  { “errcode” : 88003, “errmsg” : “elected comment upper limit”                      //精选评论数已达上限 }
     *  { “errcode” : 88004, “errmsg” : “comment was deleted by user”                     //已被用户删除，无法精选 }
     *  { “errcode” : 88008, “errmsg” : “comment is not exists”                                  //该评论不存在 }
     * @throws Exception
     */
    public static String markelectComment(String appid, String secret, WxCommentId wxCommentId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_MARKELECT), wxCommentId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     *  将评论取消精选
     * @param appid appid
     * @param secret secret
     * @param wxCommentId
     * { “msg_data_id” : MSG_DATA_ID, “index”: INDEX, “user_comment_id”: COMMENT_ID }
     *
     *  参数	            是否必须	            类型	                说明
     * msg_data_id	            是	                Uint32	            群发返回的msg_data_id
     * index	                否	                Uint32	            多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * user_comment_id	        是	                Uint32	                评论id
     * @return
     * { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     * 返回码定义
     * { “errcode” : 45009, “errmsg” : “reach max api daily quota limit”                  //没有剩余的调用次数 }
     * { “errcode” : 88000， “errmsg” : “open comment without comment privilege” //没有留言权限 }
     * { “errcode” : 88001, “errmsg” : “msg_data is not exists”                                          //该图文不存在 }
     * { “errcode” : 88008, “errmsg” : “comment is not exists”                                          //该评论不存在 }
     * @throws Exception
     */
    public static String unmarkelectComment(String appid, String secret, WxCommentId wxCommentId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_UNMARKELECT), wxCommentId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 删除评论
     * @param appid appid
     * @param secret secret
     * @param wxCommentId
     * { “msg_data_id” : MSG_DATA_ID, “index”: INDEX, “user_comment_id”: COMMENT_ID }
     *  参数	            是否必须	            类型	                说明
     * msg_data_id	            是	                Uint32	            群发返回的msg_data_id
     * index	                否	                Uint32	            多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * user_comment_id	        是	                Uint32	                评论id
     * @return
     * { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     * 返回码定义
     * { “errcode” : 45009, “errmsg” : “reach max api daily quota limit”                  //没有剩余的调用次数 }
     * { “errcode” : 88000， “errmsg” : “open comment without comment privilege” //没有留言权限 }
     * { “errcode” : 88001, “errmsg” : “msg_data is not exists”                                          //该图文不存在 }
     * { “errcode” : 88008, “errmsg” : “comment is not exists”                                          //该评论不存在 }
     * @throws Exception
     */
    public static String deletetComment(String appid, String secret, WxCommentId wxCommentId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_DELETE), wxCommentId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }


    /**
     * 回复评论
     * @param appid appid
     * @param secret secret
     * @param wxCommentContent 格式如下：
     * { “msg_data_id” : MSG_DATA_ID, “index”        : INDEX, “user_comment_id”: COMMENT_ID, “content”: CONTENT }
     * @return
     * { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     *
     * 返回码定义
     * { “errcode” : 45009, “errmsg” : “reach max api daily quota limit”                  //没有剩余的调用次数 }
     *  { “errcode” : 88000， “errmsg” : “open comment without comment privilege” //没有留言权限 }
     *  { “errcode” : 88001, “errmsg” : “msg_data is not exists”                              //该图文不存在 }
     *  { “errcode” : 88005, “errmsg” : “already reply”                                            //已经回复过了 }
     *  { “errcode” : 88007, “errmsg” : “reply content beyond max len or content len is zero”//回复超过长度限制或为0 }
     *  { “errcode” : 88008, “errmsg” : “comment is not exists”                            //该评论不存在 }
     * @throws Exception
     */
    public static String replyComment(String appid, String secret, WxCommentContent wxCommentContent) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_REPLY), wxCommentContent.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

    /**
     * 删除回复
     * @param appid appid
     * @param secret secret
     * @param wxCommentId 格式如下：
     *  { “msg_data_id” : MSG_DATA_ID, “index”        : INDEX, “user_comment_id”: COMMENT_ID, }
     *
     *  参数	            是否必须	            类型	                说明
     * msg_data_id	            是	                Uint32	            群发返回的msg_data_id
     * index	                否	                Uint32	            多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * user_comment_id	        是	                Uint32	                评论id
     * @return
     * { “errcode”: ERRCODE, “errmsg” : ERRMSG }
     * 返回码定义
     * { “errcode” : 45009, “errmsg” : “reach max api daily quota limit”                     //没有剩余的调用次数 }
     * { “errcode” : 88000， “errmsg” : “open comment without comment privilege” //没有留言权限 }
     *  { “errcode” : 88001, “errmsg” : “msg_data is not exists”                                   //该图文不存在 }
     *  { “errcode” : 88008, “errmsg” : “comment is not exists”                                   //该评论不存在 }
     *  { “errcode” : 87009, “errmsg” : “reply is not exists”                                         //该回复不存在 }
     * @throws Exception
     */
    public static String deleteReply(String appid, String secret, WxCommentId wxCommentId) throws Exception{
        return HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_COMMENT_REPLY_DELETE), wxCommentId.toJsonString(), getAccessTokenNameValuePairs(appid, secret), null, HttpConnectionUtils.TYPE_STREAM);
    }

}
