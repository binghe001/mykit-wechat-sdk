/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.wechat.mp.beans.json.factory.json;

import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeForever;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeSence;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeSenceId;
import io.mykit.wechat.mp.beans.json.qrcode.WxQrcodeTemporary;

/**
 * @author binghe
 * @version 1.0.0
 * @description 请求微信生成二维码的参数
 */
public class QrcodeParamsFactory {

    /**
     * 生成永久二维码，传递Int类型参数
     */
    public static WxQrcodeForever getWxQrcodeForeverByIntParams(Integer qrcodeParams){
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_id(qrcodeParams);
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeForever wxQrcodeForever = new WxQrcodeForever();
        wxQrcodeForever.setAction_info(wxQrcodeSence);
        wxQrcodeForever.setAction_name("QR_LIMIT_SCENE");
        return wxQrcodeForever;
    }

    /**
     * 生成永久二维码，传递Spring类型的参数
     */
    public static WxQrcodeForever getWxQrcodeForeverByStringParams(String qrcodeParams){
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_str(qrcodeParams);
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeForever wxQrcodeForever = new WxQrcodeForever();
        wxQrcodeForever.setAction_info(wxQrcodeSence);
        wxQrcodeForever.setAction_name("QR_LIMIT_STR_SCENE");
        return wxQrcodeForever;
    }

    /**
     * 生成临时二维码，传递Int类型的参数和有效时间
     */
    public static WxQrcodeTemporary getWxQrcodeTemporaryByIntParams(Integer qrcoceParams, Integer expireTime){
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_id(qrcoceParams);
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeTemporary wxQrcodeTemporary = new WxQrcodeTemporary();
        wxQrcodeTemporary.setAction_info(wxQrcodeSence);
        wxQrcodeTemporary.setAction_name("QR_SCENE");
        wxQrcodeTemporary.setExpire_seconds(expireTime);
        return wxQrcodeTemporary;
    }

    /**
     * 生成临时二维码，传递String类型的参数和有效时间
     */
    public static WxQrcodeTemporary getWxQrcodeTemporaryByStringParams(String qrcoceParams, Integer expireTime){
        WxQrcodeSenceId wxQrcodeSenceId = new WxQrcodeSenceId();
        wxQrcodeSenceId.setScene_str(qrcoceParams);
        WxQrcodeSence wxQrcodeSence = new WxQrcodeSence(wxQrcodeSenceId);
        WxQrcodeTemporary wxQrcodeTemporary = new WxQrcodeTemporary();
        wxQrcodeTemporary.setAction_info(wxQrcodeSence);
        wxQrcodeTemporary.setAction_name("QR_STR_SCENE");
        wxQrcodeTemporary.setExpire_seconds(expireTime);
        return wxQrcodeTemporary;
    }
}
