package io.mykit.wechat.cache.redis;

import io.mykit.wechat.cache.redis.builder.RedisBuilder;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Author: liuyazhuang
 * @Date: 2018/7/18 15:38
 * @Description: Redis工具类
 */
@Slf4j
public class RedisUtils {

    /**
     * 保存数据到redis
     * @param key 保存的数据key
     * @param value 保存的value
     * @param expireTime 过期时间,单位秒
     */
    public static void saveValueToRedis(String key, String value, int expireTime){
        Jedis jedis = RedisBuilder.getJedis();
        String ret = jedis.set(key, value);
        log.debug(ret);

        long time = jedis.expire(key, expireTime);
        log.debug(String.valueOf(time));

    }

    /**
     * 从Redis中获取数据
     * @param key 获取数据的key
     * @return redis中缓存的key
     */
    public static String getValueFromRedis(String key){
        Jedis jedis = RedisBuilder.getJedis();
        if (!jedis.exists(key)) return "";
        return jedis.get(key);
    }

}
