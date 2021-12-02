package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.User;
import com.pascal7.ingre_api_mono.properties.CustomerCredentials;
import com.pascal7.ingre_api_mono.properties.TokenResponse;
import com.pascal7.ingre_api_mono.repository.UserRepository;
import com.pascal7.ingre_api_mono.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        validateIdIsNotExist(user.getId());
        userDetailService.validateEmailIsNotExist(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void validateIdIsNotExist(String id) {
        if(id != null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "you have been registered before"
            );
        }
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

    @Override
    public TokenResponse userLogIn(CustomerCredentials customerCredentials) {
        userDetailService.validateEmailIsExist(customerCredentials.getUsername());
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(
                customerCredentials.getUsername(),
                customerCredentials.getPassword()
        );
        authenticationManager.authenticate(userPass);
        UserDetails userDetails = userDetailService.loadUserByUsername(customerCredentials.getUsername());
        return new TokenResponse(jwtUtils.generateToken(userDetails, 60));
    }
}
