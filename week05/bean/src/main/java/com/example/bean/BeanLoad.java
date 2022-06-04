package com.example.bean;


import com.example.bean.auto.AutoWiringExample;
import org.springframework.beans.factory.annotation.Autowired;



public class BeanLoad {

    @Autowired
    public AutoWiringExample instanceExample;

    public static void main(String[] args) {
        BeanLoad beanLoad = new BeanLoad();
        if(beanLoad.instanceExample==null)
        beanLoad.instanceExample = new AutoWiringExample();
        beanLoad.instanceExample.info();
    }
}
