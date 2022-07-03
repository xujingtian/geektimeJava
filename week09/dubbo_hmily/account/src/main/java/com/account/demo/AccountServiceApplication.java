package com.account.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource({"classpath:spring-dubbo.xml"})
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AccountServiceApplication.class);
        // 关闭tomat，使用dubbo内置通信
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
