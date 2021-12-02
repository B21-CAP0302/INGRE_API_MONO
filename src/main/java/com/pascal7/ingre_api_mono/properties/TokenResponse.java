package com.pascal7.ingre_api_mono.properties;

public class TokenResponse {
    public String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }
}
