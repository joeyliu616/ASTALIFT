package com.aoe.astalift.cart.config;


import com.aoe.astalift.cart.dto.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.util.Assert;

/**
 * Created by joey on 16-3-17.
 */
@Configuration
public class RedisConfig  {

    @Bean
    public RedisTemplate<Integer, Cart> cartCache(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Integer, Cart> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Assert.isTrue(redisConnectionFactory.getConnection().isClosed() == false);
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<Cart>(Cart.class));
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<Integer>(Integer.TYPE));
        return redisTemplate;
    }


}
