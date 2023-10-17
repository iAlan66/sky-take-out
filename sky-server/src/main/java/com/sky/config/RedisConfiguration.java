package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/10/17 8:25
 */
@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        // 设置 redis 的连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置 redis 的 key 的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
