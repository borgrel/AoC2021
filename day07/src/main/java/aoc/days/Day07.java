package aoc.days;

import aoc.utils.AbstractDay;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

public class Day07 extends AbstractDay {
    private static final IntUnaryOperator increasingCost = i -> (i + 1) * i / 2;
    IntList depths;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        depths = new IntArrayList(
                stream.map(str -> str.split(","))
                        .flatMap(Arrays::stream)
                        .mapToInt(Integer::parseInt)
                        .toArray()
        );
    }

    private long calculateFuelUsed(IntList depths, int moveTo, IntUnaryOperator fuelCalc) {
        return depths.intStream().map(i -> Math.abs(i - moveTo)).map(fuelCalc).sum();
    }

    @Override
    public void part1() {
        int skipForMiddle = depths.size() / 2 - (depths.size() + 1) % 2;
        int[] medianValues = depths.intStream()
                .sorted()
                .skip(skipForMiddle)
                .limit((long) depths.size() - 2 * skipForMiddle)
                .toArray();
        int median = Arrays.stream(medianValues).sum() / medianValues.length;

        long fuelUsed = calculateFuelUsed(depths, median, i -> i);

        result1 = Long.toString(fuelUsed);
    }

    @Override //95476248 too high
    public void part2() {
        int forDepth = (int) Math.round(depths.intStream().sum() / (double) depths.size());
        long fuelUsed1 = calculateFuelUsed(depths, forDepth--, increasingCost);
        long fuelUsed2 = calculateFuelUsed(depths, forDepth--, increasingCost);
        while (fuelUsed2 < fuelUsed1) {
            fuelUsed1 = fuelUsed2;
            fuelUsed2 = calculateFuelUsed(depths, forDepth--, increasingCost);
        }

        result2 = Long.toString(fuelUsed1);
    }
}
