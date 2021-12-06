package com.pascal7.ingre_api_mono.custom;

public enum TransactionStat {
    WAITING("Waiting for Payment"),
    ON_DELIVERY("On Delivery"),
    DONE("Done");

    private final String value;

    TransactionStat(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
