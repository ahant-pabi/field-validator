package org.ahant.validator.validation

import org.ahant.validator.annotation.FieldInfo
import org.ahant.validator.validation.FieldValidatorType.STRING
import org.ahant.validator.validation.FieldValidatorType.ZIP

/**
 * Created by ahant on 7/16/2016.
 */
class Address {
    @FieldInfo(validatorType = STRING, optional = false)
    var addressLine1: String? = null
    @FieldInfo(optional = true, validatorType = STRING)
    var addressLine2: String? = null
    @FieldInfo(validatorType = STRING, optional = false)
    var city: String? = null
    @FieldInfo(validatorType = STRING, optional = false)
    var state: String? = null
    @FieldInfo(validatorType = STRING, optional = false)
    var country = "India"
    @FieldInfo(validatorType = ZIP, optional = false)
    var zip: String? = null
}
