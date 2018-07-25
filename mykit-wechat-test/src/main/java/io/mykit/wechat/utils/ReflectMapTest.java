package io.mykit.wechat.utils;

import io.mykit.wechat.utils.map.ReflectMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/25 14:30
 * @Description: 测试Java类与Map互转
 */
@Slf4j
public class ReflectMapTest {
    @Test
    public void testMap() throws Exception{
        io.mykit.wechat.utils.Test test = new io.mykit.wechat.utils.Test("liuyazhuang");
        log.info(test.toJsonString());
        Map<String, Object> map = ReflectMap.beanToMap(test);
        log.info(map.toString());

        io.mykit.wechat.utils.Test test1 = ReflectMap.mapToT(map, io.mykit.wechat.utils.Test.class);
        log.info(test1.toJsonString());
    }
}
