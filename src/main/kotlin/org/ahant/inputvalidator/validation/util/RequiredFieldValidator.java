package org.ahant.inputvalidator.validation.util;

import com.google.common.collect.Sets;
import org.ahant.inputvalidator.annotation.CollectionType;
import org.ahant.inputvalidator.annotation.FieldInfo;
import org.ahant.inputvalidator.annotation.Required;
import org.ahant.inputvalidator.validation.FieldValidationType;
import org.ahant.inputvalidator.validation.FieldValidatorType;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static org.ahant.inputvalidator.constants.ApplicationConstants.COLLECTION_MIN_SIZE_ERROR;
import static org.ahant.inputvalidator.constants.ApplicationConstants.REQUIRED_FIELD_MISSING;
import static org.ahant.inputvalidator.util.CommonUtil.isNotBlank;

/**
 * Created by ahant on 8/14/2016.
 */
public class RequiredFieldValidator {

    /**
     * Get all the declared fields of 'type' object and invoke their respective field validators. throw exception if validator returns false.
     * The thrown exception must be of type Application exception with message as required field missing along with field name.
     */
    public static Set<String> validate(Object type, FieldValidationType validationType) {
        checkArgument(type != null, "type can't be null");
        return performFieldValidation(type, validationType, type.getClass().getAnnotation(Required.class) != null);
    }

    /**
     * Iterates over all declared fields and performs validation using their declared validator or default validator if none has been provided.
     *
     * @param type                      type instance for which validation needs to be performed
     * @param validationType            If <code>FieldValidationType.FAIL_FAST</code>, the process terminates as soon as it encounters a failed scenario else continues validation.
     * @param requiredAnnotationPresent indicates if the given type object has 'Required' annotation at class level. If present, all of it's fields are considered as required
     *                                  unless explicitly mentioned as 'optional'.
     * @return returns a set of error messages, if any or empty. It never returns <code>null</code>.
     */
    private static Set<String> performFieldValidation(Object type, FieldValidationType validationType, boolean requiredAnnotationPresent) {
        Set<String> errors = Sets.newHashSet();
        Field[] fields = type.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            FieldInfo info = field.getAnnotation(FieldInfo.class);
            Object fieldValue = null;
            try {
                fieldValue = field.get(type);
            } catch (IllegalAccessException e) {
                // ignore exception and either terminate or continue based on validationType.
                if (FieldValidationType.CONTINUE.equals(validationType)) {
                    continue;
                } else {
                    errors.add(e.getMessage());
                    return errors;
                }
            }
            String fieldName = (info != null && isNotBlank(info.name())) ? info.name() : field.getName();
            if (info != null && !info.optional() && fieldValue == null) {
                errors.add(getExceptionMessage(fieldName));
            }
            //continue if there are no errors OR validation type is {@code FieldValidationType.CONTINUE}
            if (fieldValue != null && (FieldValidationType.CONTINUE.equals(validationType) || errors.isEmpty())) {
                FieldValidatorType validator = info != null ? info.validatorType() : FieldValidatorType.DEFAULT;
                Set<String> fieldError = Sets.newHashSet();
                CollectionType collectionAnnotation = field.getAnnotation(CollectionType.class);
                if (collectionAnnotation == null) {
                    fieldError = validator.get().validate(fieldValue);
                } else {
                    Collection collectionData = (Collection) fieldValue;
                    if (collectionData.size() < collectionAnnotation.minSize()) {
                        errors.add(getCollectionErrorMessage(fieldName, collectionAnnotation.minSize()));
                    } else {
                        Iterator collectionFieldIterator = collectionData.iterator();
                        while (collectionFieldIterator.hasNext()) {
                            Object collectionValue = collectionFieldIterator.next();
                            Set<String> tempErrors = validator.get().validate(collectionValue);
                            if (tempErrors != null) {
                                fieldError.addAll(tempErrors);
                            }
                        }
                    }
                }
                if (fieldError != null) {
                    errors.addAll(fieldError);
                }
            }
            if (FieldValidationType.FAIL_FAST.equals(validationType) && !errors.isEmpty()) {
                break;
            }
        }

        return errors;
    }

    private static String getExceptionMessage(String fieldName) {
        return String.format(REQUIRED_FIELD_MISSING, fieldName);
    }

    private static String getCollectionErrorMessage(String fieldName, int minSize) {
        return String.format(COLLECTION_MIN_SIZE_ERROR, minSize, fieldName);
    }
}
