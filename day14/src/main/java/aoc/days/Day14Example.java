package aoc.days;

import aoc.utils.Example;
import aoc.utils.Regex;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day14Example implements Example {
    public static final String input = """
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(Regex.splitAtNewLine(input));
    }

    @Override
    public String resultPart1() {
        return "";
    }

    @Override
    public String resultPart2() {
        return "";
    }
}
