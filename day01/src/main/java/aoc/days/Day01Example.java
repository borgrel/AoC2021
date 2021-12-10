package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day01Example implements Example {
    private static final String testInput = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(testInput.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "7";
    }

    @Override public String resultPart2() {
        return "5";
    }
}