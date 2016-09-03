package com.github.ahant.validator.validation

import com.github.ahant.validator.constants.ApplicationConstants.DATE_REGEX
import com.github.ahant.validator.constants.ApplicationConstants.EMAIL_REGEX
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_DATE
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_EMAIL
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_PHONE
import com.github.ahant.validator.constants.ApplicationConstants.INVALID_ZIP
import com.github.ahant.validator.constants.ApplicationConstants.PHONE_NUMBER_REGEX
import com.github.ahant.validator.constants.ApplicationConstants.ZIP_CODE_REGEX
import com.google.common.collect.ImmutableSet

/**
 * Created by ahant on 8/14/2016.
 */
enum class FieldValidatorType constructor(private val validator: FieldValidator<Any>) {
    DEFAULT(DefaultFieldValidator()),
    STRING(StringFieldValidator()),
    PHONE(StringFieldValidator(PHONE_NUMBER_REGEX, INVALID_PHONE)),
    ZIP(StringFieldValidator(ZIP_CODE_REGEX, INVALID_ZIP)),
    EMAIL(StringFieldValidator(EMAIL_REGEX, INVALID_EMAIL, 30, 10)),
    DATE(StringFieldValidator(DATE_REGEX, INVALID_DATE)),
    CUSTOM(CustomTypeFieldValidator());

    fun get(): FieldValidator<Any> = validator

    private class DefaultFieldValidator : FieldValidator<Any> {
        override fun validate(input: Any): Set<String> {
            return ImmutableSet.of()
        }
    }
}
