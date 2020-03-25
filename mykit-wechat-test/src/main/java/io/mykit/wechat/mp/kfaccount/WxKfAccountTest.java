package io.mykit.wechat.mp.kfaccount;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.kfaccount.WxKfaccountBean;
import io.mykit.wechat.mp.beans.json.kfaccount.message.news.WxKfaccountNewsArticlesItemsMessage;
import io.mykit.wechat.mp.beans.json.kfaccount.message.news.WxKfaccountNewsArticlesMessage;
import io.mykit.wechat.mp.beans.json.kfaccount.message.news.WxKfaccountNewsMessage;
import io.mykit.wechat.mp.http.handler.kfaccount.WxKfaccountHandler;
import io.mykit.wechat.utils.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 17:10
 * @Description: 微信客服测试
 */
@Slf4j
public class WxKfAccountTest extends BaseTest {

    @Test
    public void testAddKfaccount() throws Exception{
        WxKfaccountBean wxKfaccountBean = new WxKfaccountBean();
        wxKfaccountBean.setKf_account("test1@test");
        wxKfaccountBean.setNickname("客服1");
        wxKfaccountBean.setPassword("pswmd5");

        WxCode wxCode = WxKfaccountHandler.addKfaccount(APPID, APPSECRET, wxKfaccountBean);
        log.info(wxCode.toString());
    }

    @Test
    public void testGetKflist() throws Exception{
        log.info(WxKfaccountHandler.getKflist(APPID, APPSECRET));
    }

    private WxKfaccountNewsMessage getWxKfaccountNewsMessage(){
        WxKfaccountNewsArticlesItemsMessage wxKfaccountNewsArticlesItemsMessage = new WxKfaccountNewsArticlesItemsMessage();
        wxKfaccountNewsArticlesItemsMessage.setDescription("关心健康直播预告");
        wxKfaccountNewsArticlesItemsMessage.setPicurl("http://wx.cdmn.com/resources/image/20200320/1584690186529_15331.jpg?server=image");
        wxKfaccountNewsArticlesItemsMessage.setTitle("关心健康直播预告：心血管病人自我管理与防护怎么做？");
        wxKfaccountNewsArticlesItemsMessage.setUrl("http://wx.cdmn.com/html/medcare/dest/client/service/newsDetail.html?id=8a8383ab70995a3a0170f6df92ad09ba");

        WxKfaccountNewsArticlesMessage wxKfaccountNewsArticlesMessage = new WxKfaccountNewsArticlesMessage();
        wxKfaccountNewsArticlesMessage.setArticles(Arrays.asList(wxKfaccountNewsArticlesItemsMessage));

        WxKfaccountNewsMessage wxKfaccountNewsMessage = new WxKfaccountNewsMessage();
        wxKfaccountNewsMessage.setNews(wxKfaccountNewsArticlesMessage);
        wxKfaccountNewsMessage.setMsgtype("news");
        wxKfaccountNewsMessage.setTouser("ojTxfw9P2e6BDavqBFq7G_vwASLg");
        return wxKfaccountNewsMessage;
    }

    @Test
    public void testWxKfaccountNewsMessage(){
        log.info(JsonUtils.bean2Json(getWxKfaccountNewsMessage()));
    }



    @Test
    public void sendKfMessage() throws Exception{
        String result = WxKfaccountHandler.sendWxKfaccountNewsMessage(APPID, APPSECRET, getWxKfaccountNewsMessage());
        log.info(result);
    }
}
