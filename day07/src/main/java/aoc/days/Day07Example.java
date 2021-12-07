package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day07Example implements Example {
    public static final String input = "16,1,2,0,4,2,7,1,2,14";

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(input.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "37";
    }

    @Override
    public String resultPart2() {
        return "168";
    }
}
