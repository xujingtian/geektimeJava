package org.redis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }

    private PublishUtil publishUtil;

    @Bean
    public void test(PublishUtil publishUtil) {
        publishUtil.publish("subscribe", "hello");
    }
}
