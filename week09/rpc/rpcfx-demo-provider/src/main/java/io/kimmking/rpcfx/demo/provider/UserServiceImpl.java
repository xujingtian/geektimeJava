package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        log.info("11111111111111111111111111111");
        return new User(id, "dean" + System.currentTimeMillis());
    }
}
