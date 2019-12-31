package io.mykit.wechat.mp.qrcode;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.factory.json.QrcodeParamsFactory;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeForever;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeTemporary;
import io.mykit.wechat.mp.http.handler.qrcode.WxQrcodeHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/27 13:55
 * @Description: 测试微信二维码
 */
@Slf4j
public class WxQrcodeHandlerTest extends BaseTest {

    @Test
    public void testCreateWxQrcodeForeverBySenceId() throws Exception{
        //{"errcode":0,"errmsg":"","ticket":"gQEl8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQXVfNDlObWdicGUxMDAwMHcwM0EAAgSjxVpbAwQAAAAA","url":"http://weixin.qq.com/q/02Au_49Nmgbpe10000w03A"}
        WxQrcodeForever wxQrcodeForever = QrcodeParamsFactory.getWxQrcodeForeverByIntParams(123);
        log.info(wxQrcodeForever.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeForever(APPID, APPSECRET, wxQrcodeForever).toJsonString());
    }
    @Test//使用这个测试
    public void testCreateWxQrcodeForeverBySenceStr() throws Exception{
        //{"errcode":0,"errmsg":"","ticket":"gQHn8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyemg1cjlHbWdicGUxMDAwMHcwNzcAAgSqIABeAwQAAAAA","url":"http://weixin.qq.com/q/02zh5r9Gmgbpe10000w077"}
        WxQrcodeForever wxQrcodeForever = QrcodeParamsFactory.getWxQrcodeForeverByStringParams("8a8383ab6f3250d8016f3720e80a0014");
        log.info(wxQrcodeForever.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeForever(APPID, APPSECRET, wxQrcodeForever).toJsonString());
    }
    @Test
    public void testCreateWxQrcodeTemporaryBySenceId() throws Exception{
        //{"errcode":0,"errmsg":"","expire_seconds":2592000,"ticket":"gQE-8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUkpFajkzbWdicGUxQWFrMnhyMWMAAgQKx1pbAwQAjScA","url":"http://weixin.qq.com/q/02RJEj93mgbpe1Aak2xr1c"}
        WxQrcodeTemporary wxQrcodeTemporary = QrcodeParamsFactory.getWxQrcodeTemporaryByIntParams(123, (30 * 24 * 60 * 60));
        log.info(wxQrcodeTemporary.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeTemporary(APPID, APPSECRET, wxQrcodeTemporary).toJsonString());
    }
    @Test
    public void testCreateWxQrcodeTemporaryBySenceStr() throws Exception{
        //{"errcode":0,"errmsg":"","expire_seconds":2592000,"ticket":"gQEU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOVVDODlBbWdicGUxQXVrMnhyY2gAAgQex1pbAwQAjScA","url":"http://weixin.qq.com/q/029UC89Amgbpe1Auk2xrch"}
        WxQrcodeTemporary wxQrcodeTemporary = QrcodeParamsFactory.getWxQrcodeTemporaryByStringParams("sence_123", (30 * 24 * 60 * 60));
        log.info(wxQrcodeTemporary.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeTemporary(APPID, APPSECRET, wxQrcodeTemporary).toJsonString());
    }

    @Test
    public void testDownloadQrcode() throws Exception{
        WxQrcodeHandler.downloadQrcode("d:/yj_str.jpg", "gQHD8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAySjlqcjgxZFlkdjMxMDAwME0wN2wAAgSP1QFeAwQAAAAA");
//        WxQrcodeHandler.downloadQrcode("d:/yj_id.jpg", "gQEl8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQXVfNDlObWdicGUxMDAwMHcwM0EAAgSjxVpbAwQAAAAA");
//        WxQrcodeHandler.downloadQrcode("d:/ls_str.jpg", "gQEU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOVVDODlBbWdicGUxQXVrMnhyY2gAAgQex1pbAwQAjScA");
//        WxQrcodeHandler.downloadQrcode("d:/ls_id.jpg", "gQE-8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUkpFajkzbWdicGUxQWFrMnhyMWMAAgQKx1pbAwQAjScA");
    }
}
