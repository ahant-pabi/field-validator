package org.ahant.inputvalidator.validation;

import org.ahant.inputvalidator.annotation.FieldInfo;
import org.ahant.inputvalidator.annotation.Required;

import static org.ahant.inputvalidator.validation.FieldValidatorType.STRING;
import static org.ahant.inputvalidator.validation.FieldValidatorType.ZIP;

/**
 * Created by ahant on 7/16/2016.
 */
@Required
public class Address {
    @FieldInfo(validatorType = STRING, optional = false)
    private String addressLine1;
    @FieldInfo(optional = true, validatorType = STRING)
    private String addressLine2;
    @FieldInfo(validatorType = STRING, optional = false)
    private String city;
    @FieldInfo(validatorType = STRING, optional = false)
    private String state;
    @FieldInfo(validatorType = STRING, optional = false)
    private String country = "India";
    @FieldInfo(validatorType = ZIP, optional = false)
    private String zip;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
