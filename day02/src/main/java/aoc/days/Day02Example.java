package aoc.days;

import aoc.utils.Day;
import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day02Example implements Example {
    String sampleInput = """
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
    @Override
    public String sampleResultPart1() {
        return "150";
    }
    @Override
    public String sampleResultPart2() {
        return "";
    }

    public static void main(String[] args) {
        Example example = new Day02Example();
        Day day = new Day02();
        day.convertInput(example.sampleInput());
        day.part1();
        day.part2();

        System.out.println(day.result1());
        System.out.println(day.result2());
    }
}
