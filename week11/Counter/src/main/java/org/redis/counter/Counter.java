package org.redis.counter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Counter {
    private RedisTemplate<String, Long> redisTemplate;
    public Counter(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long minusOne(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }
}
