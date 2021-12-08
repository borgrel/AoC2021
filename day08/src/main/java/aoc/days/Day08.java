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

    private List<String> input;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        input = stream.toList();
    }

    @Override //301
    public void part1() {
        result1 = Long.toString(
                input.stream()
                        .map(outputValues::split)
                        .map(array -> array[1])
                        .map(values::split)
                        .flatMap(Arrays::stream)
                        .mapToInt(String::length)
                        .filter(i -> (i == 2 || i == 3 || i == 4 || i == 7))
                        .count()
        );
    }

    @Override //908067
    public void part2() {
        AtomicLong sum = new AtomicLong(0);
        input.stream().map(outputValues::split)
                .forEach(array -> {
                    Segments solver = new Segments();
                    solver.solve(values.split(array[0]));
                    int value = solver.getOutput(values.split(array[1]));
                    sum.getAndAdd(value);
                });
        result2 = Long.toString(sum.get());
    }
}
