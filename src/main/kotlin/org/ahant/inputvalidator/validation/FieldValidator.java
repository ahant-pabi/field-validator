package org.ahant.inputvalidator.validation;

import java.util.Set;

/**
 * Created by ahant on 8/14/2016.
 */
@FunctionalInterface
public interface FieldValidator<T> {
    Set<String> validate(T input);
}
