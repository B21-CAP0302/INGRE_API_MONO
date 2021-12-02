package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User getById(String id);
    List<User> getAll();
    User update(User user);
    void delete(String id);
}
