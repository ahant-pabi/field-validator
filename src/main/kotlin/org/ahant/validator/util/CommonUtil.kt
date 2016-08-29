package org.ahant.validator.util

import com.google.common.base.Strings
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
            return Strings.isNullOrEmpty(input)
        }

        fun isNotBlank(input: String): Boolean {
            return !isBlank(input)
        }

        fun log(clazz: KClass<*>, severity: LogSeverity, msg: String) {
            logger = LoggerFactory.getLogger(clazz.javaObjectType)
            when (severity) {
                CommonUtil.LogSeverity.DEBUG -> logger!!.debug(msg)
                CommonUtil.LogSeverity.INFO -> logger!!.info(msg)
                CommonUtil.LogSeverity.WARN -> logger!!.warn(msg)
                CommonUtil.LogSeverity.ERROR -> logger!!.error(msg)
            }
        }

        fun log(clazz: KClass<*>, msg: String) {
            log(clazz, LogSeverity.DEBUG, msg)
        }
    }
}
