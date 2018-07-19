package io.mykit.wechat.mp.kfaccount;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.beans.json.kfaccount.WxKfaccountBean;
import io.mykit.wechat.mp.http.handler.kfaccount.WxKfaccountHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
}
