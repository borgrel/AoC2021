package aoc.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {
    public static TextBlockCollector toTextBlock() {
        return new TextBlockCollector();
    }

    // ----------++- CONSOLE UTILITIES -++---------------------------------------
    public static <T> String printArray(T[][] input) {
        return Arrays.stream(input)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }

    public static <T> String printArray(T[] input) {
        return Arrays.stream(input)
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }

    public static <T> String printArray(List<T[]> input) {
        return input.stream()
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
}
