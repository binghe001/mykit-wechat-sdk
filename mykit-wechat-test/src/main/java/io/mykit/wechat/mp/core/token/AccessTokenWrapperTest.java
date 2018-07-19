package io.mykit.wechat.mp.core.token;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.http.handler.token.AccessTokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 17:31
 * @Description:
 */

@Slf4j
public class AccessTokenWrapperTest extends BaseTest {

    @Test
    public void testGetAccessToken()throws Exception{
        String access_token = AccessTokenHandler.getAccessToken(APPID, APPSECRET);
        log.info(access_token);
    }
}
