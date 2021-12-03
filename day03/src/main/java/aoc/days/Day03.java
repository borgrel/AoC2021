package aoc.days;

import aoc.utils.AbstractDay;
import aoc.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day03 extends AbstractDay {
    List<int[]> input;

    private int[] parseArray(String[] input) {
        return Arrays.stream(input)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int[] sumArrays(int[] array1, int[] array2) {
        if (array1.length != array2.length)
            throw new IllegalArgumentException("Arrays are different sizes: %s and %s"
                    .formatted(Arrays.toString(array1), Arrays.toString(array2)));
        return IntStream.range(0, array1.length)
                .map(i -> array1[i] + array2[i])
                .toArray();
    }

    private String multiplyBinary(String str1, String str2) {
        return Integer.toString(
                Integer.parseInt(str1, 2) *
                        Integer.parseInt(str2, 2));
    }

    private int[] countOccurrences(List<int[]> input) {
        int[] initial = new int[input.get(0).length];
        return input.stream()
                .reduce(initial, this::sumArrays);
    }

    private int[] mostCommon(List<int[]> input) {
        return Arrays.stream(countOccurrences(input))
                .map(value -> (value < input.size() - value) ? 0 : 1)
                .toArray();
    }

    @Override
    public void convertInput(@NotNull Stream<String> stream) {
        input = stream.map(str -> str.split(""))
                .map(this::parseArray)
                .toList();
    }

    @Override //3687446
    public void part1() {
        String gammaRate = Utils.stringConcat(mostCommon(input));

        String epsilonRate = Arrays.stream(gammaRate.split(""))
                .map(str -> (str.equals("0") ? "1" : "0"))
                .collect(Collectors.joining(""));

        result1 = multiplyBinary(gammaRate, epsilonRate);
    }

    private int[] filter(List<int[]> input, boolean filterLeast) {
        List<int[]> runner = input;
        for (int i = 0; i < input.get(0).length; i++) {
            if (runner.size() <= 1) break;

            int index = i; //needs effectively final
            int[] count = mostCommon(runner);

            runner = runner.stream()
                    .filter(array -> filterLeast ^ count[index] != array[index])
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
        String oxygenGenRating = Utils.stringConcat(filterLeastCommon(input));
        String co2ScrubRating = Utils.stringConcat(filterMostCommon(input));

        result2 = multiplyBinary(oxygenGenRating, co2ScrubRating);
    }
}
