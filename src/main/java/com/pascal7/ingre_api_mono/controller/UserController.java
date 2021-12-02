package com.pascal7.ingre_api_mono.controller;

import com.pascal7.ingre_api_mono.entity.User;
import com.pascal7.ingre_api_mono.properties.VerificationStat;
import com.pascal7.ingre_api_mono.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/register")
    public User createUser(@RequestBody User user){
        user.setVerificationStat(VerificationStat.UNVERIFIED.getValue());
        user.setRole("USER");
        user.setDateCreated(new Timestamp(System.currentTimeMillis()));
        return userService.create(user);
    }

    @GetMapping("/api/user/profile/{id}")
    public User getProfile(@PathVariable String id){
        return userService.getById(id);
    }

    @PutMapping("/api/user/profile/update")
    public User updateProfile(@RequestBody User user){
        return userService.update(user);
    }
}
