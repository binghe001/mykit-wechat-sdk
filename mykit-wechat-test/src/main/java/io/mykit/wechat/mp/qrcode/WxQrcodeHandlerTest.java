package io.mykit.wechat.mp.qrcode;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeForever;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeSence;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeSenceId;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeTemporary;
import io.mykit.wechat.mp.http.handler.qrcode.WxQrcodeHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

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
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_id(123);
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeForever wxQrcodeForever = new WxQrcodeForever();
        wxQrcodeForever.setAction_info(wxQrcodeSence);
        wxQrcodeForever.setAction_name("QR_LIMIT_SCENE");
        log.info(wxQrcodeForever.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeForever(APPID, APPSECRET, wxQrcodeForever).toJsonString());
    }
    @Test
    public void testCreateWxQrcodeForeverBySenceStr() throws Exception{
        //{"errcode":0,"errmsg":"","ticket":"gQE78DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAydk5tLThsbWdicGUxMDAwME0wN0wAAgR1xlpbAwQAAAAA","url":"http://weixin.qq.com/q/02vNm-8lmgbpe10000M07L"}
         WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_str("sence_123");
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeForever wxQrcodeForever = new WxQrcodeForever();
        wxQrcodeForever.setAction_info(wxQrcodeSence);
        wxQrcodeForever.setAction_name("QR_LIMIT_STR_SCENE");
        log.info(wxQrcodeForever.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeForever(APPID, APPSECRET, wxQrcodeForever).toJsonString());
    }
    @Test
    public void testCreateWxQrcodeTemporaryBySenceId() throws Exception{
        //{"errcode":0,"errmsg":"","expire_seconds":2592000,"ticket":"gQE-8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUkpFajkzbWdicGUxQWFrMnhyMWMAAgQKx1pbAwQAjScA","url":"http://weixin.qq.com/q/02RJEj93mgbpe1Aak2xr1c"}
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_id(123);
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeTemporary wxQrcodeTemporary = new WxQrcodeTemporary();
        wxQrcodeTemporary.setAction_info(wxQrcodeSence);
        wxQrcodeTemporary.setAction_name("QR_SCENE");
        wxQrcodeTemporary.setExpire_seconds(30 * 24 * 60 * 60);
        log.info(wxQrcodeTemporary.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeTemporary(APPID, APPSECRET, wxQrcodeTemporary).toJsonString());
    }
    @Test
    public void testCreateWxQrcodeTemporaryBySenceStr() throws Exception{
        //{"errcode":0,"errmsg":"","expire_seconds":2592000,"ticket":"gQEU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOVVDODlBbWdicGUxQXVrMnhyY2gAAgQex1pbAwQAjScA","url":"http://weixin.qq.com/q/029UC89Amgbpe1Auk2xrch"}
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_str("sence_123");
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeTemporary wxQrcodeTemporary = new WxQrcodeTemporary();
        wxQrcodeTemporary.setAction_info(wxQrcodeSence);
        wxQrcodeTemporary.setAction_name("QR_STR_SCENE");
        wxQrcodeTemporary.setExpire_seconds(30 * 24 * 60 * 60);
        log.info(wxQrcodeTemporary.toJsonString());
        log.info(WxQrcodeHandler.createWxQrcodeTemporary(APPID, APPSECRET, wxQrcodeTemporary).toJsonString());
    }

    @Test
    public void testDownloadQrcode() throws Exception{
        WxQrcodeHandler.downloadQrcode("d:/yj_str.jpg", "gQE78DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAydk5tLThsbWdicGUxMDAwME0wN0wAAgR1xlpbAwQAAAAA");
        WxQrcodeHandler.downloadQrcode("d:/yj_id.jpg", "gQEl8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQXVfNDlObWdicGUxMDAwMHcwM0EAAgSjxVpbAwQAAAAA");
        WxQrcodeHandler.downloadQrcode("d:/ls_str.jpg", "gQEU8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOVVDODlBbWdicGUxQXVrMnhyY2gAAgQex1pbAwQAjScA");
        WxQrcodeHandler.downloadQrcode("d:/ls_id.jpg", "gQE-8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUkpFajkzbWdicGUxQWFrMnhyMWMAAgQKx1pbAwQAjScA");
    }
}
