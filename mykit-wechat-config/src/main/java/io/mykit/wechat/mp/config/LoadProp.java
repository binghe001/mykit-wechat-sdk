package io.mykit.wechat.mp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/11 16:50
 * @Description: 读取配置文件获取相关的配置信息
 */
public class LoadProp extends BaseProp{

    private volatile static Properties instance;

    static {
        InputStream in = LoadProp.class.getClassLoader().getResourceAsStream(PROP_FILE_PARAMS);
        instance = new Properties();
        try {
            instance.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        if (instance == null) return "";
        return instance.getProperty(key, "");
    }

}
