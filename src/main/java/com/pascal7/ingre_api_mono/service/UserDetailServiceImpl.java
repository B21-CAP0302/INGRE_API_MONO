package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.User;
import com.pascal7.ingre_api_mono.custom.CustomerDetails;
import com.pascal7.ingre_api_mono.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        validateEmailIsExist(username);
        User user = getUserByUsername(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole()));

        return new CustomerDetails(
                user.getEmail(),
                user.getPassword(),
                authorityList
        );
    }

    public User getUserByUsername(String username){
        validateEmailIsExist(username);
        return userDetailRepository.findByEmail(username).get();
    }

    public void validateEmailIsExist(String username) {
        if(!userDetailRepository.findByEmail(username).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email didn't exist");
        }
    }

    public void validateEmailIsNotExist(String username) {
        if(userDetailRepository.findByEmail(username).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is exist");
        }
    }
}
