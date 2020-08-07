package com.csii.travels;

import com.csii.travels.entity.User;
import com.csii.travels.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    public void testSave(){

        User user = new User();

        user.setUsername("王杨");
        user.setEmail("6532454");
        user.setPassword("123456");

        userService.register(user);

    }
}
