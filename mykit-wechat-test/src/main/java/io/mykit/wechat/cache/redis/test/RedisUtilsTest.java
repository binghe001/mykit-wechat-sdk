package io.mykit.wechat.cache.redis.test;

import io.mykit.wechat.cache.redis.RedisUtils;
import io.mykit.wechat.cache.redis.config.LoadRedisProp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 15:39
 * @Description: 测试Redis缓存
 */

@Slf4j
public class RedisUtilsTest {

    @Test
    public void testLoadRedisProp(){
        log.info(LoadRedisProp.getStringValue(LoadRedisProp.HOST));
    }

    @Test
    public void testSaveAndGetRedisData() throws Exception{
        RedisUtils.saveValueToRedis("test", "test", 5);
        while(true){
            Thread.sleep(1000);
            log.info(RedisUtils.getValueFromRedis("test"));
        }
    }
}
