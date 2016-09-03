package com.github.ahant.validator.validation

import com.github.ahant.validator.util.CommonUtil.isNotBlank
import com.google.common.collect.ImmutableSet
/**
 * Created by ahant on 8/14/2016.
 */
internal class StringFieldValidator : FieldValidator<Any> {

    private var regEx = ""
    private var msg = ""
    private var maxLength = 0
    private var minLength = 0

    constructor() {
    }

    @JvmOverloads constructor(regEx: String, msg: String, maxLength: Int = 0, minLength: Int = 0) {
        this.regEx = regEx
        this.msg = msg
        this.maxLength = maxLength
        this.minLength = minLength
    }

    override fun validate(input: Any): Set<String> {
        var isValid = false
        if (input is String) {
            if (isNotBlank(input)) {
                isValid = true
                if (minLength > 0) {
                    isValid = input.length >= minLength
                }
                if (isValid && maxLength > 0) {
                    isValid = input.length <= maxLength
                }
                if (isValid) {
                    isValid = if (isNotBlank(regEx)) input.matches(regEx.toRegex()) else true
                }
            }
        }
        return if (isValid) ImmutableSet.of() else ImmutableSet.of(if (isNotBlank(msg)) String.format(msg, input) else "Invalid value: " + input)
    }
}
