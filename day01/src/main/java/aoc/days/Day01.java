package aoc.days;

import aoc.utils.AbstractDay;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

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

    private long countIncreasingDifferences(IntList list,int gap) {
        return IntStream.range(gap, list.size())
                .map(i -> list.get(i) - list.get(i - gap))
                .filter(i -> i > 0)
                .count();
    }

    @Override //1715
    public void part1() {
        result1 = Long.toString(
                countIncreasingDifferences(listInput,1));
    }

    @Override //1739
    public void part2() {
        result2 = Long.toString(
                countIncreasingDifferences(listInput,3));
    }
}
