package io.mykit.wechat.bean.xml.test;

import io.mykit.wechat.mp.beans.xml.receive.event.mass.WxMassCopyrightCheckResult;
import io.mykit.wechat.mp.beans.xml.receive.event.mass.WxMassEventMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.mass.WxMassResultListItem;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPicSysPhotoInfoMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPicSysPhotoItemMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.menu.WxMenuPicSysPhotoMessage;
import io.mykit.wechat.mp.beans.xml.receive.voice.WxReceiveVoiceMessage;
import io.mykit.wechat.mp.beans.xml.send.news.WxSendNewsItemMessage;
import io.mykit.wechat.mp.beans.xml.send.news.WxSendNewsMessage;
import io.mykit.wechat.mp.http.constants.WxConstants;
import io.mykit.wechat.utils.crypt.wrapper.WxBizMsgCryptWrapper;
import io.mykit.wechat.utils.json.JsonUtils;
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
        log.info("源标签是否包含<Encrypt>：" + str.contains(WxConstants.TAG_ENCRYPT));

        String enStr = WxBizMsgCryptWrapper.encryptMsg(encodingAesKey, token, timestamp, nonce, appId, str);
        log.info("加密后：" + enStr);
        log.info("加密后标签是否包含<Encrypt>：" + enStr.contains(WxConstants.TAG_ENCRYPT));


        String deStr = WxBizMsgCryptWrapper.decryptMsg(encodingAesKey, token,  appId, enStr);
        log.info("解密后：" + deStr);
        log.info("解密后标签是否包含<Encrypt>：" + deStr.contains(WxConstants.TAG_ENCRYPT));

        log.info(String.valueOf(str.equals(deStr)));
    }

    @Test
    public void testWxMassEventMessage(){
        WxMassResultListItem wxMassResultListItem = new WxMassResultListItem();
        wxMassResultListItem.setArticleIdx(1);
        wxMassResultListItem.setAuditState(1);
        wxMassResultListItem.setCanReprint(1);
        wxMassResultListItem.setNeedReplaceContent(1);
        wxMassResultListItem.setNeedShowReprintSource(1);
        wxMassResultListItem.setOriginalArticleType(1);
        wxMassResultListItem.setOriginalArticleUrl("http://www.baidu.com");
        wxMassResultListItem.setUserDeclareState(1);

        List<WxMassResultListItem> resultList = new ArrayList<>();
        resultList.add(wxMassResultListItem);
        resultList.add(wxMassResultListItem);

        WxMassCopyrightCheckResult wxMassCopyrightCheckResult = new WxMassCopyrightCheckResult();
        wxMassCopyrightCheckResult.setCheckState(2);
        wxMassCopyrightCheckResult.setCount(2);
        wxMassCopyrightCheckResult.setResultList(resultList);

        WxMassEventMessage wxMassEventMessage = new WxMassEventMessage();
        wxMassEventMessage.setCopyrightCheckResult(wxMassCopyrightCheckResult);
        wxMassEventMessage.setErrorCount(0);
        wxMassEventMessage.setFilterCount(2);
        wxMassEventMessage.setMsgId(123L);
        wxMassEventMessage.setSentCount(2);
        wxMassEventMessage.setStatus("success");
        wxMassEventMessage.setCreateTime(System.currentTimeMillis() /1000);
        wxMassEventMessage.setEvent("event");
        wxMassEventMessage.setFromUserName("fromUser");
        wxMassEventMessage.setMsgType("msgType");
        wxMassEventMessage.setToUserName("toUser");
        wxMassEventMessage.setTotalCount(2);

        log.info(wxMassEventMessage.toXmlString());
    }

    @Test
    public void testXml2WxMassEventMessage(){
        String xml = "<xml>" +
                "<ToUserName><![CDATA[gh_4d00ed8d6399]]></ToUserName>" +
                "<FromUserName><![CDATA[oV5CrjpxgaGXNHIQigzNlgLTnwic]]></FromUserName>" +
                "<CreateTime>1481013459</CreateTime>" +
                "<MsgType><![CDATA[event]]></MsgType>" +
                "<Event><![CDATA[MASSSENDJOBFINISH]]></Event>" +
                "<MsgID>1000001625</MsgID>" +
                "<Status><![CDATA[err(30003)]]></Status>" +
                "<TotalCount>0</TotalCount>" +
                "<FilterCount>0</FilterCount>" +
                "<SentCount>0</SentCount>" +
                "<ErrorCount>0</ErrorCount>" +
                "<CopyrightCheckResult>" +
                "<Count>2</Count>" +
                "<ResultList>" +
                "<item>" +
                "<ArticleIdx>1</ArticleIdx>" +
                "<UserDeclareState>0</UserDeclareState>" +
                "<AuditState>2</AuditState>" +
                "<OriginalArticleUrl><![CDATA[Url_1]]></OriginalArticleUrl>" +
                "<OriginalArticleType>1</OriginalArticleType>" +
                "<CanReprint>1</CanReprint>" +
                "<NeedReplaceContent>1</NeedReplaceContent>" +
                "<NeedShowReprintSource>1</NeedShowReprintSource>" +
                "</item>" +
                "<item>" +
                "<ArticleIdx>2</ArticleIdx>" +
                "<UserDeclareState>0</UserDeclareState>" +
                "<AuditState>2</AuditState>" +
                "<OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl>" +
                "<OriginalArticleType>1</OriginalArticleType>" +
                "<CanReprint>1</CanReprint>" +
                "<NeedReplaceContent>1</NeedReplaceContent>" +
                "<NeedShowReprintSource>1</NeedShowReprintSource>" +
                "</item>" +
                "</ResultList>" +
                "<CheckState>2</CheckState>" +
                "</CopyrightCheckResult>" +
                "</xml>";

        WxMassEventMessage wxMassEventMessage = XStreamHandler.toBean(xml, WxMassEventMessage.class);
        System.out.println(JsonUtils.bean2Json(wxMassEventMessage));

    }

}
