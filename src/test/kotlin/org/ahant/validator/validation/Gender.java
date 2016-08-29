package org.ahant.validator.validation;

/**
 * Created by ahant on 7/16/2016.
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
