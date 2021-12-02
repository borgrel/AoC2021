package aoc.utils;

import java.util.stream.Stream;

public interface Example {
    Stream<String> sampleInput();

    String sampleResultPart1();
    String sampleResultPart2();
}