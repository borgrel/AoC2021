package aoc.days;

import aoc.utils.AbstractDay;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day06 extends AbstractDay {
    private static final int BREEDING_TIME = 7;
    private static final int MATURATION_TIME = 9;

    long[] lanternFishInitial;
    long[] lanternFishPart1;
    long[] lanternFishPart2;

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        lanternFishInitial = new long[MATURATION_TIME + 1];
        stream.map(str -> str.split(","))
                .flatMap(Arrays::stream)
                .mapToInt(Integer::parseInt)
                .forEach(i -> lanternFishInitial[i]++);
    }

    private long[] simulateLifeCycle(long[] lanternFish, int from, int until) {
        long[] simulating = Arrays.copyOf(lanternFish, lanternFish.length);
        for (int i = from; i <= until; i++) {
            simulating[BREEDING_TIME] += simulating[0];
            simulating[MATURATION_TIME] += simulating[0];

            long[] temp = new long[MATURATION_TIME + 1];
            System.arraycopy(simulating, 1, temp, 0, 9);
            simulating = temp;
        }
        return simulating;
    }

    @Override //390923
    public void part1() {
        lanternFishPart1 = simulateLifeCycle(lanternFishInitial, 1, 80);
        result1 = Long.toString(Arrays.stream(lanternFishPart1).sum());
    }

    @Override //1749945484935
    public void part2() {
        lanternFishPart2 = simulateLifeCycle(lanternFishPart1, 81, 256);
        result2 = Long.toString(Arrays.stream(lanternFishPart2).sum());
    }
}
