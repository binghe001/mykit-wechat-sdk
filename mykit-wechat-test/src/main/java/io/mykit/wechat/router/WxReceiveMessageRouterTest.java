package io.mykit.wechat.router;

import io.mykit.wechat.mp.beans.router.receive.WxReceiveRouterResult;
import io.mykit.wechat.mp.beans.xml.receive.base.WxBaseReceiveMessage;
import io.mykit.wechat.mp.beans.xml.receive.image.WxReceiveImageMessage;
import io.mykit.wechat.mp.beans.xml.receive.text.WxReceiveTextMessage;
import io.mykit.wechat.mp.core.router.WxReceiveMessageRouter;
import io.mykit.wechat.utils.constants.WxConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/30 10:00
 * @Description: 测试微信路由
 */
@Slf4j
public class WxReceiveMessageRouterTest {

    private Map<String, Class<? extends WxBaseReceiveMessage>> map = new HashMap<String, Class<? extends WxBaseReceiveMessage>>();

    @Before
    public void init(){
        map.put(WxConstants.ROUTER_TEXT, WxReceiveTextMessage.class);
    }

    @Test
    public void testTextRouter() throws Exception {
        log.info(map.toString());
        long startTime = System.currentTimeMillis();
        log.info("开始：" + startTime);
        String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        WxReceiveRouterResult wxReceiveRouterResult = WxReceiveMessageRouter.router(str);
        if(WxConstants.ROUTER_TEXT.equals(wxReceiveRouterResult.getRouterType())){
            log.info(wxReceiveRouterResult.toJsonString());
            log.info(wxReceiveRouterResult.getBaseReceiveMessage().toJsonString());
            log.info(String.valueOf(wxReceiveRouterResult.getBaseReceiveMessage() instanceof  WxReceiveTextMessage));
            WxReceiveTextMessage wxReceiveTextMessage = (WxReceiveTextMessage) wxReceiveRouterResult.getBaseReceiveMessage();
            log.info(wxReceiveTextMessage.toString());
        }
        long endTime = System.currentTimeMillis();
        log.info("结束：" + endTime);
        log.info("间隔：" + (endTime - startTime));
    }
    @Test
    public void testImageRouter(){
        long startTime = System.currentTimeMillis();
        log.info("开始：" + startTime);
        String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[this is a url]]></PicUrl><MediaId><![CDATA[media_id]]></MediaId><MsgId>1234567890123456</MsgId></xml>";
        WxReceiveRouterResult wxReceiveRouterResult = WxReceiveMessageRouter.router(str);
        if(WxConstants.ROUTER_IMAGE.equals(wxReceiveRouterResult.getRouterType())){
            log.info(wxReceiveRouterResult.toJsonString());
            log.info(wxReceiveRouterResult.getBaseReceiveMessage().toJsonString());
            WxReceiveImageMessage wxReceiveImageMessage = (WxReceiveImageMessage) wxReceiveRouterResult.getBaseReceiveMessage();
            log.info(wxReceiveImageMessage.toString());
        }
        long endTime = System.currentTimeMillis();
        log.info("结束：" + endTime);
        log.info("间隔：" + (endTime - startTime));
    }
    @Test
    public void testScanSubscriveRouter(){
        long startTime = System.currentTimeMillis();
        log.info("开始：" + startTime);
        String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_123123]]></EventKey><Ticket><![CDATA[TICKET]]></Ticket></xml>";
        WxReceiveRouterResult wxReceiveRouterResult = WxReceiveMessageRouter.router(str);
        log.info(wxReceiveRouterResult.toJsonString());
        long endTime = System.currentTimeMillis();
        log.info("结束：" + endTime);
        log.info("间隔：" + (endTime - startTime));
    }
}
