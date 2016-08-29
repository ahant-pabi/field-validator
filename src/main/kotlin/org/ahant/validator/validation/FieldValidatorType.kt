package org.ahant.validator.validation

import org.ahant.validator.constants.ApplicationConstants.DATE_REGEX
import org.ahant.validator.constants.ApplicationConstants.EMAIL_REGEX
import org.ahant.validator.constants.ApplicationConstants.INVALID_DATE
import org.ahant.validator.constants.ApplicationConstants.INVALID_EMAIL
import org.ahant.validator.constants.ApplicationConstants.INVALID_PHONE
import org.ahant.validator.constants.ApplicationConstants.INVALID_ZIP
import org.ahant.validator.constants.ApplicationConstants.PHONE_NUMBER_REGEX
import org.ahant.validator.constants.ApplicationConstants.ZIP_CODE_REGEX

/**
 * Created by ahant on 8/14/2016.
 */
enum class FieldValidatorType constructor(private val validator: FieldValidator<*>) {
    DEFAULT(DefaultFieldValidator()),
    STRING(StringFieldValidator()),
    PHONE(StringFieldValidator(PHONE_NUMBER_REGEX, INVALID_PHONE)),
    ZIP(StringFieldValidator(ZIP_CODE_REGEX, INVALID_ZIP)),
    EMAIL(StringFieldValidator(EMAIL_REGEX, INVALID_EMAIL, 30, 10)),
    DATE(StringFieldValidator(DATE_REGEX, INVALID_DATE)),
    CUSTOM(CustomTypeFieldValidator());

    fun get(): FieldValidator<*> {
        return validator
    }
}
