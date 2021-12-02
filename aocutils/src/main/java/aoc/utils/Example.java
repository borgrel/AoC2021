package aoc.utils;

import java.util.stream.Stream;

public interface Example {
    Stream<String> sampleInput();

    String resultPart1();

    String resultPart2();
}