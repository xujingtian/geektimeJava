package com.example.mysql3.controller;

import com.example.mysql3.entity.User;
import com.example.mysql3.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonController {
    private CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("query")
    public String query() {
        List map = commonService.queryUserByUserId(1);
        return "ddd";
    }

    @GetMapping("insert")
    public String insert() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("df");
        user.setPhoneNumber("322222222");
        commonService.insert(user);
        return "ddd";
    }
}
