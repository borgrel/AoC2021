package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day05Example implements Example {
    public static final String input = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(input.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "5";
    }

    @Override
    public String resultPart2() {
        return "12";
    }
}
