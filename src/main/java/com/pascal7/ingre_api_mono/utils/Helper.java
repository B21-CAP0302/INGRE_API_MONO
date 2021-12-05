package com.pascal7.ingre_api_mono.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Helper {

    public void validateIdIsNull(String id) {
        if(id != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idMustNull);
        }
    }

    public void validateIdIsNotNull(String id) {
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idMustNotNull);
        }
    }
}
