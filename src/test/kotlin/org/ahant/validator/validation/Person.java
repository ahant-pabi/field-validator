package org.ahant.validator.validation;

import org.ahant.validator.annotation.CollectionType;
import org.ahant.validator.annotation.FieldInfo;

import java.util.Date;
import java.util.List;

import static org.ahant.validator.validation.FieldValidatorType.*;

/**
 * Created by ahant on 8/22/2016.
 */
public class Person {
    @FieldInfo(validatorType = STRING, optional = false)
    private String fullName;
    private Date birthDate;
    private Gender gender;
    @FieldInfo(name = "contactNumber", validatorType = PHONE, optional = false)
    @CollectionType
    private List<String> contactNumberList;
    @FieldInfo(validatorType = CUSTOM)
    private Address address;
    @FieldInfo(name = "email", validatorType = EMAIL)
    private String emailAddress;
    @FieldInfo(optional = false)
    private int score;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = new Date(birthDate.getTime());
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public List<String> getContactNumberList() {
        return contactNumberList;
    }

    public void setContactNumberList(List<String> contactNumberList) {
        this.contactNumberList = contactNumberList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
