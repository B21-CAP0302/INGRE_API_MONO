package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.User;
import com.pascal7.ingre_api_mono.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        if(user.getId() != null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "you have been registered before"
            );
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(String id) {
        if(!userRepository.findById(id).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "your id didn't exist"
            );
        }
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        getById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        getById(id);
        userRepository.delete(userRepository.getById(id));
        throw new ResponseStatusException(
                HttpStatus.ACCEPTED,
                String.format("the id %s has been deleted", id)
        );
    }
}
