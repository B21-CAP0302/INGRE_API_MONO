package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends UserRepository{
    Optional<User> findByEmail(String email);
}
