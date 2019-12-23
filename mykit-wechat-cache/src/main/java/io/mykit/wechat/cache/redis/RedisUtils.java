package io.mykit.wechat.cache.redis;

import io.mykit.wechat.cache.redis.builder.RedisClusterBuilder;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisCluster;

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
     */
    public static void saveValueToRedis(String key, String value){
        JedisCluster jedisCluster = RedisClusterBuilder.getInstance();
        if(jedisCluster != null){
            String ret =  jedisCluster.set(key, value);
            log.debug(ret);
        }
    }

    /**
     * 保存数据到redis
     * @param key 保存的数据key
     * @param value 保存的value
     * @param expireTime 过期时间,单位秒
     */
    public static void saveValueToRedis(String key, String value, int expireTime){
        JedisCluster jedisCluster = RedisClusterBuilder.getInstance();
        if(jedisCluster != null){
            String ret =  jedisCluster.setex(key, expireTime, value);
            log.debug(ret);
        }
    }

    /**
     * 从Redis中获取数据
     * @param key 获取数据的key
     * @return redis中缓存的key
     */
    public static String getValueFromRedis(String key){
        JedisCluster jedisCluster = RedisClusterBuilder.getInstance();
        if (jedisCluster == null || !jedisCluster.exists(key)) return "";
        return jedisCluster.get(key);
    }

}
