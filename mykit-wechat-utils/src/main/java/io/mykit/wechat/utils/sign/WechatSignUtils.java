/**
 * Copyright 2018-2118 the original author or authors.
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
package io.mykit.wechat.utils.sign;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author liuyazhuang
 * @date 2018/10/29 17:27
 * @description 微信签名工具类
 * @version 1.0.0
 */
public class WechatSignUtils {

    /**
     * 检测微信签名是否正确
     * @param token 配置的微信token
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param signature 签名
     * @return 是否正确：true:正确； false:错误
     */
    public static boolean checkSignature (String token, String timestamp, String nonce, String signature){
        return gen(new String[]{token, timestamp, nonce}).equals(signature);
    }

    public static String gen(String... arr) {
        if (StringUtils.isAnyEmpty(arr)) {
            throw new IllegalArgumentException("非法请求参数，有部分参数为空 : " + Arrays.toString(arr));
        } else {
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            String[] var2 = arr;
            int var3 = arr.length;
            for(int var4 = 0; var4 < var3; ++var4) {
                String a = var2[var4];
                sb.append(a);
            }
            return org.apache.commons.codec.digest.DigestUtils.sha1Hex(sb.toString());
        }
    }

    public static String genWithAmple(String... arr) {
        if (StringUtils.isAnyEmpty(arr)) {
            throw new IllegalArgumentException("非法请求参数，有部分参数为空 : " + Arrays.toString(arr));
        } else {
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < arr.length; ++i) {
                String a = arr[i];
                sb.append(a);
                if (i != arr.length - 1) {
                    sb.append('&');
                }
            }
            return DigestUtils.sha1Hex(sb.toString());
        }
    }
}
