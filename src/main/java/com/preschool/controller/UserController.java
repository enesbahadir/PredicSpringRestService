package com.preschool.controller;

import com.preschool.model.LoginForm;
import com.preschool.model.User;
import com.preschool.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginForm user) {
        User findUser = userService.getUserByUserName(user.getUserName());
        return findUser != null && findUser.getPassword().equals(user.getPassword());
    }

    @PostMapping("/register")
    public User createUser (@RequestBody User newUser)
    {
        return userService.createUser(newUser);
    }
}
