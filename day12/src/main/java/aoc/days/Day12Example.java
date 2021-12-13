package aoc.days;

import aoc.utils.Example;
import aoc.utils.Regex;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day12Example implements Example {
    public static final String input = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
            """;

    @Override
    public Stream<String> sampleInput() {
        return Arrays.stream(Regex.splitAtNewLine(input));
    }

    @Override
    public String resultPart1() {
        return "19";
    }

    @Override
    public String resultPart2() {
        return "";
    }
}
