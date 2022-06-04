package com.example.bean.auto;

import com.example.bean.javacode.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyJavaCodeExampleTest {

    @Test
    public void test() {
        try (AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MyJavaCodeConfig.class)) {
            MyJavaCodeExample example = (MyJavaCodeExample) configApplicationContext.getBean("javaCodeExample");
            example.info();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
