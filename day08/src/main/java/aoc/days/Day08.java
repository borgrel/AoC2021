package aoc.days;

import aoc.utils.AbstractDay;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
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
                    List<String> puzzle = Arrays.stream(values.split(array[0])).toList();
                    List<String> output = Arrays.stream(values.split(array[1])).toList();
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
        AtomicLong sum = new AtomicLong(0);
        input.forEach(puzzle -> {
            Segments solver = Segments.solverOf(puzzle.patterns());
            int value = solver.getOutput(puzzle.outputs());
            sum.getAndAdd(value);
        });
        result2 = Long.toString(sum.get());
    }
}
