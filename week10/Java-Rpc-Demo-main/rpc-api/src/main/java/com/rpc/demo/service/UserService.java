package com.rpc.demo.service;


import com.rpc.demo.model.User;

/**
 * 
 */
public interface UserService {

    /**
     * find by id
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
