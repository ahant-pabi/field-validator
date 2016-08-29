package org.ahant.inputvalidator.annotation

import org.ahant.inputvalidator.validation.FieldValidatorType

/**
 * Created by ahant on 8/28/2016.
 */
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
public annotation class FieldInfo(
        val name: String = "",
        val optional: Boolean = true,
        val validatorType: FieldValidatorType = FieldValidatorType.DEFAULT)
