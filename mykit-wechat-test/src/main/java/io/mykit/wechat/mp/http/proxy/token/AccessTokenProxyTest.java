package io.mykit.wechat.mp.http.proxy.token;

import io.mykit.wechat.mp.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 17:05
 * @Description: 测试获取AccessToken
 */
@Slf4j
public class AccessTokenProxyTest extends BaseTest {
    @Test
    public void testGetAccessToken() throws Exception{
        log.info(AccessTokenProxy.getAccessToken(APPID, APPSECRET));
    }
}
