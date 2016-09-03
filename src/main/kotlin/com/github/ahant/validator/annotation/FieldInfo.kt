package com.github.ahant.validator.annotation

import com.github.ahant.validator.validation.FieldValidatorType
/**
 * Created by ahant on 8/28/2016.
 */
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class FieldInfo(
        val name: String = "",
        val optional: Boolean = true,
        val validatorType: FieldValidatorType = FieldValidatorType.DEFAULT)
