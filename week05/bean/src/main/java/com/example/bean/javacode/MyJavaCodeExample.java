package com.example.bean.javacode;

import org.springframework.stereotype.Component;

/**
 * Java代码方式，Bean装配
 */
@Component
public class MyJavaCodeExample {

    public MyJavaCodeExample() {
        System.out.println("Java Construct Example");
    }

    public void info() {
        System.out.println("Java Auto wiring example");
    }
}
