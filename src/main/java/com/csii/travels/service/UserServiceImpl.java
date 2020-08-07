package com.csii.travels.service;

import com.csii.travels.dao.UserDao;
import com.csii.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //注入userDao
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {

        User resUser = userDao.findUserByNameAndPwd(user);
        if (resUser == null){
            throw new RuntimeException("用户名或密码错误");
        }
        return resUser;
    }

    @Override
    public void register(User user) {

        if (userDao.findByUsername(user.getUsername()) == null){
            userDao.save(user);
        }else {
            throw new RuntimeException("用户名已存在--------");
        }

    }
}
