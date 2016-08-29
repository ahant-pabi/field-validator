package org.ahant.inputvalidator.util;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ahant on 7/23/2016.
 */
public class CommonUtil {
    private static Logger logger;

    private CommonUtil() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean isBlank(String input) {
        return Strings.isNullOrEmpty(input);
    }

    public static boolean isNotBlank(String input) {
        return !isBlank(input);
    }

    public static void log(Class<?> clazz, LogSeverity severity, String msg) {
        logger = LoggerFactory.getLogger(clazz);
        switch (severity) {
            case DEBUG:
                logger.debug(msg);
                break;
            case INFO:
                logger.info(msg);
                break;
            case WARN:
                logger.warn(msg);
                break;
            case ERROR:
                logger.error(msg);
                break;
        }
    }

    public static void log(Class<?> clazz, String msg) {
        log(clazz, LogSeverity.DEBUG, msg);
    }

    public enum LogSeverity {
        DEBUG, INFO, WARN, ERROR;
    }
}
