package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.User;
import com.pascal7.ingre_api_mono.custom.CustomerCredentials;
import com.pascal7.ingre_api_mono.custom.TokenResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User create(User user);
    User getById(String id);
    List<User> getAll();
    User update(User user);
    void delete(String id);
    TokenResponse userLogIn(CustomerCredentials customerCredentials);
    User createWithFile(User user, MultipartFile multipartFile) throws IOException;
    User updateWithFile(User user, MultipartFile multipartFile) throws IOException;
}
