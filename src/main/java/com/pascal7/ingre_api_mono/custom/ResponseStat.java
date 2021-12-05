package com.pascal7.ingre_api_mono.custom;

public class ResponseStat {
    private String id;
    private String stat;

    public ResponseStat() {
    }

    public ResponseStat(String id, String stat) {
        this.id = id;
        this.stat = stat;
    }

    public String getId() {
        return id;
    }

    public String getStat() {
        return stat;
    }
}
