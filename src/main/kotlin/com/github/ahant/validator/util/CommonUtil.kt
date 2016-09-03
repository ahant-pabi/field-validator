package com.github.ahant.validator.util

/**
 * Created by ahant on 7/23/2016.
 */
internal object CommonUtil {

    fun isBlank(input: String?): Boolean {
        return input != null && input.isEmpty()
    }

    fun isNotBlank(input: String?): Boolean {
        return !isBlank(input)
    }
}