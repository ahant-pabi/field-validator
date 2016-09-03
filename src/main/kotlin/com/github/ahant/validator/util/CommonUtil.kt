package com.github.ahant.validator.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

/**
 * Created by ahant on 7/23/2016.
 */
class CommonUtil private constructor() {

    init {
        throw IllegalAccessError("Utility class")
    }

    enum class LogSeverity {
        DEBUG, INFO, WARN, ERROR
    }

    companion object {
        private var logger: Logger? = null

        fun isBlank(input: String): Boolean {
            return input.isEmpty()
        }

        fun isNotBlank(input: String): Boolean {
            return !isBlank(input)
        }

        fun log(clazz: KClass<*>, severity: LogSeverity, msg: String) {
            logger = LoggerFactory.getLogger(clazz.javaObjectType)
            when (severity) {
                LogSeverity.DEBUG -> logger!!.debug(msg)
                LogSeverity.INFO -> logger!!.info(msg)
                LogSeverity.WARN -> logger!!.warn(msg)
                LogSeverity.ERROR -> logger!!.error(msg)
            }
        }

        fun log(clazz: KClass<*>, msg: String) {
            log(clazz, LogSeverity.DEBUG, msg)
        }
    }
}
