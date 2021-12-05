package com.pascal7.ingre_api_mono.custom;

public class TokenResponse {
    public String id;
    public String token;

    public TokenResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public TokenResponse() {
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
