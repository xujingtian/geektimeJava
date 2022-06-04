package com.example.bean.auto;

import org.springframework.stereotype.Component;

/**
 * 自动注解方式，Bean装配
 */
@Component
public class AutoWiringExample {
    public AutoWiringExample() {
        System.out.println("AutoWiring Construct Example");
    }

    public void info() {
        System.out.println("AutoWiring Auto wiring example");
    }
}
