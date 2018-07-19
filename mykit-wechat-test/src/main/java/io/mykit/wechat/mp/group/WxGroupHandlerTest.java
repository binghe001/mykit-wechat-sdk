package io.mykit.wechat.mp.group;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.http.handler.group.WxGroupHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/19 18:10
 * @Description: 微信群发消息测试
 */
@Slf4j
public class WxGroupHandlerTest extends BaseTest {

    @Test
    public void testUploadImg() throws Exception{
        log.info(WxGroupHandler.uploadImg(APPID, APPSECRET, "d:/222.jpg"));
    }
}
