package io.mykit.wechat.mp.config;

import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 14:59
 * @Description: 测试读取配置文件
 */

public class LoadPropTest {
    public static void main(String[] args){
        System.out.println(LoadProp.getValue(LoadProp.WEIXIN_TOKEN_GET));
    }
}
