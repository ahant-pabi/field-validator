package org.ahant.inputvalidator.validation;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static org.ahant.inputvalidator.util.CommonUtil.isNotBlank;

/**
 * Created by ahant on 8/14/2016.
 */
class StringFieldValidator implements FieldValidator<String> {

    private String regEx;
    private String msg;
    private int maxLength;
    private int minLength;

    StringFieldValidator() {
    }

    StringFieldValidator(String regEx, String msg) {
        this(regEx, msg, 0, 0);
    }

    StringFieldValidator(String regEx, String msg, int maxLength, int minLength) {
        this.regEx = regEx;
        this.msg = msg;
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    @Override
    public Set<String> validate(String input) {
        boolean isValid = false;
        if (isNotBlank(input)) {
            isValid = true;
            if (minLength > 0) {
                isValid = input.length() >= minLength;
            }
            if (isValid && maxLength > 0) {
                isValid = input.length() <= maxLength;
            }
            if (isValid) {
                isValid = isNotBlank(regEx) ? input.matches(regEx) : true;
            }
        }
        return isValid ? null : ImmutableSet.of(isNotBlank(msg) ? String.format(msg, input) : "Invalid value: " + input);
    }
}
