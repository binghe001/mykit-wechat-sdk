package io.mykit.wechat.mp.user;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.user.tag.*;
import io.mykit.wechat.mp.http.handler.user.WxUserHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 09:25
 * @Description: 测试用户管理
 */
@Slf4j
public class WxUserHandlerTest extends BaseTest {

    @Test
    public void testCreateTag() throws Exception{
        //wxUserTag.setTag(wxUserTagName);
        WxUserTag wxUserTag = new WxUserTag();
        WxUserTagName wxUserTagName = new WxUserTagName();
        wxUserTagName.setName("测试标签");
        wxUserTag.setTag(wxUserTagName);
        log.info(WxUserHandler.createTag(APPID, APPSECRET, wxUserTag).toJsonString());
    }

    @Test
    public void testGetTag() throws Exception{
        log.info(WxUserHandler.getTag(APPID, APPSECRET).toJsonString());
    }

    @Test
    public void testUpdateTag() throws Exception{
        WxUserTagCreateResp wxUserTagCreateResp = new WxUserTagCreateResp();
        WxUserTagName wxUserTagName = new WxUserTagName();
        wxUserTagName.setId(100);
        wxUserTagName.setName("用户标签");
        wxUserTagCreateResp.setTag(wxUserTagName);
        log.info(WxUserHandler.updateTag(APPID, APPSECRET, wxUserTagCreateResp).toJsonString());
    }

    @Test
    public void testGetWxUseInfo() throws Exception{
        WxUserTagLang wxUserTagLang = new WxUserTagLang();
        wxUserTagLang.setLang("zh_CN");
        wxUserTagLang.setOpenid("olhDZjvl7GADzpmkJpqqgiSzAp5M");
        log.info(wxUserTagLang.toMap().toString());
        log.info(WxUserHandler.getWxUseInfo(APPID, APPSECRET, wxUserTagLang).toJsonString());
    }

    @Test
    public void testGetWxUserGetList() throws Exception{
        WxUserTagOpenId wxUserTagOpenId = new WxUserTagOpenId();
        log.info(wxUserTagOpenId.toMap().toString());
        log.info(WxUserHandler.getWxUserGetList(APPID, APPSECRET, wxUserTagOpenId).toJsonString());
    }
    @Test
    public void testGetWxUserBlackList() throws Exception{
        WxUserTagOpenId wxUserTagOpenId = new WxUserTagOpenId();
        log.info(wxUserTagOpenId.toMap().toString());
        log.info(WxUserHandler.getUserBlackList(APPID, APPSECRET, wxUserTagOpenId).toJsonString());
    }
}
