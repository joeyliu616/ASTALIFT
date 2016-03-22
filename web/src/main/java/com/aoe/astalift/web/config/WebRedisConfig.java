package com.aoe.astalift.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * Created by joey on 16-3-18.
 */
@Configuration
public class WebRedisConfig {

    @Bean
    public RedisTemplate<String,Integer> sessionCache(RedisConnectionFactory connectionFactory){
        Assert.isTrue(connectionFactory.getConnection().isClosed() == false);
        RedisTemplate<String,Integer> sessionCache = new RedisTemplate<>();
        sessionCache.setConnectionFactory(connectionFactory);
        return sessionCache;
    }

}
