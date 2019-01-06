package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration

public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    String home() {
        return "Demo Application";
    }

    @GetMapping("/user/{userId}")
    public User getMessage(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/user")
    public User postMessage(@RequestBody User userEntity) {
        User user = userService.createUser(userEntity);
        return user;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserController.class, args);
    }

}