package io.mykit.wechat.mp.media;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagFilter;
import io.mykit.wechat.mp.beans.json.mass.tag.WxMassTagMediaId;
import io.mykit.wechat.mp.beans.json.mass.tag.image.WxMassTagImageMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.news.WxMassTagNewsMessage;
import io.mykit.wechat.mp.beans.json.mass.tag.text.WxMassTagTextContent;
import io.mykit.wechat.mp.beans.json.mass.tag.text.WxMassTagTextMessage;
import io.mykit.wechat.mp.beans.json.media.news.WxMediaNewsAdd;
import io.mykit.wechat.mp.beans.json.media.news.WxMediaNewsAddItem;
import io.mykit.wechat.mp.beans.json.media.news.WxNewsBatchGet;
import io.mykit.wechat.mp.beans.json.media.upload.WxMediaUpdateNews;
import io.mykit.wechat.mp.beans.json.media.upload.WxMediaUploadNews;
import io.mykit.wechat.mp.beans.json.media.upload.WxMediaUploadNewsItem;
import io.mykit.wechat.mp.beans.map.WxMediaId;
import io.mykit.wechat.mp.http.constants.WxConstants;
import io.mykit.wechat.mp.http.handler.mass.WxMassHandler;
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
        log.info(WxMediaHandler.uploadMediaFile(APPID, APPSECRET, WxConstants.TYPE_IMAGE,"d:/222.jpg"));
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
        log.info(WxMassHandler.uploadMediaNewsFile(APPID, APPSECRET, wxMediaUploadNews));
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

        log.info(WxMassHandler.sendMpnewsMessageByTag(APPID, APPSECRET, wxMassNewsMessage));
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

        log.info(WxMassHandler.sendTextMessageByTag(APPID, APPSECRET, wxMassTagTextMessage));
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
        log.info(WxMassHandler.sendImageMessageByTag(APPID, APPSECRET, wxMassTagImageMessage));
    }

    @Test
    public void testGetMediaFile() throws Exception {
       File file = WxMediaHandler.getMediaFile(APPID, APPSECRET, "d:/444.jpg", "DX21cpocQDY3Yx4nlzozi18w6p7993zDE_-hatutzKHoT_OlLY7W9mMA1dOxCeAd");
       System.out.println(file.getAbsolutePath());
    }

    @Test
    public void testUploadImage() throws Exception{
        //String appid, String secret, String filePath
        //{"url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/hGYoeBADAANM1qd3ibYvnjuoZmGG44ibobic54uyw2pnevlqktlhAQSnWPzzpLREOAf3JRdgPmRxvQ0oS96shEmqA\/0"}
        log.info(WxMediaHandler.uploadImg(APPID, APPSECRET, "d:/222.jpg"));
    }

    @Test
    public void testUploadNewsMaterial() throws Exception{
        //{"media_id":"J5i3FLnqTZtULBc7PIhJB11E1h1SdRrvy0wXrf_1rTc","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/hGYoeBADAANM1qd3ibYvnjuoZmGG44ibobic54uyw2pnevlqktlhAQSnWPzzpLREOAf3JRdgPmRxvQ0oS96shEmqA\/0?wx_fmt=jpeg"}
        log.info(WxMediaHandler.uploadNewsMaterial(APPID, APPSECRET, WxConstants.TYPE_IMAGE, "d:/222.jpg"));
    }

    @Test
    public void testAddNews() throws Exception{
        //{"media_id":"J5i3FLnqTZtULBc7PIhJB79F9cp7-OnKoDYk9EgKYi8"}
        WxMediaNewsAddItem wxMediaNewsAddItem  = new WxMediaNewsAddItem();
        wxMediaNewsAddItem.setAuthor("刘亚壮");
        wxMediaNewsAddItem.setContent("测试图文素材消息，测试图文素材消息，测试图文素材消息，测试图文素材消息，测试图文素材消息<img src=\"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/hGYoeBADAANM1qd3ibYvnjuoZmGG44ibobic54uyw2pnevlqktlhAQSnWPzzpLREOAf3JRdgPmRxvQ0oS96shEmqA\\/0?wx_fmt=jpeg\"/>");
        wxMediaNewsAddItem.setContent_source_url("https://www.baidu.com");
        wxMediaNewsAddItem.setDigest("测试图文消息");
        wxMediaNewsAddItem.setShow_cover_pic(1);
        wxMediaNewsAddItem.setThumb_media_id("J5i3FLnqTZtULBc7PIhJB11E1h1SdRrvy0wXrf_1rTc");
        wxMediaNewsAddItem.setTitle("测试图文消息");

        List<WxMediaNewsAddItem> list = new ArrayList<>();
        list.add(wxMediaNewsAddItem);

        WxMediaNewsAdd wxMediaNewsAdd = new WxMediaNewsAdd();
        wxMediaNewsAdd.setArticles(list);
        log.info(wxMediaNewsAdd.toJsonString());
        log.info(WxMediaHandler.addNews(APPID, APPSECRET, wxMediaNewsAdd));
    }

    @Test
    public void testGetOtherMaterail() throws Exception{
        File file = WxMediaHandler.getOtherMaterail(APPID, APPSECRET, "d:/456.jpg", new WxMediaId("J5i3FLnqTZtULBc7PIhJB11E1h1SdRrvy0wXrf_1rTc"));
        log.info(file.getAbsolutePath());
    }

    @Test
    public void testGetNewsOrVideoMaterail() throws Exception{
        log.info(WxMediaHandler.getNewsOrVideoMaterail(APPID, APPSECRET, new WxMediaId("J5i3FLnqTZtULBc7PIhJB79F9cp7-OnKoDYk9EgKYi8")));
    }

    @Test
    public void testUpdateMaterail() throws Exception{
        WxMediaUpdateNews wxMediaUpdateNews = new WxMediaUpdateNews();
        wxMediaUpdateNews.setIndex(0);
        wxMediaUpdateNews.setMedia_id("J5i3FLnqTZtULBc7PIhJB79F9cp7-OnKoDYk9EgKYi8");

        WxMediaNewsAddItem wxMediaNewsAddItem  = new WxMediaNewsAddItem();
        wxMediaNewsAddItem.setAuthor("刘亚壮");
        wxMediaNewsAddItem.setContent("测试图文素材消息，测试图文素材消息，测试图文素材消息，测试图文素材消息，测试图文素材消息<img src=\"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/hGYoeBADAANM1qd3ibYvnjuoZmGG44ibobic54uyw2pnevlqktlhAQSnWPzzpLREOAf3JRdgPmRxvQ0oS96shEmqA\\/0?wx_fmt=jpeg\"/>");
        wxMediaNewsAddItem.setContent_source_url("https://www.google.com");
        wxMediaNewsAddItem.setDigest("测试图文消息");
        wxMediaNewsAddItem.setShow_cover_pic(1);
        wxMediaNewsAddItem.setThumb_media_id("J5i3FLnqTZtULBc7PIhJB11E1h1SdRrvy0wXrf_1rTc");
        wxMediaNewsAddItem.setTitle("测试图文消息");
        wxMediaUpdateNews.setArticles(wxMediaNewsAddItem);

        log.info(WxMediaHandler.updateMaterail(APPID, APPSECRET, wxMediaUpdateNews));
    }

    @Test
    public void testCountMaterail() throws Exception{
        log.info(WxMediaHandler.countMaterail(APPID, APPSECRET));
    }

    @Test
    public void testBatchGetMaterail() throws Exception{
        WxNewsBatchGet wxNewsBatchGet = new WxNewsBatchGet();
        wxNewsBatchGet.setCount(20);
        wxNewsBatchGet.setOffset(0);
        wxNewsBatchGet.setType(WxConstants.TYPE_NEWS);
        log.info(WxMediaHandler.batchGetMaterail(APPID, APPSECRET, wxNewsBatchGet));
    }
}
