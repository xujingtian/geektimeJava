package org.split.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.split.entity.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<User> queryUserByUserId(long userId);

    void insert(@Value("user") User user);

    @Delete("delete from t_user where user_id = #{userId}")
    void delete(long userId);
}
