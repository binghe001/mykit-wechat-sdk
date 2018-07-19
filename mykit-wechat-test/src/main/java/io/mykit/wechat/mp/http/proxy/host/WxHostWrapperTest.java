package io.mykit.wechat.mp.http.proxy.host;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.http.handler.host.WxHostHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 20:34
 * @Description: 测试获取微信服务器列表的数据
 */
@Slf4j
public class WxHostWrapperTest extends BaseTest {

    @Test
    public void testGetWxHosts() throws Exception{
        String ret = WxHostHandler.getHostList(APPID, APPSECRET);
        log.info(ret);
    }
}
