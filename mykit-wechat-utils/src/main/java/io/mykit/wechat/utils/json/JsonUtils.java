package io.mykit.wechat.utils.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 17:19
 * @Description: json字符串和JavaBean相互转化
 */

public class JsonUtils {

    public static <T> T json2Bean(String json, Class<T> clazz){
        JSONObject jsonObject = JSONObject.parseObject(json);
        T obj = jsonObject.toJavaObject(clazz);
        return obj;
    }

    public static String bean2Json(Object obj){
        return JSONObject.toJSONString(obj);
    }
}
