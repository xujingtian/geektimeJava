package com.example.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.prac.annotation.DataSource;
import org.prac.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonMapper {
    @DataSource("slave")
    @Select("select user_id as userId, user_name as userName, phone_number as phoneNumber from t_user where user_id = #{userId}")
    List<User> queryUserByUserId(long userId);
    @Insert("insert into t_user values (#{userId}, #{userName}, #{phoneNumber})")
    void insert(User user);

    @Delete("delete from t_user where user_id = #{userId}")
    void delete(long userId);
}
