package aoc.days;

import aoc.utils.AbstractDay;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day03 extends AbstractDay {
    List<int[]> input;
    int[] count;

    private int[] parseArray(String[] input) {
        return Arrays.stream(input)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int[] sumArrays(int[] array1, int[] array2) {
        if (array1.length != array2.length)
            throw new IllegalArgumentException("Arrays are differant sizes: %s and %s"
                    .formatted(Arrays.toString(array1), Arrays.toString(array2)));
        return IntStream.range(0, array1.length)
                .map(i -> array1[i] + array2[i])
                .toArray();
    }

    private int[] countOccurrences(List<int[]> input) {
        int[] initial = new int[input.get(0).length];
        return input.stream()
                .reduce(initial, this::sumArrays);
    }

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        input = stream
                .map(str -> str.split(""))
                .map(this::parseArray)
                .toList();

        count = countOccurrences(input);
    }

    @Override //3687446
    public void part1() {
        final int limit = input.size() / 2;

        String gammaRate = Arrays.stream(count)
                .mapToObj(i -> (i < limit) ? "0" : "1")
                .collect(Collectors.joining(""));
        String epsilonRate = Arrays.stream(gammaRate.split(""))
                .map(str -> (str.equals("0") ? "1" : "0"))
                .collect(Collectors.joining(""));

        result1 = Integer.toString(Integer.parseInt(gammaRate, 2) *
                Integer.parseInt(epsilonRate, 2));
    }

    private int[] filter(List<int[]> input, boolean filterLeast) {
        List<int[]> runner = input;
        for (int i = 0; i < input.get(0).length; i++) {
            if (runner.size() <= 1) break;

            int index = i;
            int limit = runner.size();
            int[] count = Arrays.stream(countOccurrences(runner))
                    .map(value -> (value < limit - value) ? 0 : 1)
                    .toArray();

            runner = runner.stream()
                    .filter(array -> filterLeast && count[index] == array[index]
                            || !filterLeast && count[index] != array[index])
                    .toList();
        }
        return runner.get(0);
    }

    private int[] filterLeastCommon(List<int[]> input) {
        return filter(input, true);
    }

    private int[] filterMostCommon(List<int[]> input) {
        return filter(input, false);
    }

    @Override
    public void part2() {
        String oxygenGenRating = Arrays.stream(filterLeastCommon(input))
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(""));

        String co2ScrubRating = Arrays.stream(filterMostCommon(input))
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(""));

        result2 = Integer.toString(Integer.parseInt(oxygenGenRating, 2) *
                Integer.parseInt(co2ScrubRating, 2));
    }
}
