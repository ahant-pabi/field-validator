package org.ahant.validator.validation

import org.ahant.validator.annotation.CollectionType
import org.ahant.validator.annotation.FieldInfo
import org.ahant.validator.validation.FieldValidatorType.*
import java.util.*

/**
 * Created by ahant on 8/22/2016.
 */
class Person {
    @FieldInfo(validatorType = STRING, optional = false)
    var fullName: String? = null
    private var birthDate: Date? = null
    var gender: Gender? = null
    @FieldInfo(name = "contactNumber", validatorType = PHONE, optional = false)
    @CollectionType
    var contactNumberList: List<String>? = null
    @FieldInfo(validatorType = CUSTOM)
    var address: Address? = null
    @FieldInfo(name = "email", validatorType = EMAIL)
    var emailAddress: String? = null
    @FieldInfo(optional = false)
    private val score: Int = 0

    fun getBirthDate(): Date {
        return Date(birthDate!!.time)
    }

    fun setBirthDate(birthDate: Date) {
        this.birthDate = Date(birthDate.time)
    }
}