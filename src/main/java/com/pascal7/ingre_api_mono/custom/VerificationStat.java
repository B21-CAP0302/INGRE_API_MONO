package com.pascal7.ingre_api_mono.custom;

public enum VerificationStat {
    VERIFIED("verified"),
    UNVERIFIED("unverified");

    private final String value;

    public String getValue(){
        return this.value;
    }
    VerificationStat(String value){
        this.value = value;
    }
}
