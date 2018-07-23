package io.mykit.wechat.mp.mass;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.mass.speed.WxMassSpeed;
import io.mykit.wechat.mp.http.handler.mass.WxMassHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/23 16:30
 * @Description: 测试群发接口
 */
@Slf4j
public class MassTest extends BaseTest {

    @Test
    public void testGetMassSpeed() throws Exception{
        log.info(WxMassHandler.getMassSpeed(APPID, APPSECRET));
    }

    @Test
    public void testSetMassSpeed() throws Exception{
        log.info(WxMassHandler.setMassSpeed(APPID, APPSECRET, new WxMassSpeed(1)));
    }
}
