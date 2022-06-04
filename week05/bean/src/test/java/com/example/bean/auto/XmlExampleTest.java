package com.example.bean.auto;

import com.example.bean.xml.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlExampleTest {

    @Test
    public void test() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml")) {
            XmlExample example = (XmlExample) context.getBean("XmlExample");
            example.info();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
