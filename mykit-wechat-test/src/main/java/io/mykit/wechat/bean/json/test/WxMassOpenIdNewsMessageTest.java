package io.mykit.wechat.bean.json.test;

import io.mykit.wechat.mp.beans.json.mass.openid.WxMassOpenIdMediaId;
import io.mykit.wechat.mp.beans.json.mass.openid.news.WxMassOpenIdNewsMassage;
import io.mykit.wechat.mp.beans.json.mass.openid.video.WxMassOpenIdMedia;
import io.mykit.wechat.mp.beans.json.mass.openid.video.WxMassOpenIdVideoMessage;
import lombok.extern.slf4j.Slf4j;
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
public class WxMassOpenIdNewsMessageTest {


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
}
