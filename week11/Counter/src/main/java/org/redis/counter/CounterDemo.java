package org.redis.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class CounterDemo {
    public static void main(String[] args) {
        SpringApplication.run(CounterDemo.class, args);
    }

    @Bean
    public void test(RedisTemplate<String, Long> redisTemplate, Counter counter) {
        // 初始化订单数量
        redisTemplate.opsForValue().set("orderCount", 1000L);

        for(int i = 0; i < 10; i++) {
            System.out.println(counter.minusOne("orderCount"));
        }
    }
}
