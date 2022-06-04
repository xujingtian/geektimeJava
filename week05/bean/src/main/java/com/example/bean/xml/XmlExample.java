package com.example.bean.xml;

import org.springframework.stereotype.Component;

/**
 * xml方式，Bean装配
 */
@Component
public class XmlExample {
    public XmlExample() {
        System.out.println("XML Construct Example");
    }

    public void info() {
        System.out.println("XML Auto wiring example");
    }
}
