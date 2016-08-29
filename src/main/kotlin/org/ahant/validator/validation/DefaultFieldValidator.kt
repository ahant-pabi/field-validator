package org.ahant.validator.validation

import com.google.common.collect.ImmutableSet

/**
 * Created by ahant on 8/14/2016.
 */
internal class DefaultFieldValidator : FieldValidator<Any> {
    override fun validate(input: Any): Set<String> {
        return if (null != input) ImmutableSet.of() else ImmutableSet.of("null input")
    }
}
