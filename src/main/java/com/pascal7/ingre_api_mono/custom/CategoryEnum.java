package com.pascal7.ingre_api_mono.custom;

public enum CategoryEnum {
    BREAKFAST("Breakfast"),
    LAUNCH("Launch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    FAST_FOOD("Fast Food"),
    ONE_MINuTE("One Minute Craft");

    private final String value;

    CategoryEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
