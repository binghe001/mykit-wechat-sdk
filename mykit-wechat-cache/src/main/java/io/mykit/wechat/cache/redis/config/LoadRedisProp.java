package io.mykit.wechat.cache.redis.config;

import io.mykit.wechat.utils.common.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 15:14
 * @Description: 加载指定的redis.properties
 */

public class LoadRedisProp extends BaseRedisProp {

    private volatile static Properties instance;

    static {
        InputStream in = LoadRedisProp.class.getClassLoader().getResourceAsStream(FILE_NAME);
        instance = new Properties();
        try {
            instance.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(String key){
        if(instance == null) return "";
        return instance.getProperty(key, "");
    }

    public static Integer getIntegerValue(String key){
       String v = getStringValue(key);
       return StringUtils.isEmpty(v) ? 0 : Integer.parseInt(v);
    }

    public static Boolean getBooleanValue(String key){
       String v = getStringValue(key);
       return StringUtils.isEmpty(v) ? false : Boolean.parseBoolean(key);
    }
}
