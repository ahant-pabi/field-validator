package com.github.ahant.validator.validation

/**
 * Created by ahant on 8/14/2016.
 */
@FunctionalInterface
internal interface FieldValidator<in T> {
    fun validate(input: T): Set<String>
}
