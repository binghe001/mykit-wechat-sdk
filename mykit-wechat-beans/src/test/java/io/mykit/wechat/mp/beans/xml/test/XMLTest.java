package io.mykit.wechat.mp.beans.xml.test;

import io.mykit.wechat.mp.beans.xml.receive.text.WxReceiveTextMessage;
import io.mykit.wechat.utils.xml.handler.XStreamHandler;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 13:50
 * @Description: 测试XML
 */

public class XMLTest {

    @Test
    public void testBean2Xml(){
        WxReceiveTextMessage wxTextMessage = new WxReceiveTextMessage();
        wxTextMessage.setContent("哈哈");
        wxTextMessage.setCreateTime(System.currentTimeMillis());
        wxTextMessage.setFromUserName("fromUser");
        wxTextMessage.setMsgId(123L);
        wxTextMessage.setMsgType("text");
        wxTextMessage.setToUserName("toUser");
        String xml = XStreamHandler.toXml(wxTextMessage);
        System.out.println(xml);
    }

    @Test
    public void testXml2Bean(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[toUser]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[fromUser]]></FromUserName>");
        sb.append("<CreateTime>1531893613417</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[哈哈]]></Content>");
        sb.append("<MsgId>123</MsgId>");
        sb.append("</xml>");
        WxReceiveTextMessage wxTextMessage = XStreamHandler.toBean(sb.toString(), WxReceiveTextMessage.class);
        System.out.println(wxTextMessage.toString());
    }
}
