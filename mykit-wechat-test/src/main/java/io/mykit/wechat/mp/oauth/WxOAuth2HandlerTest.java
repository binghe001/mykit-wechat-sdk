package io.mykit.wechat.mp.oauth;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.oauth.WxOAuth2Code;
//import io.mykit.wechat.mp.http.handler.oauth.WxOAuth2Handler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/31 13:36
 * @Description: 网页授权
 */
@Slf4j
public class WxOAuth2HandlerTest extends BaseTest {

    @Test
    public void testGetUserInfo() throws Exception{
        WxOAuth2Code wxOAuth2Code = new WxOAuth2Code();
        wxOAuth2Code.setCode("123");
//        log.info(WxOAuth2Handler.getUserInfo(APPID, APPSECRET, wxOAuth2Code).toJsonString());
    }
}
