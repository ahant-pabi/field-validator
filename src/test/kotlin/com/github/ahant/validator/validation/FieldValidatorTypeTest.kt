package com.github.ahant.validator.validation

import com.github.ahant.validator.constants.ApplicationConstants.INVALID_DATE
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_EMAIL
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_PHONE
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_ZIP
import com.github.ahant.validator.validation.util.CountryCode
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
        val phoneValidator = FieldValidatorType.PHONE.get()
        assertTrue(phoneValidator.validate("8978889915").isEmpty())
        assertTrue(phoneValidator.validate("+918978889915").isEmpty())
        assertTrue(phoneValidator.validate("04040028085").isEmpty())
        assertTrue(phoneValidator.validate("068040028085").isEmpty())
        assertNotNull(phoneValidator.validate("invalid-phone"))
        assertEquals(phoneValidator.validate("884646334").iterator().next(), String.format(INVALID_PHONE, "884646334"))
    }

    @Test
    fun testEmail() {
        val emailValidator = FieldValidatorType.EMAIL.get()
        assertTrue(emailValidator.validate("mailtopabi@gmail.com").isEmpty())
        assertTrue(emailValidator.validate("mailtopabi@gmail.co.in").isEmpty())
        assertTrue(emailValidator.validate("mailtop.abi@gmail.com").isEmpty())
        assertTrue(emailValidator.validate("mailt.o.pabi@gmail.co.in").isEmpty())
        assertNotNull(emailValidator.validate("invalid-email@gmail.com"))
        assertEquals(emailValidator.validate("invalid-email@gmail.com").iterator().next(), String.format(INVALID_EMAIL, "invalid-email@gmail.com"))
    }

    @Test
    fun testIndiaZip() {
        val zipValidator = FieldValidatorType.ZIP.get()
        zipValidator.setCountry(CountryCode.INDIA)
        assertTrue(zipValidator.validate("500049").isEmpty())
        assertNotNull(zipValidator.validate("5000498"))
        assertEquals(zipValidator.validate("5000").iterator().next(), String.format(INVALID_ZIP, "5000"))
    }

    @Test
    fun testUSZip() {
        val zipValidator = FieldValidatorType.ZIP.get()
        zipValidator.setCountry(CountryCode.US)
        assertTrue(zipValidator.validate("50049").isEmpty())
        assertTrue(zipValidator.validate("50049-1234").isEmpty())
        assertNotNull(zipValidator.validate("5000491234"))
        assertNotNull(zipValidator.validate("500049"))
        assertEquals(zipValidator.validate("500022").iterator().next(), String.format(INVALID_ZIP, "500022"))
    }

    @Test
    fun testDate() {
        val dateValidator = FieldValidatorType.DATE.get()
        assertTrue(dateValidator.validate("23/08/2016").isEmpty())
        assertNotNull(dateValidator.validate("23-08-2016"))
        assertNotNull(dateValidator.validate("23.08.2016"))
        assertEquals(dateValidator.validate("23/08/16").iterator().next(), String.format(INVALID_DATE, "23/08/16"))
    }
}
