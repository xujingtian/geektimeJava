package org.split.service;


import org.split.dao.CommonMapper;
import org.split.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    private CommonMapper commonMapper;
    public CommonService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    public List queryUserByUserId(long userId) {
        return commonMapper.queryUserByUserId(userId);
    }

    public void insert(User user) {
        commonMapper.insert(user);
    }
}
