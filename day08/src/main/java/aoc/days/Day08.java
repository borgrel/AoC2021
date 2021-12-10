package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Regex;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day08 extends AbstractDay {
    private static final Pattern outputValues = Pattern.compile(" \\| ");
    private static final Pattern values = Pattern.compile(" ");

    private List<Puzzle> input;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        input = stream
                .map(outputValues::split)
                .map(array -> {
                    List<String> puzzle = Arrays.stream(Regex.splitAtWhiteSpace(array[0])).toList();
                    List<String> output = Arrays.stream(Regex.splitAtWhiteSpace(array[1])).toList();
                    return new Puzzle(puzzle, output);
                })
                .toList();
    }

    @Override //301
    public void part1() {
        result1 = Long.toString(
                input.stream()
                        .map(Puzzle::outputs)
                        .flatMap(List::stream)
                        .mapToInt(String::length)
                        .filter(i -> (i == 2 || i == 3 || i == 4 || i == 7))
                        .count()
        );
    }

    @Override //908067
    public void part2() {
        result2 = Long.toString(input.stream()
                .mapToInt(Segments::solverFor)
                .sum());
    }
}