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
package io.mykit.wechat.mp.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.mykit.wechat.mp.base.BaseTest;
import io.mykit.wechat.mp.beans.json.code.WxCode;
import io.mykit.wechat.mp.http.handler.menu.WxMenuHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author binghe
 * @version 1.0.0
 * @description 新增自定义菜单测试类
 */
@Slf4j
public class WechatMenuTest extends BaseTest {

    @Test
    public void testCreateWechatMenu(){
        JSONArray buttonArray = new JSONArray();
        JSONObject subButton1 = new JSONObject();
        JSONArray subButtonArray1 = new JSONArray();

        JSONObject subButtonItem1 = new JSONObject();
        subButtonItem1.put("type", "view");
        //subButtonItem1.put("name", "签到有礼");
        //subButtonItem1.put("url", "https://14ldroa8.haoyebao.com/v2/index.php?act=tag&store_id=14ldroa8&tag_id=14ldw5b2&p_branch_id=14ldtnlu");

        subButtonItem1.put("name", "疫情动态");
        subButtonItem1.put("url", "http://t.cn/A6PTpXwL");
        subButtonArray1.add(subButtonItem1);

        JSONObject subButtonItem2 = new JSONObject();
        subButtonItem2.put("type", "view");
        subButtonItem2.put("name", "健康课程");
        subButtonItem2.put("url", "https://m.lizhiweike.com/liveroom/24093526");
        subButtonArray1.add(subButtonItem2);

        JSONObject subButtonItem3 = new JSONObject();
        subButtonItem3.put("type", "view");
        subButtonItem3.put("name", "健康严选");
        subButtonItem3.put("url", "https://14ldroa8.haoyebao.com/v2/index.php?act=store_home&op=micropage&id=14leqzbo&store_id=14ldroa8");
        subButtonArray1.add(subButtonItem3);


        JSONObject subButtonItem4 = new JSONObject();
        subButtonItem4.put("type", "view");
        subButtonItem4.put("name", "百科知识");
        subButtonItem4.put("url", "http://cluster.cardiochina.net/baike.html");
        subButtonArray1.add(subButtonItem4);

        //subButton1.put("name", "关心课堂");
        subButton1.put("name", "疫情动态");
        subButton1.put("sub_button", subButtonArray1);

        buttonArray.add(subButton1);


        JSONObject subButton2 = new JSONObject();
        JSONArray subButtonArray2 = new JSONArray();


        JSONObject subButtonItem5 = new JSONObject();
        subButtonItem5.put("type", "view");
        subButtonItem5.put("name", "预约挂号");
        subButtonItem5.put("url", "http://wx.cdmn.com/reservationService/index.html?hospitalId=8a8383af60747eb8016096fdb3dd0144#/service");
        subButtonArray2.add(subButtonItem5);

        JSONObject subButtonItem6 = new JSONObject();
        subButtonItem6.put("type", "view");
        subButtonItem6.put("name", "专家服务");
        subButtonItem6.put("url", "http://wx.cdmn.com/medcare/dest/client/fdSignWX/index.html?hospitalId=8a8383a458dd38d40158ddd759a10004");
        subButtonArray2.add(subButtonItem6);

        JSONObject subButtonItem7 = new JSONObject();
        subButtonItem7.put("type", "view");
//        subButtonItem7.put("name", "社区问诊");
//        subButtonItem7.put("url", "http://wx.cdmn.com/fdConsultWX/minHtml/login.html?hospitalId=8a8383af60747eb8016096fdb3dd0144");
        subButtonItem7.put("name", "免费咨询");
        subButtonItem7.put("url", "http://wx.cdmn.com/fdConsultWX/minHtml/teamDetail.html?teamId=8a8383a761413afa0161417ac7d7000b&isSign=&askBodyType=type_team&askBodyId=8a8383a761413afa0161417ac7d7000b&askBodyName=6218201C75AB201D514D8D394E498BCA670D52A156E2961F&askType=type_charge&hospitalId=8a8383af60747eb8016096fdb3dd0144");
        subButtonArray2.add(subButtonItem7);

        JSONObject subButtonItem8 = new JSONObject();
        subButtonItem8.put("type", "view");
        subButtonItem8.put("name", "预约服务");
        subButtonItem8.put("url", "http://wx.cdmn.com/medcare/dest/client/fdSignWX/districtIndexCopy.html?from=singlemessage&isappinstalled=0");
        subButtonArray2.add(subButtonItem8);


        JSONObject subButtonItem9 = new JSONObject();
        subButtonItem9.put("type", "view");
        subButtonItem9.put("name", "合作机构");
        subButtonItem9.put("url", "http://wx.cdmn.com/medcare/dest/client/fdSignWX/districtIndex.html");
        subButtonArray2.add(subButtonItem9);



        subButton2.put("name", "战疫咨询");
        //subButton2.put("name", "医疗服务");
        subButton2.put("sub_button", subButtonArray2);

        buttonArray.add(subButton2);


        JSONObject subButton3 = new JSONObject();
        JSONArray subButtonArray3 = new JSONArray();


/*       JSONObject subButtonItem10 = new JSONObject();
        subButtonItem10.put("type", "miniprogram");
        subButtonItem10.put("name", "健康记录");
        subButtonItem10.put("pagepath", "pages/homes/home/home");
        subButtonItem10.put("appid", "wxe21d13d082e71cca");
        subButtonItem10.put("url", "https://mp.weixin.qq.com/s?__biz=MzI3Nzc5ODU0MQ==&tempkey=MTAwOF9BQ3plK0NWcEpOSzM0WTFoVGpmd3FnXy1XUXhNeEVYY010Z2NkRE5Qb0ZvVEpVTkdyYllpbUtpMVd3ekVOdGk5RkZEa2VJSmRicUtiRktPQW5NY3BlLVI1eWVfTXhJWERtYU4yYkxqRHV5NmFrZTFWVy1JM3cyQXZhY05tMGFIZUlOdWxlN1hYVDNfNjJReXdmYzVuY3N1LU1VREdnZllPM2x4a0hBfn4%3D&chksm=6b618a155c160303543b4d8772bead6569c2bef191f88e5fba66203e30aee8a3e5541404123a&scene=0&xtrack=1&previewkey=vYtIc5o5KtnPpqjmkwTgbcNS9bJajjJKzz%252F0By7ITJA%253D#wechat_redirect");
        subButtonArray3.add(subButtonItem10);*/

        JSONObject subButtonItem11 = new JSONObject();
        subButtonItem11.put("type", "view");
        subButtonItem11.put("name", "智能心电");
        subButtonItem11.put("url", "https://14ldroa8.haoyebao.com/goods.html?goods_id=14leolsu&gcom=1&p_branch_id=14ldtnlu&store_id=14ldroa8&source=g_share&front_id=0&branch_mid=14lgpxf0");
        subButtonArray3.add(subButtonItem11);


        JSONObject subButtonItem12 = new JSONObject();
        subButtonItem12.put("type", "view");
        subButtonItem12.put("name", "可享服务");
        subButtonItem12.put("url", "http://wx.cdmn.com/html/medcare/dest/client/fdSignWX/recordVA.html?hospitalId=8a8383af60747eb8016096fdb3dd0144");
        subButtonArray3.add(subButtonItem12);

        JSONObject subButtonItem13 = new JSONObject();
        subButtonItem13.put("type", "view");
        subButtonItem13.put("name", "下载APP");
        subButtonItem13.put("url", "http://medcare.cardiochina.net/appdownload.html");
        subButtonArray3.add(subButtonItem13);

        /**
         *
         * 如有任何疑问可联系关心堂健康管家！
         * 周一至周五请拨打028-66333314；
         * 周六至周日请拨打13088021189；
         * 或加管家微信：13088075887
         * （服务时间：9：00-18：00）
         */
        JSONObject subButtonItem14 = new JSONObject();
        subButtonItem14.put("type", "click");
        subButtonItem14.put("name", "联系我们");
        subButtonItem14.put("key", "menu_click_call_us");
        subButtonArray3.add(subButtonItem14);

        subButton3.put("name", "会员中心");
        subButton3.put("sub_button", subButtonArray3);

        buttonArray.add(subButton3);

        JSONObject button = new JSONObject();
        button.put("button", buttonArray);

        log.info(button.toJSONString());

        try {
           // WxCode wxCode = WxMenuHandler.createWxMenu(APPID, APPSECRET, button.toJSONString());
            //log.info(wxCode.toJsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
