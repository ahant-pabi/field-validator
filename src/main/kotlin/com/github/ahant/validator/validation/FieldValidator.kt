package com.github.ahant.validator.validation

import com.github.ahant.validator.validation.util.CountryCode

/**
 * Created by ahant on 8/14/2016.
 */
@FunctionalInterface
internal interface FieldValidator<in T> {
    fun validate(input: T): Set<String>
    fun setCountry(countryCode: CountryCode){}
}
