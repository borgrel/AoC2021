package aoc.runner;

import aoc.utils.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public enum Invoker {
    ;

    private static final Logger logger = LoggerFactory.getLogger(Invoker.class);

    // ----------++- DYNAMIC START UTILITIES -++---------------------------------------
    public static <T extends Day> T invokeDay(int value) {
        return invokeDay(Days.dayFromInt(value));
    }

    public static <T extends Day> T invokeDay(Days dayNum) {
        String className = dayNum.getClassName();
        try {
            return invoke(className);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            logger.error("Error invoking and instantiating %s via reflection".formatted(className));
            throw new IllegalArgumentException("Unable to locate .class for '%s'".formatted(className), e);
        }
    }

    public static <T> T invoke(String className) throws ClassNotFoundException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        T t = (T) DayRunner.class.getClassLoader().loadClass(className).getConstructors()[0].newInstance();
        return t;
    }
}
