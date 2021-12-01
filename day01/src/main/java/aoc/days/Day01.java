package aoc.days;

import aoc.utils.AbstractDay;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day01 extends AbstractDay {
    IntList listInput;
    IntList listTripleSum;

    @Override
    public void convertInput(Stream<String> stream) {
        listInput = new IntArrayList(
                stream.mapToInt(Integer::parseInt)
                        .toArray()
        );
        listTripleSum = new IntArrayList(
                IntStream.range(2, listInput.size())
                        .map(i -> listInput.get(i - 2) + listInput.get(i - 1) + listInput.get(i))
                        .toArray()
        );
    }

    private long countIncreasingDifferences(IntList list) {
        return IntStream.range(1, list.size())
                .map(i -> list.get(i) - list.get(i - 1))
                .filter(i -> i > 0)
                .count();
    }

    @Override //1715
    public void part1() {
        long result = countIncreasingDifferences(listInput);
        result1 = Long.toString(result);
    }

    @Override //1739
    public void part2() {
        long result = countIncreasingDifferences(listTripleSum);
        result2 = Long.toString(result);
    }
}
