package org.ahant.validator.validation

/**
 * Created by ahant on 8/14/2016.
 */
@FunctionalInterface
interface FieldValidator<T> {
    fun validate(input: T): Set<String>
}
