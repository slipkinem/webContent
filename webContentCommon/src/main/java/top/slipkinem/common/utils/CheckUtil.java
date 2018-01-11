package top.slipkinem.common.utils;

import org.springframework.context.MessageSource;
import top.slipkinem.common.excepitons.CheckException;

import java.util.Locale;

/**
 * Created by slipkinem on 1/11/2018.
 */
public class CheckUtil {
    private static MessageSource messageSource;

    public static void setMessageSource(MessageSource messageSource) {
        CheckUtil.messageSource = messageSource;
    }

    private static void fail(String message, Object... args) {
        throw new CheckException(messageSource.getMessage(message, args, Locale.CHINA));
    }

    public static void check(boolean condition, String message, Object ...args) {
        if (!condition) {
            fail(message, args);
        }
    }

    public static void notNull(Object object, String message, Object ...args) {
        if (object == null) {
            fail(message, args);
        }
    }

    public static void notEmpty(String string, String message, Object ...args) {
        if (string == null || string.isEmpty()) {
            fail(message, args);
        }
    }
}
