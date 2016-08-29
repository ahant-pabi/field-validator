package org.ahant.validator.validation;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * Created by ahant on 8/14/2016.
 */
class DefaultFieldValidator implements FieldValidator<Object> {
    @Override
    public Set<String> validate(Object input) {
        return null != input ? null : ImmutableSet.of("null input");
    }
}
