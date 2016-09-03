package com.github.ahant.validator.validation

import com.github.ahant.validator.validation.util.RequiredFieldValidator

/**
 * Created by ahant on 8/14/2016.
 */
internal class CustomTypeFieldValidator : FieldValidator<Any> {

    override fun validate(input: Any): Set<String> {
        return RequiredFieldValidator.validate(input, FieldValidationType.FAIL_FAST)
    }
}
