package io.mykit.wechat.mp.autoreply;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.http.handler.autoreply.WxAutoReplyHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 14:00
 * @Description: 测试获取公众号的自动回复规则
 */
@Slf4j
public class WxAutoReplyHandlerTest extends BaseTest {

    @Test
    public void testAutoReply() throws Exception{
        log.info(WxAutoReplyHandler.getAutoReply(APPID, APPSECRET));
    }
}
