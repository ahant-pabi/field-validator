package org.ahant.inputvalidator.validation;

import com.google.common.collect.ImmutableList;
import org.ahant.inputvalidator.validation.util.RequiredFieldValidator;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Set;

import static org.ahant.inputvalidator.constants.ApplicationConstants.COLLECTION_MIN_SIZE_ERROR;
import static org.ahant.inputvalidator.constants.ApplicationConstants.REQUIRED_FIELD_MISSING;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by ahant on 8/22/2016.
 */
public class RequiredFieldValidatorTest {

    @Test
    public void test_allGood(){
        Person clazz = new Person();
        clazz.setBirthDate(new Date());
        clazz.setFullName("Pabitra Muni");
        clazz.setGender(Gender.MALE);
        clazz.setContactNumberList(ImmutableList.of("8978889915"));
        clazz.setAddress(getAddress());
        clazz.setEmailAddress("mailtopabi@gmail.com");
        assertTrue(RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST).isEmpty());
    }

    @Test
    public void test_ReqdFieldMissing(){
        Person clazz = new Person();
        clazz.setBirthDate(new Date());
        clazz.setFullName("Pabitra Muni");
        clazz.setGender(Gender.MALE);
        clazz.setAddress(new Address());
        Set<String> result = RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 1);
        assertEquals(result.iterator().next(), String.format(REQUIRED_FIELD_MISSING, "contactNumber"));
    }

    @Test
    public void test_CollectionLessThanMinSize(){
        Person clazz = new Person();
        clazz.setBirthDate(new Date());
        clazz.setFullName("Pabitra Muni");
        clazz.setGender(Gender.MALE);
        clazz.setContactNumberList(ImmutableList.of());
        clazz.setAddress(new Address());
        Set<String> result = RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 1);
        assertEquals(result.iterator().next(), String.format(COLLECTION_MIN_SIZE_ERROR, 1, "contactNumber"));
    }

    @Test
    public void test_ValidationTypeContinue(){
        Person clazz = new Person();
        clazz.setBirthDate(new Date());
        clazz.setGender(Gender.MALE);
        clazz.setAddress(getAddress());
        Set<String> result = RequiredFieldValidator.validate(clazz, FieldValidationType.CONTINUE);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 2);
    }

    @Test
    public void test_ValidationTypeFailFastShouldNotContinue(){
        Person clazz = new Person();
        clazz.setBirthDate(new Date());
        clazz.setGender(Gender.MALE);
        clazz.setAddress(getAddress());
        Set<String> result = RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 1);
        assertEquals(result.iterator().next(), String.format(REQUIRED_FIELD_MISSING, "fullName"));
    }

    private Address getAddress() {
        Address address = new Address();
        address.setAddressLine1("India");
        address.setCity("Hyderabad");
        address.setState("Telangana");
        address.setZip("500049");
        return address;
    }
}
