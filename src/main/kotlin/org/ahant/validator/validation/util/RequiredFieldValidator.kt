package org.ahant.validator.validation.util

import com.google.common.collect.Sets
import org.ahant.validator.annotation.CollectionType
import org.ahant.validator.annotation.FieldInfo
import org.ahant.validator.constants.ApplicationConstants.COLLECTION_MIN_SIZE_ERROR
import org.ahant.validator.constants.ApplicationConstants.REQUIRED_FIELD_MISSING
import org.ahant.validator.util.CommonUtil.Companion.isNotBlank
import org.ahant.validator.validation.FieldValidationType

/**
 * Created by ahant on 8/14/2016.
 */
object RequiredFieldValidator {

    /**
     * Get all the declared fields of 'type' object and invoke their respective field validators. throw exception if validator returns false.
     * The thrown exception must be of type Application exception with message as required field missing along with field name.
     */
    fun validate(type: Any, validationType: FieldValidationType): Set<String> {
        return performFieldValidation(type, validationType)
    }

    /**
     * Iterates over all declared fields and performs validation using their declared validator or default validator if none has been provided.

     * @param type                      type instance for which validation needs to be performed
     * *
     * @param validationType            If `FieldValidationType.FAIL_FAST`, the process terminates as soon as it encounters a failed scenario else continues validation.
     * *
     * @param requiredAnnotationPresent indicates if the given type object has 'Required' annotation at class level. If present, all of it's fields are considered as required
     * *                                  unless explicitly mentioned as 'optional'.
     * *
     * @return returns a set of error messages, if any or empty. It never returns `null`.
     */
    private fun performFieldValidation(type: Any, validationType: FieldValidationType): Set<String> {
        val errors = Sets.newHashSet<String>()
        val fields = type.javaClass.declaredFields
        for (field in fields) {
            field.isAccessible = true
            val info = field.getAnnotation(FieldInfo::class.java)
            var fieldValue: Any?
            try {
                fieldValue = field.get(type)
            } catch (e: IllegalAccessException) {
                // ignore exception and either terminate or continue based on validationType.
                if (FieldValidationType.CONTINUE == validationType) {
                    continue
                } else {
                    errors.add(e.message)
                    return errors
                }
            }

            val fieldName = if (info != null && isNotBlank(info.name)) info.name else field.name
            if (info != null && !info.optional && fieldValue == null) {
                errors.add(getExceptionMessage(fieldName))
            }
            /**
             * continue if 1) the field has a non null value
             * 2) there are no errors OR validation type is {@code FieldValidationType.CONTINUE}
             * 3) the field a validator type declared
             */
            if (fieldValue != null && (FieldValidationType.CONTINUE == validationType || errors.isEmpty()) && (info != null)) {
                val validator = info.validatorType
                var fieldError: Set<String> = Sets.newHashSet<String>()
                val collectionAnnotation = field.getAnnotation(CollectionType::class.java)
                if (collectionAnnotation == null) {
                    fieldError = validator.get().validate(fieldValue)
                } else {
                    val collectionData = fieldValue as Collection<*>
                    if (collectionData.size < collectionAnnotation.minSize) {
                        errors.add(getCollectionErrorMessage(fieldName, collectionAnnotation.minSize))
                    } else {
                        val collectionFieldIterator = collectionData.iterator()
                        while (collectionFieldIterator.hasNext()) {
                            val collectionValue = collectionFieldIterator.next()
                            val tempErrors = validator.get().validate(collectionValue as Any)
                            fieldError.plus(tempErrors)
                        }
                    }
                }
                errors.addAll(fieldError)
            }
            if (FieldValidationType.FAIL_FAST == validationType && !errors.isEmpty()) {
                break
            }
        }
        return errors
    }

    private fun getExceptionMessage(fieldName: String): String {
        return String.format(REQUIRED_FIELD_MISSING, fieldName)
    }

    private fun getCollectionErrorMessage(fieldName: String, minSize: Int): String {
        return String.format(COLLECTION_MIN_SIZE_ERROR, minSize, fieldName)
    }
}
