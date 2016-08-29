package org.ahant.validator.validation

import com.google.common.base.Preconditions.checkArgument
import org.ahant.validator.validation.util.RequiredFieldValidator

/**
 * Created by ahant on 8/14/2016.
 */
internal class CustomTypeFieldValidator : FieldValidator<Any> {

    override fun validate(type: Any): Set<String> {
        checkArgument(type != null, type!!.javaClass.simpleName + "can't be null")
        return RequiredFieldValidator.validate(type, FieldValidationType.FAIL_FAST)
    }
}
