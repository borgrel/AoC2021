package aoc.runner;

import aoc.utils.Day;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayRunner {
    static final Logger logger = LoggerFactory.getLogger(DayRunner.class);

    static final String INCOMPLETE = "No solution yet";

    private static @NotNull
    String printResults(@NotNull Day solution) {
        String title = solution.getClass().getSimpleName() + ":";
        return Stream.of(
                        title,
                        solution.result1().orElse(INCOMPLETE),
                        solution.result2().orElse(""))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.joining("\n  "));
    }

    public static void run(int forDay) {
        if (logger.isInfoEnabled()) {
            logger.info("Running starter for Day%02d%n".formatted(forDay));
        }

        final Days day = Days.dayFromInt(forDay);
        final Day solution = Invoker.invokeDay(day);

        Optional<Stream<String>> input = FileLoader.readInput(day);
        if (input.isEmpty()) {
            if (logger.isErrorEnabled())
                logger.error("The Day%02d does not have a text file to read.%n".formatted(forDay));
            return;
        }
        solution.convertInput(input.get());
        solution.part1();
        solution.part2();

        System.out.println(printResults(solution));
    }
}
