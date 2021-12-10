package aoc.days;

import aoc.utils.Example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day10Example implements Example {
    public static final String input = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
            """;

    // [({(<(())[]>[[{[]{<()<>>
    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(input.split("\n"));
    }

    @Override
    public String resultPart1() {
        return "26397";
    }

    @Override
    public String resultPart2() {
        return "288957";
    }
}
