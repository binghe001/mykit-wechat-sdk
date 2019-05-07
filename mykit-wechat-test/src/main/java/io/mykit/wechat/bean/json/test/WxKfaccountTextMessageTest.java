/**
 * Copyright 2019-2999 the original author or authors.
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
package io.mykit.wechat.bean.json.test;

import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.kfaccount.message.WxKfaccountTextMessage;
import io.mykit.wechat.mp.beans.json.kfaccount.message.WxKfaccountTextMessageItem;
import io.mykit.wechat.mp.http.handler.kfaccount.WxKfaccountHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author liuyazhuang
 * @version 1.0.0
 * @date 2019/5/7
 * @description 测试微信客服文本消息数据结构
 */
@Slf4j
public class WxKfaccountTextMessageTest extends BaseTest {

    private WxKfaccountTextMessage getWxKfaccountTextMessage(){
        WxKfaccountTextMessageItem wxKfaccountTextMessageItem = new WxKfaccountTextMessageItem();
        wxKfaccountTextMessageItem.setContent("hello world");
        WxKfaccountTextMessage wxKfaccountTextMessage = new WxKfaccountTextMessage();
        wxKfaccountTextMessage.setText(wxKfaccountTextMessageItem);
        wxKfaccountTextMessage.setMsgtype("text");
        wxKfaccountTextMessage.setTouser("olhDZjpv-GZmITBOwBeb6t8KNPkw");
        return wxKfaccountTextMessage;
    }

    @Test
    public void testWxKfaccountTextMessage(){
        WxKfaccountTextMessage wxKfaccountTextMessage = getWxKfaccountTextMessage();
        log.info(wxKfaccountTextMessage.toJsonString());
    }

    @Test
    public void testSendWxKfaccountTextMessage() throws Exception{
        WxKfaccountTextMessage wxKfaccountTextMessage = getWxKfaccountTextMessage();
        String ret = WxKfaccountHandler.sendWxKfaccountTextMessage(APPID, APPSECRET, wxKfaccountTextMessage);
        log.info(ret);
    }
}
