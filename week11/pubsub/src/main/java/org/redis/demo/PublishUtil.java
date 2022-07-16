package org.redis.demo;

import org.springframework.data.redis.core.RedisTemplate;

public class PublishUtil {
    private RedisTemplate<String, String> redisTemplate;

    public PublishUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void publish(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
