package io.mykit.wechat.mp.analysis;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.analysis.user.req.WxUserAnalysisDate;
import io.mykit.wechat.mp.http.handler.analysis.WxAnalysisHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/8/1 15:18
 * @Description: 测试用户分析
 */
@Slf4j
public class WxAnalysisHandlerTest extends BaseTest {

    @Test
    public void testGetUserSummary() throws Exception{
        log.info(WxAnalysisHandler.getUserSummary(APPID, APPSECRET, new WxUserAnalysisDate("2018-07-20", "2018-07-26")).toJsonString());
    }
    @Test
    public void testGetUserCumulate() throws Exception{
        log.info(WxAnalysisHandler.getUserCumulate(APPID, APPSECRET, new WxUserAnalysisDate("2018-07-20", "2018-07-26")).toJsonString());
    }
}
