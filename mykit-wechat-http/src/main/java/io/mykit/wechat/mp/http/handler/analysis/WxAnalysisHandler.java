package io.mykit.wechat.mp.http.handler.analysis;

import io.mykit.wechat.mp.beans.json.analysis.user.req.WxUserAnalysisDate;
import io.mykit.wechat.mp.beans.json.analysis.user.resp.WxUserCumulateAnalysisResp;
import io.mykit.wechat.mp.beans.json.analysis.user.resp.WxUserSummaryAnalysisResp;
import io.mykit.wechat.mp.config.LoadProp;
import io.mykit.wechat.mp.http.base.HttpConnectionUtils;
import io.mykit.wechat.mp.http.handler.base.BaseHandler;
import io.mykit.wechat.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyazhuang
 * @Date: 2018/8/1 14:57
 * @Description: 数据分析能力
 */
@Slf4j
public class WxAnalysisHandler extends BaseHandler {


    /**
     * 获取用户增减数据,	最大时间跨度: 7
     * @param appid appid
     * @param secret secret
     * @param wxUserAnalysisDate
     * {
     *     "begin_date": "2014-12-02",
     *     "end_date": "2014-12-07"
     * }
     * @return
     * {
     *     "list": [
     *         {
     *             "ref_date": "2014-12-07",
     *             "user_source": 0,
     *             "new_user": 0,
     *             "cancel_user": 0
     *         }//后续还有ref_date在begin_date和end_date之间的数据
     *     ]
     * }
     * @throws Exception
     */
    public static WxUserSummaryAnalysisResp getUserSummary(String appid, String secret, WxUserAnalysisDate wxUserAnalysisDate) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_DATACUBE_GETUSERSUMMARY), wxUserAnalysisDate.toJsonString(), getAccessTokenNameValuePairs(appid, secret) ,null, HttpConnectionUtils.TYPE_STREAM), WxUserSummaryAnalysisResp.class);
    }

    /**
     * 获取累计用户数据, 最大时间跨度7
     * @param appid appid
     * @param secret secret
     * @param wxUserAnalysisDate
     * {
     *     "begin_date": "2014-12-02",
     *     "end_date": "2014-12-07"
     * }
     * @return
     *
     * {
     *     "list": [
     *         {
     *             "ref_date": "2014-12-07",
     *             "cumulate_user": 1217056
     *         }, //后续还有ref_date在begin_date和end_date之间的数据
     *     ]
     * }
     * @throws Exception
     */
    public static WxUserCumulateAnalysisResp getUserCumulate(String appid, String secret, WxUserAnalysisDate wxUserAnalysisDate) throws Exception{
        return JsonUtils.json2Bean(HttpConnectionUtils.postWechatData(LoadProp.getValue(LoadProp.WEIXIN_DATACUBE_GETUSERCUMULATE), wxUserAnalysisDate.toJsonString(), getAccessTokenNameValuePairs(appid, secret) ,null, HttpConnectionUtils.TYPE_STREAM), WxUserCumulateAnalysisResp.class);
    }
}
