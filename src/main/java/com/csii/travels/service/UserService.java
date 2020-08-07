package com.csii.travels.service;


import com.csii.travels.entity.User;

public interface UserService {

    User login(User user);

    void register(User user);
}
