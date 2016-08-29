package org.ahant.inputvalidator.annotation

/**
 * Created by ahant on 8/29/2016.
 */
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
public annotation class CollectionType(
        val minSize: Int = 1)