package com.csii.travels.dao;

import com.csii.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    //注册用户
    void save(User user);

    //根据用户名查询用户
    User findByUsername(String username);

    //根据用户名和密码查询用户
    User findUserByNameAndPwd(User user);
}
