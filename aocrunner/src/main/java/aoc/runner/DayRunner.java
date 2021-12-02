package aoc.runner;

import aoc.utils.Day;
import aoc.utils.Example;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DayRunner {
    ;

    static final Logger logger = LoggerFactory.getLogger(DayRunner.class);

    static final String INCOMPLETE = "No solution yet";

    private static @NotNull String printResults(@NotNull Day day) {
        String title = day.getClass().getSimpleName() + ":";
        return Stream.of(
                        title,
                        day.result1().orElse(INCOMPLETE),
                        day.result2().orElse(""))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.joining("\n  "));
    }

    private static void printExampleResults(String description, String example, Optional<String> result) {
        if (example.isEmpty()) {
            if (logger.isWarnEnabled())
                logger.warn("No solution for Example provided for %s".formatted(description));
        } else {
            result.filter(str -> str.equals(example))
                    .ifPresentOrElse(str -> logger.info("%s Example run succeeded".formatted(description)),
                            () -> logger.warn("%s Example run failed".formatted(description)));
        }
    }

    private static void runExample(Days forDay) {
        final Optional<Example> optionalExample = Invoker.invokeExample(forDay);
        if (optionalExample.isEmpty()) {
            if (logger.isWarnEnabled()) {
                logger.warn("No Example prepared for {}", forDay.upperCaseFirstLetter());
            }
            return;
        }

        final Day day = Invoker.invokeDay(forDay);
        Example example = optionalExample.orElseThrow();
        day.convertInput(example.sampleInput());
        day.part1();
        day.part2();

        printExampleResults("Part 1", example.resultPart1(), day.result1());
        printExampleResults("Part 2", example.resultPart2(), day.result2());
    }

    @SuppressWarnings("java:S106")
    public static void run(Days forDay) {
        if (logger.isInfoEnabled()) {
            logger.info("Running starter for %s%n".formatted(forDay.upperCaseFirstLetter()));
        }

        runExample(forDay);
        final Day day = Invoker.invokeDay(forDay);

        Optional<Stream<String>> input = FileLoader.readInput(forDay);
        if (input.isEmpty()) {
            if (logger.isErrorEnabled())
                logger.error("The %s does not have a text file to read.".formatted(forDay.upperCaseFirstLetter()));
            return;
        }
        day.convertInput(input.orElseThrow());
        day.part1();
        day.part2();

        System.out.println(printResults(day));
    }

    public static void run(int dayIndex) {
        run(Days.dayFromInt(dayIndex));
    }
}
