package com.example.mysql3;

import org.mybatis.spring.annotation.MapperScan;
import org.split.dao.CommonMapper;
import org.split.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@MapperScan("org.split.dao")
public class Mysql3Application {
    public static void main(String[] args) {
        SpringApplication.run(Mysql3Application.class, args);
    }

    @Autowired
    CommonMapper commonMapper;
    @Bean
    public void test() {
        User user = new User();
        user.setUserId(10);
        user.setUserName("lily");
        user.setPhoneNumber("13511111111");
        // 在主表更新
        commonMapper.delete(user.getUserId());
        commonMapper.insert(user);
        // 在从表查询
        List<User> list = commonMapper.queryUserByUserId(2);
    }
}
