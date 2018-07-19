package io.mykit.wechat.bean.xml.test;

import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPicSysPhotoInfoMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPicSysPhotoItemMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPicSysPhotoMessage;
import io.mykit.wechat.mp.beans.xml.receive.voice.WxReceiveVoiceMessage;
import io.mykit.wechat.mp.beans.xml.send.news.WxSendNewsItemMessage;
import io.mykit.wechat.mp.beans.xml.send.news.WxSendNewsMessage;
import io.mykit.wechat.utils.crypt.wrapper.WxBizMsgCryptWrapper;
import io.mykit.wechat.utils.xml.handler.XStreamHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 17:59
 * @Description: 测试xml类的转化
 */
@Slf4j
public class BeansParserTest {
    @Test
    public void testJavaBean2XML(){
        WxReceiveVoiceMessage wxReceiveVoiceMessage = new WxReceiveVoiceMessage();
        wxReceiveVoiceMessage.setCreateTime(System.currentTimeMillis());
        wxReceiveVoiceMessage.setFormat("format");
        wxReceiveVoiceMessage.setMediaId("mediaId");
        wxReceiveVoiceMessage.setMsgId("msgId");
        wxReceiveVoiceMessage.setFromUserName("fromUser");
        wxReceiveVoiceMessage.setToUserName("toUSer");
        wxReceiveVoiceMessage.setMsgType("msgType");
        log.info(XStreamHandler.toXml(wxReceiveVoiceMessage));
    }

    @Test
    public void testNewsJavaBean2XML(){
        WxSendNewsMessage wxSendNewsMessage = new WxSendNewsMessage();
        wxSendNewsMessage.setArticleCount(2);
        wxSendNewsMessage.setCreateTime(System.currentTimeMillis());
        wxSendNewsMessage.setMsgType("news");
        wxSendNewsMessage.setFromUserName("fromUser");
        wxSendNewsMessage.setToUserName("toUser");

        List<WxSendNewsItemMessage> list = new ArrayList<WxSendNewsItemMessage>();
        WxSendNewsItemMessage wxSendNewsItemMessage = new WxSendNewsItemMessage();
        wxSendNewsItemMessage.setDescription("description");
        wxSendNewsItemMessage.setPicUrl("picUrl");
        wxSendNewsItemMessage.setTitle("title");
        wxSendNewsItemMessage.setUrl("url");
        list.add(wxSendNewsItemMessage);
        list.add(wxSendNewsItemMessage);

        wxSendNewsMessage.setArticles(list);

        log.info(XStreamHandler.toXml(wxSendNewsMessage));
    }

    @Test
    public void testWxMenuPicSysPhotoMessage2XML(){
        WxMenuPicSysPhotoItemMessage wxMenuPicSysPhotoItemMessage = new WxMenuPicSysPhotoItemMessage();
        wxMenuPicSysPhotoItemMessage.setPicMd5Sum("picMd5Sum");
//        List<WxMenuPicSysPhotoItemMessage> list = Arrays.asList(new WxMenuPicSysPhotoItemMessage[]{wxMenuPicSysPhotoItemMessage});
        List<WxMenuPicSysPhotoItemMessage> list = new ArrayList<>();
        list.add(wxMenuPicSysPhotoItemMessage);

        WxMenuPicSysPhotoInfoMessage wxMenuPicSysPhotoInfoMessage = new  WxMenuPicSysPhotoInfoMessage();
        wxMenuPicSysPhotoInfoMessage.setCount(1);
        wxMenuPicSysPhotoInfoMessage.setPicList(list);

        WxMenuPicSysPhotoMessage wxMenuPicSysPhotoMessage = new WxMenuPicSysPhotoMessage();
        wxMenuPicSysPhotoMessage.setCreateTime(System.currentTimeMillis());
        wxMenuPicSysPhotoMessage.setEvent("event");
        wxMenuPicSysPhotoMessage.setEventKey("eventKey");
        wxMenuPicSysPhotoMessage.setMsgType("msgType");
        wxMenuPicSysPhotoMessage.setSendPicsInfo(wxMenuPicSysPhotoInfoMessage);
        wxMenuPicSysPhotoMessage.setFromUserName("fromUser");
        wxMenuPicSysPhotoMessage.setToUserName("toUser");

        log.info(XStreamHandler.toXml(wxMenuPicSysPhotoMessage));
    }

    @Test
    public void testXML2WxMenuPic(){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[toUser]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[fromUser]]></FromUserName>");
        sb.append("<CreateTime>1531964046964</CreateTime>");
        sb.append("<MsgType><![CDATA[msgType]]></MsgType>");
        sb.append("<Event><![CDATA[event]]></Event>");
        sb.append("<EventKey><![CDATA[eventKey]]></EventKey>");
        sb.append("<SendPicsInfo>");
        sb.append("<Count>1</Count>");
        sb.append("<PicList>");
//        sb.append("<a>");
        sb.append("<item>");
        sb.append("<PicMd5Sum><![CDATA[picMd5Sum]]></PicMd5Sum>");
        sb.append(" </item>");
//        sb.append("</a>");
        sb.append("</PicList>");
        sb.append("</SendPicsInfo>");
        sb.append("</xml>");

        WxMenuPicSysPhotoMessage wxMenuPicSysPhotoMessage = XStreamHandler.toBean(sb.toString(), WxMenuPicSysPhotoMessage.class);
        log.info(wxMenuPicSysPhotoMessage.toString());
    }

    @Test
    public void testXML2WxMenuPic2(){
        String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1531969861648</CreateTime><MsgType><![CDATA[msgType]]></MsgType><Event><![CDATA[event]]></Event><EventKey><![CDATA[eventKey]]></EventKey><SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[picMd5Sum]]></PicMd5Sum></item></PicList></SendPicsInfo></xml>";
        WxMenuPicSysPhotoMessage wxMenuPicSysPhotoMessage = XStreamHandler.toBean(str, WxMenuPicSysPhotoMessage.class);
        log.info(wxMenuPicSysPhotoMessage.toString());
    }

    @Test
    public void testWxBizMsgCryptWrapper() throws Exception{
        // 需要加密的明文
        String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
        String token = "pamtest";
        String timestamp = "1409304348";
        String nonce = "xxxxxx";
        String appId = "wxb11529c136998cb6";
        String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1531969861648</CreateTime><MsgType><![CDATA[msgType]]></MsgType><Event><![CDATA[event]]></Event><EventKey><![CDATA[eventKey]]></EventKey><SendPicsInfo><Count>1</Count><PicList><item><PicMd5Sum><![CDATA[picMd5Sum]]></PicMd5Sum></item></PicList></SendPicsInfo></xml>";
        //String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1531982446839</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>2</ArticleCount><Articles><item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item><item><Title><![CDATA[title]]></Title><Description><![CDATA[description]]></Description><PicUrl><![CDATA[picUrl]]></PicUrl><Url><![CDATA[url]]></Url></item></Articles></xml>";
        log.info("原字符串：" + str);

        String enStr = WxBizMsgCryptWrapper.encryptMsg(encodingAesKey, token, timestamp, nonce, appId, str);
        log.info("加密后：" + enStr);


        String deStr = WxBizMsgCryptWrapper.decryptMsg(encodingAesKey, token,  appId, enStr);
        log.info("解密后：" + deStr);

        log.info(String.valueOf(str.equals(deStr)));
    }
}
