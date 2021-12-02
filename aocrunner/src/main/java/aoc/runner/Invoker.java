package aoc.runner;

import aoc.utils.Day;
import aoc.utils.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public enum Invoker {
    ;

    private static final Logger logger = LoggerFactory.getLogger(Invoker.class);

    // ----------++- DYNAMIC START UTILITIES -++---------------------------------------
    public static <T extends Day> T invokeDay(int dayIndex) {
        return invokeDay(Days.dayFromInt(dayIndex));
    }

    public static <T extends Day> T invokeDay(Days forDay) {
        return invoke(forDay.getDayClassName());
    }

    public static <T extends Example> Optional<T> invokeExample(int dayIndex) {
        return invokeExample(Days.dayFromInt(dayIndex));
    }

    public static <T extends Example> Optional<T> invokeExample(Days forDay) {
        try {
            return Optional.of(
                    invoke(forDay.getExampleClassName()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Optional.empty();
        }
    }

    static <T> T invoke(String className) {
        try {
            @SuppressWarnings("unchecked")
            T t = (T) Booter.class.getClassLoader().loadClass(className).getConstructors()[0].newInstance();
            return t;
        } catch (ClassNotFoundException | IllegalAccessException e) {
            logger.error("Error invoking %s via reflection".formatted(className));
            throw new IllegalArgumentException("Unable to locate .class for '%s'".formatted(className), e);

        } catch (InvocationTargetException | InstantiationException e) {
            logger.error("Error instantiating %s via reflection".formatted(className));
            throw new IllegalStateException("Class '%s' does not have a public no argument constructor.".formatted(className), e);
        }
    }
}
