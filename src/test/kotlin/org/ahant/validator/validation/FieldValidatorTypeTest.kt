package org.ahant.validator.validation

import org.testng.annotations.Test

import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotNull
import org.testng.AssertJUnit.assertNull

/**
 * Created by ahant on 8/23/2016.
 */
class FieldValidatorTypeTest {

    @Test
    fun testString() {
        assertNull(FieldValidatorType.STRING.get().validate("some value"))
        assertEquals(FieldValidatorType.STRING.get().validate(null).iterator().next(), "Invalid value: null")
    }

    @Test
    fun testPhone() {
        assertNull(FieldValidatorType.PHONE.get().validate("8978889915"))
        assertNull(FieldValidatorType.PHONE.get().validate("+918978889915"))
        assertNull(FieldValidatorType.PHONE.get().validate("04040028085"))
        assertNull(FieldValidatorType.PHONE.get().validate("068040028085"))
        assertNotNull(FieldValidatorType.PHONE.get().validate("invalid-phone"))
        assertEquals(FieldValidatorType.PHONE.get().validate("884646334").iterator().next(), String.format(INVALID_PHONE, "884646334"))
    }

    @Test
    fun testEmail() {
        assertNull(FieldValidatorType.EMAIL.get().validate("mailtopabi@gmail.com"))
        assertNull(FieldValidatorType.EMAIL.get().validate("mailtopabi@gmail.co.in"))
        assertNull(FieldValidatorType.EMAIL.get().validate("mailtop.abi@gmail.com"))
        assertNull(FieldValidatorType.EMAIL.get().validate("mailt.o.pabi@gmail.co.in"))
        assertNotNull(FieldValidatorType.EMAIL.get().validate("invalid-email@gmail.com"))
        assertEquals(FieldValidatorType.EMAIL.get().validate("invalid-email@gmail.com").iterator().next(), String.format(INVALID_EMAIL, "invalid-email@gmail.com"))
    }

    @Test
    fun testZip() {
        assertNull(FieldValidatorType.ZIP.get().validate("500049"))
        assertNull(FieldValidatorType.ZIP.get().validate("500049"))
        assertNotNull(FieldValidatorType.ZIP.get().validate("5000498"))
        assertEquals(FieldValidatorType.ZIP.get().validate("5000").iterator().next(), String.format(INVALID_ZIP, "5000"))
    }

    @Test
    fun testDate() {
        assertNull(FieldValidatorType.DATE.get().validate("23/08/2016"))
        assertNotNull(FieldValidatorType.DATE.get().validate("23-08-2016"))
        assertNotNull(FieldValidatorType.DATE.get().validate("23.08.2016"))
        assertEquals(FieldValidatorType.DATE.get().validate("23/08/16").iterator().next(), String.format(INVALID_DATE, "23/08/16"))
    }
}
