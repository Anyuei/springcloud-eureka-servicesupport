package com.yun.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version : V1.0
 * @ClassName: RedisUtils
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/5/12 1:31
 */

@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public String getValueByKey(String key){
        Jedis jedis = null;
        String value =null;
        try {
            jedis = jedisPool.getResource();
            value= jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return value;
    }
    public String setKV(String key,String value){
        Jedis jedis = null;
        String state = "";
        try {
            jedis = jedisPool.getResource();
            state= jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return state;
    }
}
