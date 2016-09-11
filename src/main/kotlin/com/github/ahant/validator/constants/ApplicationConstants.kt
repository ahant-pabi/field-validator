package com.github.ahant.validator.constants

/**
 * Created by ahant on 8/14/2016.
 */
internal object ApplicationConstants {
    val REQUIRED_FIELD_MISSING = "Required field missing: \'%s\'"
    val COLLECTION_MIN_SIZE_ERROR = "A minimum of %d values are required for field \'%s\'"
    val PHONE_NUMBER_REGEX = "\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*"
    val INDIA_ZIP_CODE_REGEX = "[0-9]{6}"
    val US_ZIP_CODE_REGEX = "[0-9]{5}(?:-[0-9]{4})?"
    val EMAIL_REGEX = "[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})"
    val DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}" //dd/MM/yyyy

    val INVALID_EMAIL = "Invalid email address: %s"
    val INVALID_ZIP = "Invalid zip code: %s"
    val INVALID_PHONE = "Invalid phone number: %s"
    val INVALID_DATE = "Invalid date value: %s"
}
