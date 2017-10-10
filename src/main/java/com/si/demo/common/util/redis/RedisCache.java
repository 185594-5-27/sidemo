package com.si.demo.common.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * 类描述：redis操作工具类
 */
@Repository
public class RedisCache {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    // 将Object对象以key的形式保存到redis中
    public void setObject(String key,Object object){
        redisTemplate.opsForValue().set(key,object);
    }

    // 以key的形式从redis中获取Object数据
    public Object getObject(String key){
        return  Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(null);
    }

    // 将String对象以key的形式保存到redis中
    public void setString(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    // 以key的形式从redis中获取String数据
    public String getString(String key){
        return Optional.ofNullable(stringRedisTemplate.opsForValue().get(key)).orElse("");
    }



}
