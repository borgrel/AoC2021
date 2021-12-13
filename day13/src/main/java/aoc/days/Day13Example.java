package aoc.days;

import aoc.utils.Example;
import aoc.utils.Regex;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day13Example implements Example {
    public static final String input = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0
                        
            fold along y=7
            fold along x=5
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(Regex.splitAtNewLine(input));
    }

    @Override
    public String resultPart1() {
        return "17";
    }

    @Override
    public String resultPart2() {
        return "Need to map array to Letter";
    }
}
