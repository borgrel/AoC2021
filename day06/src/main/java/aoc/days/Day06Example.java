package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day06Example implements Example {
    public static final String input = "3,4,3,1,2";

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(input.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "5934";
    }

    @Override
    public String resultPart2() {
        return "26984457539";
    }
}
