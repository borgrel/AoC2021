package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day03Example implements Example {
    public static final String example = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(example.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "198";
    }

    @Override
    public String resultPart2() {
        return "230";
    }
}
