package aoc.days;

import aoc.utils.Example;
import aoc.utils.Regex;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day11Example implements Example {
    public static final String input = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(Regex.splitAtNewLine(input));
    }

    @Override
    public String resultPart1() {
        return "1656";
    }

    @Override
    public String resultPart2() {
        return "195";
    }
}
