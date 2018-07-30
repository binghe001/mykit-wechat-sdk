package io.mykit.wechat.router;

import io.mykit.wechat.mp.beans.router.receive.WxReceiveRouterResult;
import io.mykit.wechat.mp.beans.xml.receive.text.WxReceiveTextMessage;
import io.mykit.wechat.mp.core.router.WxReceiveMessageRouter;
import io.mykit.wechat.utils.constants.WxConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/30 10:00
 * @Description: 测试微信路由
 */
@Slf4j
public class WxReceiveMessageRouterTest {

    @Test
    public void testRouter(){
        long startTime = System.currentTimeMillis();
        log.info("开始：" + startTime);
        String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        WxReceiveRouterResult wxReceiveRouterResult = WxReceiveMessageRouter.router(str);
        if(WxConstants.ROUTER_TEXT.equals(wxReceiveRouterResult.getRouterType())){
            log.info(wxReceiveRouterResult.toJsonString());
            log.info(wxReceiveRouterResult.getBaseReceiveMessage().toJsonString());
            WxReceiveTextMessage wxReceiveTextMessage = (WxReceiveTextMessage) wxReceiveRouterResult.getBaseReceiveMessage();
            log.info(wxReceiveTextMessage.toString());
        }
        long endTime = System.currentTimeMillis();
        log.info("结束：" + endTime);
        log.info("间隔：" + (endTime - startTime));
    }
}
