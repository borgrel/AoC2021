package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day02Example implements Example {
    private static final String sampleInput = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(sampleInput.split("\n"));
    }

    @Override //150
    public String resultPart1() {
        return "150";
    }

    @Override //900
    public String resultPart2() {
        return "900";
    }
}
