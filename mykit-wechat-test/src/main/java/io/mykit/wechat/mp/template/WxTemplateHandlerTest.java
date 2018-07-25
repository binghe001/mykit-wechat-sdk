package io.mykit.wechat.mp.template;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.template.industry.WxTemplateIndustry;
import io.mykit.wechat.mp.beans.json.template.send.WxTemplateDataItemSend;
import io.mykit.wechat.mp.beans.json.template.send.WxTemplateDataSend;
import io.mykit.wechat.mp.beans.json.template.send.WxTemplateMiniprogramSend;
import io.mykit.wechat.mp.beans.json.template.send.WxTemplateSend;
import io.mykit.wechat.mp.http.handler.template.WxTemplateHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 09:41
 * @Description: 测试模板消息
 */
@Slf4j
public class WxTemplateHandlerTest extends BaseTest {

    @Test
    public void testGetIndustry() throws Exception{
        log.info(WxTemplateHandler.getIndustry(APPID, APPSECRET));
    }

    @Test
    public void testSetIndustry() throws Exception{
        WxTemplateIndustry wxIndustry = new WxTemplateIndustry();
        wxIndustry.setIndustry_id1("2");
        wxIndustry.setIndustry_id2("41");
        log.info(WxTemplateHandler.setIndustry(APPID, APPSECRET, wxIndustry));
    }

    @Test
    public void testAllTemplate() throws Exception{
        log.info(WxTemplateHandler.allTemplate(APPID, APPSECRET));
    }

    @Test
    public void testSendTemplate() throws Exception{
        WxTemplateDataSend wxTemplateDataSend = new WxTemplateDataSend();
        wxTemplateDataSend.setFirst(new WxTemplateDataItemSend("欢迎测试模板消息", "#173177"));
        wxTemplateDataSend.setKeyword1(new WxTemplateDataItemSend("测试成功", "#173177"));
        wxTemplateDataSend.setKeyword2(new WxTemplateDataItemSend("测试成功", "#173177"));
        wxTemplateDataSend.setKeyword3(new WxTemplateDataItemSend("测试成功", "#173177"));
        wxTemplateDataSend.setRemark(new WxTemplateDataItemSend("欢迎再次测试", "#173177"));

//        WxTemplateMiniprogramSend wxTemplateMiniprogramSend = new WxTemplateMiniprogramSend();
//        wxTemplateMiniprogramSend.setAppid("appid");
//        wxTemplateMiniprogramSend.setPagepath("pagepath");

        WxTemplateSend wxTemplateSend = new WxTemplateSend();
        wxTemplateSend.setData(wxTemplateDataSend);
//        wxTemplateSend.setMiniprogram(wxTemplateMiniprogramSend);
        wxTemplateSend.setTemplate_id("3zvJ6CV_Pqy9BK43N_8r5-kt5dwra7OQ_MLHsSyxXko");
        wxTemplateSend.setTouser("olhDZjvl7GADzpmkJpqqgiSzAp5M");
        wxTemplateSend.setUrl("https://www.baidu.com");

        log.info(wxTemplateSend.toJsonString());

        log.info(WxTemplateHandler.sendTemplate(APPID, APPSECRET, wxTemplateSend));
    }
}
