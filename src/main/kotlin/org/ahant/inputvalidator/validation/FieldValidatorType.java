package org.ahant.inputvalidator.validation;

import static org.ahant.inputvalidator.constants.ApplicationConstants.*;

/**
 * Created by ahant on 8/14/2016.
 */
public enum FieldValidatorType {
    DEFAULT(new DefaultFieldValidator()),
    STRING(new StringFieldValidator()),
    PHONE(new StringFieldValidator(PHONE_NUMBER_REGEX, INVALID_PHONE)),
    ZIP(new StringFieldValidator(ZIP_CODE_REGEX, INVALID_ZIP)),
    EMAIL(new StringFieldValidator(EMAIL_REGEX, INVALID_EMAIL, 30, 10)),
    DATE(new StringFieldValidator(DATE_REGEX, INVALID_DATE)),
    CUSTOM(new CustomTypeFieldValidator());

    private FieldValidator validator;

    FieldValidatorType(FieldValidator validator) {
        this.validator = validator;
    }

    public FieldValidator get() {
        return validator;
    }
}
