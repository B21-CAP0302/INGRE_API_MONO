package com.pascal7.ingre_api_mono.utils;

import com.pascal7.ingre_api_mono.service.UserDetailServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Value("ThisIsForEncryptedPasswordTHatHashingSomeFunctionThrougThePAsswordAndThePaswordis23rhob3oirb2i3hrifhuw4hr2u3rhefb2i3hrugbi3rhoq2")
    private String secret;

    public String generateToken(UserDetails userDetails, Integer minute){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Timestamp(System.currentTimeMillis()))
//                .setExpiration(new Timestamp(System.currentTimeMillis() + (1000L*60*minute)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public UserDetails parseToken(String token){
        Jws<Claims> jws;
        try {
            jws = Jwts
                    .parserBuilder()
                    .setSigningKey(secret).build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, BankString.tokenTimeOut);
        }

        String username = jws.getBody().getSubject();
        return userDetailService.loadUserByUsername(username);
    }
}
