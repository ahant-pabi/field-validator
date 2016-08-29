package org.ahant.validator.validation;

import org.ahant.validator.validation.util.RequiredFieldValidator;

import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by ahant on 8/14/2016.
 */
class CustomTypeFieldValidator implements FieldValidator<Object> {

    @Override
    public Set<String> validate(Object type) {
        checkArgument(type != null, type.getClass().getSimpleName() + "can't be null");
        return RequiredFieldValidator.validate(type, FieldValidationType.FAIL_FAST);
    }
}
