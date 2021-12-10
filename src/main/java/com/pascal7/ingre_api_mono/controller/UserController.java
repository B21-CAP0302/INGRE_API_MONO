package com.pascal7.ingre_api_mono.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascal7.ingre_api_mono.custom.ResponseStat;
import com.pascal7.ingre_api_mono.entity.User;
import com.pascal7.ingre_api_mono.custom.CustomerCredentials;
import com.pascal7.ingre_api_mono.custom.TokenResponse;
import com.pascal7.ingre_api_mono.custom.VerificationStat;
import com.pascal7.ingre_api_mono.service.UserService;
import com.pascal7.ingre_api_mono.utils.BankString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/api/auth/login")
    public TokenResponse login(@RequestBody CustomerCredentials customerCredentials){
        customerCredentials.setAuthority("user");
        System.out.println(customerCredentials.getAuthority());
        return userService.userLogIn(customerCredentials);
    }

    @PostMapping("/api/auth/admin")
    public TokenResponse loginAdmin(@RequestBody CustomerCredentials customerCredentials){
        customerCredentials.setAuthority("admin");
        System.out.println(customerCredentials.getAuthority());
        return userService.userLogIn(customerCredentials);
    }

    @PostMapping("/api/auth/register")
    public ResponseStat createUser(@RequestPart User user, @Nullable @RequestPart("upload") MultipartFile multipartFile) throws IOException {
        user.setVerificationStat(VerificationStat.UNVERIFIED.getValue());
        user.setRole("user");
        user.setDateCreated(new Timestamp(System.currentTimeMillis()));
        userService.createWithFile(user, multipartFile);
        return new ResponseStat(user.getId(), BankString.success);
    }

    @PostMapping("/api/auth/register/admin")
    public ResponseStat createAdmin(@RequestPart User user, @Nullable @RequestPart("upload") MultipartFile multipartFile) throws IOException {
        user.setVerificationStat(VerificationStat.UNVERIFIED.getValue());
        user.setRole("admin");
        user.setDateCreated(new Timestamp(System.currentTimeMillis()));
        userService.createWithFile(user, multipartFile);
        return new ResponseStat(user.getId(), BankString.success);
    }

    @GetMapping("/api/user/profile/{id}")
    public User getProfile(@PathVariable String id){
        return userService.getById(id);
    }

    @PutMapping("/api/user/profile/update")
    public User updateProfile(@RequestPart User user, @Nullable @RequestPart("upload") MultipartFile multipartFile) throws IOException {
        return userService.updateWithFile(user, multipartFile);
    }
}
