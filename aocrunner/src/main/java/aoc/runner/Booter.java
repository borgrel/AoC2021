package aoc.runner;

import aoc.utils.Config;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Booter {
    static final Logger logger = LoggerFactory.getLogger(Booter.class);

    private static OptionalInt tryParseArg(@NotNull String input) {
        try {
            int value = Integer.parseInt(input);
            if (value < 0 || value > 25) {
                if (logger.isErrorEnabled())
                    logger.error("Invalid command line argument: '%d' is not between 0 and 25".formatted(value));
                return OptionalInt.empty();
            }
            return OptionalInt.of(value);
        } catch (NumberFormatException e) {
            logger.error("Unable to parse command line argument: '%s', all command line arguments should be ints between 0 and 25".formatted(input));
            return OptionalInt.empty();
        }
    }

    private static @NotNull int[] parseArgs(final String[] args) {
        return Arrays.stream(args)
                .skip(1)
                .map(Booter::tryParseArg)
                .filter(OptionalInt::isPresent)
                .mapToInt(OptionalInt::getAsInt)
                .toArray();
    }

    @SuppressWarnings("java:S106")
    private static void customRun() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter the day(s) you wish to run: ");
            try {
                DayRunner.run(
                        Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value '%s' is not a number.");
            }
        }
    }

    public static void start(int... days) {
        logger.info("This AoC project is for year {}", Config.year());
        if (days.length != 0) {
            logger.info("Command line arguments found, running requested days.");
            Arrays.stream(days).forEach(DayRunner::run);
            return;
        }
        OptionalInt today = Time.getCurrentAoCDay();
        if (today.isPresent()) {
            logger.info("Event time detected! Running today.");
            DayRunner.run(today.orElseThrow());
            return;
        }
        logger.info("No day specified by command line or current time.");
        customRun();
    }

    public static void main(String[] args) {
        start(parseArgs(args));
    }
}
