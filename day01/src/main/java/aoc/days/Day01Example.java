package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;


class Day01Example implements Example {
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

    public static void main(String[] args) {
        new Day01Example().testExamples();
    }

    //@Override
    void testExamples() {
        Day01 day = new Day01();
        day.convertInput(sampleInput());
        day.part1();
        day.part2();

        System.out.println(day.result1());
        System.out.println(day.result2());
    }

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(testInput.split("\n"));
    }

    @Override
    public String resultPart1() {
        return null;
    }

    @Override public String resultPart2() {
        return null;
    }
}