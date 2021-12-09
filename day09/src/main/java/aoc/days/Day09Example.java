package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day09Example implements Example {
    public static final String input = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(input.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "15";
    }

    @Override
    public String resultPart2() {
        return "1134";
    }
}
