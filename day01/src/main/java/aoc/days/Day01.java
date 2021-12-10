package aoc.days;

import aoc.utils.AbstractDay;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day01 extends AbstractDay {
    IntList listInput;

    @Override
    public void convertInput(Stream<String> stream) {
        listInput = new IntArrayList(
                stream.mapToInt(Integer::parseInt)
                        .toArray()
        );
    }

    private long countIncreasingDifferences(IntList list, int gap) {
        return IntStream.range(gap, list.size())
                .map(i -> list.getInt(i) - list.getInt(i - gap))
                .filter(i -> i > 0)
                .count();
    }

    @Override //1715
    public void part1() {
        result1 = Long.toString(
                countIncreasingDifferences(listInput, 1));
    }

    @Override //1739
    public void part2() {
        result2 = Long.toString(
                countIncreasingDifferences(listInput, 3));
    }
}
