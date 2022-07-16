package org.redis.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Lock {
    private RedisTemplate<String, String> redisTemplate;

    public Lock(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 请求锁
     * @param key 锁的名称
     * @param timout 超时时间
     * @param timeUnit 时间单位
     * @return
     */
    public boolean tryLock(String key, Long timout, TimeUnit timeUnit) {
        boolean success = redisTemplate.opsForValue().setIfAbsent(key, "", timout, timeUnit);
        // 记录一个线程请求了几次锁
        LockCounter.plusOne(key);
        return success;
    }

    /**
     * 释放锁
     * @param key 锁的名称
     */
    public void unLock(String key) {
        // 请求锁的次数大于1，则只需要将次数减1，只有次数为1时才会真正释放锁
        if(LockCounter.getLockCount(key) == 1) {
            redisTemplate.delete(key);
        }
        LockCounter.minusOne(key);
    }
}
