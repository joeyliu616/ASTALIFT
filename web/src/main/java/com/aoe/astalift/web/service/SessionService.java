package com.aoe.astalift.web.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by joey on 16-3-21.
 */
@Service
public class SessionService {

    @Resource
    RedisTemplate<String,Integer> sessionCache;


    public boolean isSessionValid(String sessionKey){
        if(null != sessionKey){
            return null != sessionCache.opsForValue().get(sessionKey);
        }
        return false;
    }

    public Integer getUserId(String sessionKey){
        return sessionCache.opsForValue().get(sessionKey);
    }

    public String saveUserSessionKey(Integer userId){
        String sessionKey = UUID.randomUUID().toString().replaceAll("-","");
        sessionCache.opsForValue().set(sessionKey,userId);
        return sessionKey;
    }

    public boolean deleteSessionKey(String sessionKey){
        if(null == sessionKey){
            return false;
        }
        if(null != this.getUserId(sessionKey)){
            sessionCache.delete(sessionKey);
            return true;
        }
        return false;
    }

}
