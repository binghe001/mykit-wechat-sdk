package io.mykit.wechat.bean.json.test;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import io.mykit.wechat.mp.beans.json.mass.openid.news.WxMassOpenIdNewsMassage;
import io.mykit.wechat.mp.beans.json.mass.openid.video.WxMassOpenIdMedia;
import io.mykit.wechat.mp.beans.json.mass.openid.video.WxMassOpenIdVideoMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.WxMassPreviewMediaId;
import io.mykit.wechat.mp.beans.json.mass.preview.news.WxMassPreviewNewsMessage;
import io.mykit.wechat.mp.beans.json.mass.preview.wxcard.WxMassPreviewCardInfo;
import io.mykit.wechat.mp.beans.json.mass.preview.wxcard.WxMassPreviewCardInfoExt;
import io.mykit.wechat.mp.beans.json.mass.preview.wxcard.WxMassPreviewCardMessage;
import io.mykit.wechat.mp.beans.xml.receive.event.template.WxTemplateEventMessage;
import io.mykit.wechat.utils.xml.handler.XStreamHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 09:56
 * @Description: 测试数据格式
 */
@Slf4j
public class WxBeanTest {


    @Test
    public void testMessage() throws Exception{
        WxMassOpenIdNewsMassage wxMassOpenIdNewsMassage = new WxMassOpenIdNewsMassage();
        List<String> openIds = new ArrayList<>();
        openIds.add("123");
        openIds.add("456");

        WxMassOpenIdMediaId wxMassOpenIdMediaId = new WxMassOpenIdMediaId();
        wxMassOpenIdMediaId.setMedia_id("media_id");

        wxMassOpenIdNewsMassage.setMpnews(wxMassOpenIdMediaId);
        wxMassOpenIdNewsMassage.setMsgtype("msg_type");
        wxMassOpenIdNewsMassage.setSend_ignore_reprint(0);
        wxMassOpenIdNewsMassage.setTouser(openIds);

        log.info(wxMassOpenIdNewsMassage.toString());
    }


    @Test
    public void testWxMassOpenIdVideoMessage(){
        List<String> touser = Arrays.asList(new String[]{"123","456"});
        WxMassOpenIdMedia wxMassOpenIdMedia = new WxMassOpenIdMedia();
        wxMassOpenIdMedia.setDescription("desc");
        wxMassOpenIdMedia.setTitle("title");
        wxMassOpenIdMedia.setMedia_id("media_id");

        WxMassOpenIdVideoMessage wxMassOpenIdVideoMessage = new WxMassOpenIdVideoMessage();
        wxMassOpenIdVideoMessage.setMpvideo(wxMassOpenIdMedia);
        wxMassOpenIdVideoMessage.setMsgtype("mpvideo");
        wxMassOpenIdVideoMessage.setTouser(touser);

        log.info(wxMassOpenIdVideoMessage.toString());
    }

    @Test
    public void testWxMassPreviewNewsMessage(){
        WxMassPreviewNewsMessage wxMassPreviewNewsMessage = new WxMassPreviewNewsMessage();
        wxMassPreviewNewsMessage.setMpnews(new WxMassPreviewMediaId("media_id"));
        wxMassPreviewNewsMessage.setMsgtype("msg_type");
        wxMassPreviewNewsMessage.setTouser("toUser");
        log.info(wxMassPreviewNewsMessage.toString());
    }


    @Test
    public void testWxMassPreviewCardMessage(){
        WxMassPreviewCardInfoExt wxMassPreviewCardInfoExt = new WxMassPreviewCardInfoExt();
        wxMassPreviewCardInfoExt.setCode("code");
        wxMassPreviewCardInfoExt.setOpenid("openId");
        wxMassPreviewCardInfoExt.setSignature("signature");
        wxMassPreviewCardInfoExt.setTimestamp(System.currentTimeMillis() / 1000);

        WxMassPreviewCardInfo wxMassPreviewCardInfo = new WxMassPreviewCardInfo();
        wxMassPreviewCardInfo.setCard_id("card_id");
        wxMassPreviewCardInfo.setCard_ext(wxMassPreviewCardInfoExt);

        WxMassPreviewCardMessage wxMassPreviewCardMessage = new WxMassPreviewCardMessage();
        wxMassPreviewCardMessage.setMsgtype("wxcard");
        wxMassPreviewCardMessage.setWxcard(wxMassPreviewCardInfo);
        wxMassPreviewCardMessage.setTouser("toUser");
        //wxMassPreviewCardMessage.setTowxname("toWxname");

        log.info(wxMassPreviewCardMessage.toString());


    }

    @Test
    public void testXml2WxTemplateEventMessage(){
        String str = "<xml>" +
                "<ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>" +
                "<FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>" +
                "<CreateTime>1395658920</CreateTime>" +
                "<MsgType><![CDATA[event]]></MsgType>" +
                "<Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>" +
                "<MsgID>200163836</MsgID>" +
                "<Status><![CDATA[success]]></Status>" +
                "</xml>";
        log.info(str);
        WxTemplateEventMessage wxTemplateEventMessage = XStreamHandler.toBean(str, WxTemplateEventMessage.class);
        log.info(wxTemplateEventMessage.toJsonString());
        String msg = XStreamHandler.toXml(wxTemplateEventMessage);
        log.info(msg);
        log.info(String.valueOf(str.equals(msg)));
        Assert.assertEquals(str, msg);
    }
}
