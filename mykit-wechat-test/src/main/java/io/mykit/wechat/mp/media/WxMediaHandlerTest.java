package io.mykit.wechat.mp.media;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagMediaId;
import io.mykit.wechat.mp.beans.json.mass.tag.image.WxMassTagImageMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.news.WxMassTagNewsMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.text.WxMassTagTextContent;
import io.mykit.wechat.mp.beans.json.mass.tag.text.WxMassTagTextMessage;
import io.mykit.wechat.mp.beans.json.media.WxMediaUploadNews;
import io.mykit.wechat.mp.beans.json.media.WxMediaUploadNewsItem;
import io.mykit.wechat.mp.http.constants.WxConstants;
import io.mykit.wechat.mp.http.handler.media.WxMediaHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/20 09:37
 * @Description: 测试上传多媒体文件
 */
@Slf4j
public class WxMediaHandlerTest extends BaseTest {

    @Test
    public void testUploadMediaFile() throws  Exception{
        log.info(WxMediaHandler.uploadMediaFile(APPID, APPSECRET, WxConstants.TYPE_VIDEO,"d:/11.mov"));
    }
    @Test
    public void testUploadMediaFile2() throws  Exception{
        log.info(WxMediaHandler.uploadMediaFile2(APPID, APPSECRET, WxConstants.MEDIA_TYPE_IMAGE,"d:/222.jpg"));
    }

    @Test
    public void testDownloadMediaFile() throws Exception{
        File file = WxMediaHandler.downloadMediaFile(APPID, APPSECRET, "d:/123.jpg", "h0Jj3MYl83jjtqmpxNGJel3k13HCzpZQGFxmlU-AWhJpktI3GPVpcdz91ARZV3Z0");
        log.info(file.getAbsolutePath());
    }

    @Test
    public void testUploadMediaNewsFile() throws Exception{
        WxMediaUploadNewsItem wxMediaUploadNewsItem = new WxMediaUploadNewsItem();
        wxMediaUploadNewsItem.setAuthor("liuyazhuang");
        wxMediaUploadNewsItem.setContent("微信测试");
        wxMediaUploadNewsItem.setContent_source_url("http://www.baidu.com");
        wxMediaUploadNewsItem.setDigest("微信测试");
        wxMediaUploadNewsItem.setShow_cover_pic(1);
        wxMediaUploadNewsItem.setThumb_media_id("EZi2dut6i14S6M3fDuzqZdKEeGg-L61qkZkk8Uzw-W2EJtrRz_zah6hfs3XHxegY");
        wxMediaUploadNewsItem.setTitle("微信测试");
        WxMediaUploadNews wxMediaUploadNews = new WxMediaUploadNews();
        List<WxMediaUploadNewsItem> list = new ArrayList<>();
        list.add(wxMediaUploadNewsItem);
        wxMediaUploadNews.setArticles(list);
        log.info(WxMediaHandler.uploadMediaNewsFile(APPID, APPSECRET, wxMediaUploadNews));
    }

    @Test
    public void testSendMpnewsMessageByTag() throws Exception{
        WxMassTagMediaId wxMassMediaId = new WxMassTagMediaId();
        wxMassMediaId.setMedia_id("ZpXkH0pn1ITReY-JbrgYewBqve3hFOT8sudJhH2ed8xxKhYFHCA3tayF7xzgKrDY");

        WxMassTagFilter wxMassTagFilter = new WxMassTagFilter();
        wxMassTagFilter.setIs_to_all(true);

        WxMassTagNewsMessage wxMassNewsMessage  = new WxMassTagNewsMessage();
        wxMassNewsMessage.setFilter(wxMassTagFilter);
        wxMassNewsMessage.setMpnews(wxMassMediaId);
        wxMassNewsMessage.setMsgtype(WxConstants.TYPE_IMAGE);
        wxMassNewsMessage.setSend_ignore_reprint(1);

        log.info(WxMediaHandler.sendMpnewsMessageByTag(APPID, APPSECRET, wxMassNewsMessage));
    }


    @Test
    public void testSendTextMessageByTag() throws Exception{
        WxMassTagFilter wxMassTagFilter = new WxMassTagFilter();
        wxMassTagFilter.setIs_to_all(true);

        WxMassTagTextContent textContent = new WxMassTagTextContent();
        textContent.setContent("测试微信");

        WxMassTagTextMessage wxMassTagTextMessage = new WxMassTagTextMessage();
        wxMassTagTextMessage.setFilter(wxMassTagFilter);
        wxMassTagTextMessage.setMsgtype("text");
        wxMassTagTextMessage.setText(textContent);

        log.info(WxMediaHandler.sendTextMessageByTag(APPID, APPSECRET, wxMassTagTextMessage));
    }

    @Test
    public void testSendImageMessageByTag() throws Exception{
        WxMassTagFilter wxMassTagFilter = new WxMassTagFilter();
        wxMassTagFilter.setIs_to_all(true);

        WxMassTagMediaId wxMassMediaId = new WxMassTagMediaId();
        wxMassMediaId.setMedia_id("h0Jj3MYl83jjtqmpxNGJel3k13HCzpZQGFxmlU-AWhJpktI3GPVpcdz91ARZV3Z0");

        WxMassTagImageMessage wxMassTagImageMessage = new WxMassTagImageMessage();
        wxMassTagImageMessage.setFilter(wxMassTagFilter);
        wxMassTagImageMessage.setImage(wxMassMediaId);
        wxMassTagImageMessage.setMsgtype("image");

        log.info(wxMassTagImageMessage.toString());
        log.info(WxMediaHandler.sendImageMessageByTag(APPID, APPSECRET, wxMassTagImageMessage));
    }

    @Test
    public void testUploadMediaVideo() throws Exception {

    }
}
