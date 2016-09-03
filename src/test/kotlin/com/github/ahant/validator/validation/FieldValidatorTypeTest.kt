package com.github.ahant.validator.validation

import com.github.ahant.validator.constants.ApplicationConstants.INVALID_DATE
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_EMAIL
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_PHONE
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_ZIP
import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotNull
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

/**
 * Created by ahant on 8/23/2016.
 */

class FieldValidatorTypeTest {

    @Test
    fun testPhone() {
        assertTrue(FieldValidatorType.PHONE.get().validate("8978889915").isEmpty())
        assertTrue(FieldValidatorType.PHONE.get().validate("+918978889915").isEmpty())
        assertTrue(FieldValidatorType.PHONE.get().validate("04040028085").isEmpty())
        assertTrue(FieldValidatorType.PHONE.get().validate("068040028085").isEmpty())
        assertNotNull(FieldValidatorType.PHONE.get().validate("invalid-phone"))
        assertEquals(FieldValidatorType.PHONE.get().validate("884646334").iterator().next(), String.format(INVALID_PHONE, "884646334"))
    }

    @Test
    fun testEmail() {
        assertTrue(FieldValidatorType.EMAIL.get().validate("mailtopabi@gmail.com").isEmpty())
        assertTrue(FieldValidatorType.EMAIL.get().validate("mailtopabi@gmail.co.in").isEmpty())
        assertTrue(FieldValidatorType.EMAIL.get().validate("mailtop.abi@gmail.com").isEmpty())
        assertTrue(FieldValidatorType.EMAIL.get().validate("mailt.o.pabi@gmail.co.in").isEmpty())
        assertNotNull(FieldValidatorType.EMAIL.get().validate("invalid-email@gmail.com"))
        assertEquals(FieldValidatorType.EMAIL.get().validate("invalid-email@gmail.com").iterator().next(), String.format(INVALID_EMAIL, "invalid-email@gmail.com"))
    }

    @Test
    fun testZip() {
        assertTrue(FieldValidatorType.ZIP.get().validate("500049").isEmpty())
        assertNotNull(FieldValidatorType.ZIP.get().validate("5000498"))
        assertEquals(FieldValidatorType.ZIP.get().validate("5000").iterator().next(), String.format(INVALID_ZIP, "5000"))
    }

    @Test
    fun testDate() {
        assertTrue(FieldValidatorType.DATE.get().validate("23/08/2016").isEmpty())
        assertNotNull(FieldValidatorType.DATE.get().validate("23-08-2016"))
        assertNotNull(FieldValidatorType.DATE.get().validate("23.08.2016"))
        assertEquals(FieldValidatorType.DATE.get().validate("23/08/16").iterator().next(), String.format(INVALID_DATE, "23/08/16"))
    }
}
