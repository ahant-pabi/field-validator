package com.github.ahant.validator.validation

import com.github.ahant.validator.constants.ApplicationConstants.COLLECTION_MIN_SIZE_ERROR
import com.github.ahant.validator.constants.ApplicationConstants.REQUIRED_FIELD_MISSING
import com.github.ahant.validator.validation.util.RequiredFieldValidator
import com.google.common.collect.ImmutableList
import org.testng.Assert.assertEquals
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import java.util.*

/**
 * Created by ahant on 8/22/2016.
 */
class RequiredFieldValidatorTest {

    @Test
    fun test_allGood() {
        val clazz = Person()
        clazz.birthDate = Date()
        clazz.fullName = "Pabitra Muni"
        clazz.gender = Gender.MALE
        clazz.contactNumberList = ImmutableList.of("8978889915")
        clazz.address = address
        clazz.emailAddress = "mailtopabi@gmail.com"
        assertTrue(RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST).isEmpty())
    }

    @Test
    fun test_ReqdFieldMissing() {
        val clazz = Person()
        clazz.birthDate = Date()
        clazz.fullName = "Pabitra Muni"
        clazz.gender = Gender.MALE
        clazz.address = Address()
        val result = RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST)
        assertFalse(result.isEmpty())
        assertEquals(result.size, 1)
        assertEquals(result.iterator().next(), String.format(REQUIRED_FIELD_MISSING, "contactNumber"))
    }

    @Test
    fun test_CollectionLessThanMinSize() {
        val clazz = Person()
        clazz.birthDate = Date()
        clazz.fullName = "Pabitra Muni"
        clazz.gender = Gender.MALE
        clazz.contactNumberList = ImmutableList.of()
        clazz.address = Address()
        val result = RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST)
        assertFalse(result.isEmpty())
        assertEquals(result.size, 1)
        assertEquals(result.iterator().next(), String.format(COLLECTION_MIN_SIZE_ERROR, 1, "contactNumber"))
    }

    @Test
    fun test_ValidationTypeContinue() {
        val clazz = Person()
        clazz.birthDate = Date()
        clazz.gender = Gender.MALE
        clazz.address = address
        val result = RequiredFieldValidator.validate(clazz, FieldValidationType.CONTINUE)
        assertFalse(result.isEmpty())
        assertEquals(result.size, 2)
    }

    @Test
    fun test_ValidationTypeFailFastShouldNotContinue() {
        val clazz = Person()
        clazz.birthDate = Date()
        clazz.gender = Gender.MALE
        clazz.address = address
        val result = RequiredFieldValidator.validate(clazz, FieldValidationType.FAIL_FAST)
        assertFalse(result.isEmpty())
        assertEquals(result.size, 1)
        assertEquals(result.iterator().next(), String.format(REQUIRED_FIELD_MISSING, "fullName"))
    }

    private val address: Address
        get() {
            val address = Address()
            address.addressLine1 = "India"
            address.city = "Hyderabad"
            address.state = "Telangana"
            address.zip = "500049"
            return address
        }
}
