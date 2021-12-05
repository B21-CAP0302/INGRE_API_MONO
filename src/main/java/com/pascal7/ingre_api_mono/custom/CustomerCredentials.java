package com.pascal7.ingre_api_mono.custom;

public class CustomerCredentials {

    private String username;
    private String password;
    private String authority;

    public CustomerCredentials() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
