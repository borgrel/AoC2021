package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Regex;
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
        lanternFishInitial = new long[MATURATION_TIME];
        stream.map(Regex::splitAtCommas)
                .flatMap(Arrays::stream)
                .mapToInt(Integer::parseInt)
                .forEach(i -> lanternFishInitial[i]++);
    }

    private long[] simulateLifeCycle(long[] lanternFish, int from, int until) {
        CircularLongArray simulating = new CircularLongArray(lanternFish);
        for (int i = from; i <= until; i++) {
            simulating.add(BREEDING_TIME, simulating.peek());
            simulating.advance();
        }
        return simulating.toArray();
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
