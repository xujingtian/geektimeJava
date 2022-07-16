package org.redis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LockDemo {
    public static void main(String[] args) {
        SpringApplication.run(LockDemo.class, args);
    }

    @Bean
    public void Test(Lock lock) {
        try {
            while (!lock.tryLock("test", 10L, TimeUnit.SECONDS)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {

                }
            }
            System.out.println("拿到锁了.");
        } finally {
            lock.unLock("test");
            System.out.println("释放锁.");
        }
    }
}
